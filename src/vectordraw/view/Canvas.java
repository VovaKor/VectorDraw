
package vectordraw.view;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;
import org.malai.action.Action;
import org.malai.action.ActionHandler;
import org.malai.action.ActionsRegistry;
import org.malai.presentation.ConcretePresentation;
import org.malai.properties.Zoomable;
import org.malai.undo.Undoable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IDrawing;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.util.EPage;


/**
 * The JFX canvas where shapes are painted.
 */
public class Canvas extends Pane implements ConcretePresentation, ActionHandler, Zoomable {
	/** The margin used to surround the drawing. */
	public static final int MARGINS = 100;

	/** The origin of the drawing in the whole drawing area. */
	public static final  IPoint ORIGIN = ShapeFactory.INST.createPoint(MARGINS, MARGINS);

	/** The model of the view. */
	protected final  IDrawing drawing;

	/** The zoom applied on the canvas. */
	protected final DoubleProperty zoom;

	/** The current page of the canvas. */
	private final  PageView page;

	/** The views of the shape. */
	private final  Group shapesPane;

	/** The pane that contains widgets to handle shapes, such as handlers, text fields. */
	private final  Group widgetsPane;

	private final  Rectangle selectionBorder;

	private final Rectangle ongoingSelectionBorder;

	private final  Map<IShape, AViewShape<?>> shapesToViewMap;

	
	/** Defined whether the canvas has been modified. */
	protected boolean isCanvasModified;

	/** The temporary view that the canvas may contain. */
	protected Optional<AViewShape<?>> tempView;

	/**
	 * Creates the canvas.
	 */
	public Canvas() {
		super();

		isCanvasModified = false;
		drawing = ShapeFactory.INST.createDrawing();
		zoom = new SimpleDoubleProperty(1d);
		tempView = Optional.empty();
		page = new PageView(EPage.USLETTER, getOrigin());
		
		widgetsPane = new Group();
		shapesPane = new Group();
		shapesToViewMap = new HashMap<>();
		selectionBorder = new Rectangle();
		ongoingSelectionBorder = new Rectangle();

		selectionBorder.setFocusTraversable(false);
		ongoingSelectionBorder.setFocusTraversable(false);
		ongoingSelectionBorder.setMouseTransparent(true);
		ongoingSelectionBorder.setFill(null);
		ongoingSelectionBorder.setStroke(Color.GRAY);
		ongoingSelectionBorder.setStrokeLineCap(StrokeLineCap.BUTT);
		ongoingSelectionBorder.getStrokeDashArray().addAll(7d, 7d);

		getChildren().add(page);
		getChildren().add(shapesPane);
		getChildren().add(widgetsPane);
		widgetsPane.getChildren().add(selectionBorder);
		widgetsPane.getChildren().add(ongoingSelectionBorder);
		widgetsPane.relocate(ORIGIN.getX(), ORIGIN.getY());
		shapesPane.relocate(ORIGIN.getX(), ORIGIN.getY());

		setPrefWidth(MARGINS * 2d + page.getPage().getWidth() * IShape.PPC);
		setPrefHeight(MARGINS * 2d + page.getPage().getHeight() * IShape.PPC);

		defineShapeListToViewBinding();
		configureSelection();

		
		ActionsRegistry.INSTANCE.addHandler(this);

		shapesPane.setFocusTraversable(true);
		shapesPane.addEventHandler(MouseEvent.ANY, evt -> shapesPane.requestFocus());
	}



	private void configureSelection() {
		selectionBorder.setMouseTransparent(true);
		selectionBorder.setVisible(false);
		selectionBorder.setFill(null);
		selectionBorder.setStroke(Color.GRAY);
		selectionBorder.setStrokeLineCap(StrokeLineCap.BUTT);
		selectionBorder.getStrokeDashArray().addAll(7d, 7d);

		drawing.getSelectedShapes().getShapes().addListener((Change<? extends IShape> evt) -> updateSelectionBorders());
	}


	private void updateSelectionBorders() {
		final ObservableList<IShape> selection = drawing.getSelectedShapes().getShapes();
		if(selection.isEmpty()) {
			selectionBorder.setVisible(false);
		}
		else {
			final Rectangle2D rec = selection.stream().map(sh -> shapesToViewMap.get(sh)).filter(vi -> vi!=null).map(vi -> {
				Bounds b = vi.getBoundsInLocal();
				return (Rectangle2D) new Rectangle2D.Double(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
			}).reduce(Rectangle2D::createUnion).orElse(new Rectangle2D.Double());

			selectionBorder.setLayoutX(rec.getMinX());
			selectionBorder.setLayoutY(rec.getMinY());
			selectionBorder.setWidth(rec.getWidth());
			selectionBorder.setHeight(rec.getHeight());
			selectionBorder.setVisible(true);
		}
	}


	public void setOngoingSelectionBorder(final  Bounds bounds) {
		if(bounds == null) {
			ongoingSelectionBorder.setVisible(false);
		}else {
			ongoingSelectionBorder.setLayoutX(bounds.getMinX());
			ongoingSelectionBorder.setLayoutY(bounds.getMinY());
			ongoingSelectionBorder.setWidth(bounds.getWidth());
			ongoingSelectionBorder.setHeight(bounds.getHeight());
			ongoingSelectionBorder.setVisible(true);
		}
	}


	/**
	 * @return The selected views.
	 */
	public List<AViewShape<?>> getSelectedViews() {
		return drawing.getSelectedShapes().getShapes().stream().map(sh -> shapesToViewMap.get(sh)).collect(Collectors.toList());
	}


	private void defineShapeListToViewBinding() {
		drawing.getShapes().addListener((Change<? extends IShape> evt) -> {
			while(evt.next()) {
				if(evt.wasAdded()) {
					evt.getAddedSubList().forEach(sh -> ViewFactory.INSTANCE.createView(sh).ifPresent(v -> {
						shapesToViewMap.put(sh, v);
						shapesPane.getChildren().add(v);
					}));
				}else if(evt.wasRemoved()) {
					evt.getRemoved().forEach(sh -> shapesPane.getChildren().remove(shapesToViewMap.remove(sh)));
				}
			}
		});
	}

	
	/**
	 * @return The point where the page is located.
	 */
	public  IPoint getOrigin() {
		return ORIGIN;
	}

//	
	/**
	 * @return The page of the drawing area. Cannot be null.
	 */
	public PageView getPage() {
		return page;
	}

	@Override
	public void update() {
		updateSelectionBorders();
	}

	@Override
	public double getZoom() {
		return zoom.getValue();
	}

	@Override
	public void onActionExecuted(final Action a) {
		update();
	}

	@Override
	public void onUndoableAdded(final Undoable u) {
		/* Nothing to do. */
	}

	@Override
	public void onUndoableCleared() {
		/* Nothing to do. */
	}

	@Override
	public void onUndoableRedo(final Undoable u) {
		/* Nothing to do. */
	}

	@Override
	public void onUndoableUndo(final Undoable u) {
		/* Nothing to do. */
	}

	@Override
	public void onActionAborted(final Action a) {
		/* Nothing to do. */
	}

	@Override
	public void onActionAdded(final Action a) {
		/* Nothing to do. */
	}

	@Override
	public void onActionCancelled(final Action a) {
		/* Nothing to do. */
	}

	@Override
	public void onActionDone(final Action a) {
		/* Nothing to do. */
	}

	@Override
	public void save(final boolean generalPreferences, final String nsURI, final Document document, final Element root) {
//		
	}

	@Override
	public void load(final boolean generalPreferences, final String nsURI, final Element meta) {
//		
	}

	@Override
	public boolean isModified() {
		return isCanvasModified;
        
	}

	@Override
	public void setModified(final boolean modif) {
		isCanvasModified = modif;
//		
	}

	@Override
	public void reinit() {
		synchronized(shapesPane) {
			shapesPane.getChildren().clear();
		}
		zoom.setValue(1d);
		update();
	}


	@Override
	public double getZoomIncrement() {
		return 0.05;
	}

	@Override
	public double getMaxZoom() {
		return 4.5;
	}

	@Override
	public double getMinZoom() {
		return 0.1;
	}

	@Override
	public Point2D getZoomedPoint(final double x, final double y) {
		final double zoomValue = zoom.getValue();
		return new Point2D.Double(x / zoomValue, y / zoomValue);
	}

	@Override
	public Point2D getZoomedPoint(final Point pt) {
		return pt == null ? new Point2D.Double() : getZoomedPoint(pt.x, pt.y);
	}

	@Override
	public void setZoom(final double x, final double y, final double z) {
		if(z <= getMaxZoom() && z >= getMinZoom() && !MathUtils.INST.equalsDouble(z, zoom.getValue())) {
			zoom.setValue(z);
			final Duration duration = Duration.millis(250);
			ParallelTransition parallelTransition = new ParallelTransition();
			parallelTransition.getChildren().addAll(
				new Timeline(new KeyFrame(duration, new KeyValue(scaleYProperty(), z))),
				new Timeline(new KeyFrame(duration, new KeyValue(scaleXProperty(), z)))
			);

			parallelTransition.play();

			// borderIns.update();
			update();
			setModified(true);
		}
	}

	/**
	 * Converts the given point in the coordinate system based on the canvas' origin. The given
	 * point must be in the coordinate system of a container widget (the top-left point is the
	 * origin).
	 * @param pt The point to convert.
	 * @return The converted point or null if the given point is null.
	 */
	public IPoint convertToOrigin(final IPoint pt) {
		final IPoint convertion;
		if(pt == null) convertion = null;
		else {
			convertion = ShapeFactory.INST.createPoint(pt);
			convertion.translate(-ORIGIN.getX(), -ORIGIN.getY());
		}
		return convertion;
	}

	/**
	 * @return The model of the canvas.
	 */
	public  IDrawing getDrawing() {
		return drawing;
	}

	/**
	 * Sets the temporary view.
	 * @param view The new temporary view.
	 */
	public void setTempView(final AViewShape<?> view) {
		tempView.ifPresent(v -> shapesPane.getChildren().remove(v));
		tempView = Optional.ofNullable(view);
		tempView.ifPresent(v -> shapesPane.getChildren().add(v));
	}

	public ScrollPane getScrollPane() {
		Parent parent = getParent();
		while(parent!=null && !(parent instanceof ScrollPane)) {
			parent = parent.getParent();
		}
		return (ScrollPane) parent;
	}

	public void addToWidgetLayer(final javafx.scene.Node node) {
		if(node!=null) {
			widgetsPane.getChildren().add(node);
		}
	}

	public boolean removeFromWidgetLayer(final javafx.scene.Node node) {
		return node != null && widgetsPane.getChildren().remove(node);
	}

	/**
	 * @return The views that the canvas contains.
	 */
	public Group getViews() {
		return shapesPane;
	}
}
