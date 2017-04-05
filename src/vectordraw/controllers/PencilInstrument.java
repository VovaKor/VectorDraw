
package vectordraw.controllers;


import vectordraw.models.EShapeTypes;
import java.util.List;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import vectordraw.actions.shape.AddShape;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.EBorderPos;
import vectordraw.models.interfaces.shape.IControlPointShape;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IModifiablePointsShape;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IRectangularShape;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.ISquaredShape;
import vectordraw.view.ViewFactory;
import vectordraw.view.AViewShape;
import org.malai.interaction.Interaction;
import org.malai.javafx.instrument.JfxInteractor;
import org.malai.javafx.interaction.JfxInteraction;
import org.malai.javafx.interaction.library.AbortableDnD;
import org.malai.javafx.interaction.library.MultiClick;
import org.malai.stateMachine.MustAbortStateMachineException;
import vectordraw.models.interfaces.shape.IArc;

/**
 * This instrument allows to draw shapes.
 */
public class PencilInstrument extends ACanvasInstrument {
	/** The current editing choice (rectangle, ellipse, etc.) of the instrument. */
	protected EShapeTypes currentChoice;

	private IGroup groupParams;


	public PencilInstrument() {
		super();
		currentChoice = EShapeTypes.RECT;
	}

//	

	public IGroup getGroupParams() {
		if(groupParams == null) {
			groupParams = ShapeFactory.INST.createGroup();
			groupParams.addShape(ShapeFactory.INST.createRectangle());
			groupParams.addShape(ShapeFactory.INST.createArc());
			groupParams.addShape(ShapeFactory.INST.createPolyline());
			groupParams.addShape(ShapeFactory.INST.createBezierCurve());
			
		}
		return groupParams;
	}

	@Override
	public void setActivated(boolean act) {
		if(this.activated != act) super.setActivated(act);
	}

	@Override
	public void interimFeedback() {
		canvas.setTempView(null);
		canvas.setCursor(Cursor.DEFAULT);
	}

	@Override
	protected void initialiseInteractors() throws IllegalAccessException, InstantiationException {
		
		addInteractor(new DnD2AddShape());
		addInteractor(new MultiClic2AddShape());
		
	}

	/**
	 * @return An instance of a shape configured (thickness, colours, etc.) with the parameters of the pencil.
	 * 
	 */
	public IShape createShapeInstance() {
		return setShapeParameters(currentChoice.createShapeInstance());
	}

	/**
	 * Configures the given shape with the parameters (e.g. thickness, colours, etc.) of the pencil.
	 * @param shape The shape to configure.
	 * @return The modified shape given as argument.
	 * 
	 */
	IShape setShapeParameters(final IShape shape) {
		if(shape instanceof IModifiablePointsShape) {
			final IModifiablePointsShape mod = (IModifiablePointsShape) shape;
			mod.addPoint(ShapeFactory.INST.createPoint());
			mod.addPoint(ShapeFactory.INST.createPoint());
		}

		shape.copy(getGroupParams());
		shape.setModified(true);
		return shape;
	}


	/** @return The current editing choice. */
	public EShapeTypes getCurrentChoice() {
		return currentChoice;
	}

	/**
	 * Sets the current editing choice.
	 * @param choice The new editing choice to set.
	 * 
	 */
	public void setCurrentChoice(EShapeTypes choice) {
		currentChoice = choice;
	}


	private abstract class PencilInteractor<I extends JfxInteraction> extends JfxInteractor<AddShape, I, PencilInstrument> {
		PencilInteractor(final Class<I> clazzInteraction) throws InstantiationException, IllegalAccessException {
			super(PencilInstrument.this, false, AddShape.class, clazzInteraction, PencilInstrument.this.canvas);
		}

		AViewShape<?> tmpShape;

		@Override
		public void initAction() {
			final IShape sh = instrument.createShapeInstance();
			ViewFactory.INSTANCE.createView(sh).ifPresent(v -> {
				tmpShape = v;
				action.setShape(sh);
				action.setDrawing(instrument.canvas.getDrawing());
				instrument.canvas.setTempView(tmpShape);
				Platform.runLater(() -> instrument.canvas.requestFocus());
			});
		}
	}


	private class DnD2AddShape extends PencilInteractor<AbortableDnD> {
		DnD2AddShape() throws InstantiationException, IllegalAccessException {
			super(AbortableDnD.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.getShape().ifPresent(sh -> interaction.getSrcPoint().ifPresent(startPt -> {
				final IPoint pt = instrument.getAdaptedPoint(startPt);

				// For squares and circles, the centre of the shape is the reference point during the creation.
				if(sh instanceof ISquaredShape) {
					final ISquaredShape sq = (ISquaredShape) sh;
					sq.setPosition(pt.getX() - 1.0, pt.getY() - 1.0);
					sq.setWidth(2.0);
				
				}else {
					sh.translate(pt.getX(), pt.getY());
				}
			}));
		}

		@Override
		public void updateAction() {
			action.getShape().ifPresent(sh -> interaction.getSrcPoint().ifPresent(srcPt -> interaction.getEndPt().ifPresent(finalPt -> {
				// Getting the points depending on the current zoom.
				final IPoint startPt = instrument.getAdaptedPoint(srcPt);
				final IPoint endPt = instrument.getAdaptedPoint(finalPt);

				if(sh instanceof ISquaredShape) {
					updateShapeFromCentre((ISquaredShape) sh, startPt, endPt.getX());
					
                                        sh.setModified(true);
					action.getDrawing().ifPresent(drawing -> drawing.setModified(true));
//				
				}else if(sh instanceof IRectangularShape) {
					updateShapeFromDiag((IRectangularShape) sh, startPt, endPt);
					sh.setModified(true);
					action.getDrawing().ifPresent(drawing -> drawing.setModified(true));
				}
			})));
		}

		/**
		 * @param shape The shape to analyse.
		 * @return The gap that must respect the pencil to not allow shape to disappear when they are too small.
		 
		 */
		private double getGap(final IShape shape) {
			// These lines are necessary to avoid shape to disappear. It appends when the borders position is INTO.
			// In this case,the minimum radius must be computed using the thickness and the double size.
			if(shape.isBordersMovable() && shape.getBordersPosition() == EBorderPos.INTO)
				return shape.getThickness() + (shape.isDbleBorderable() && shape.hasDbleBord() ? shape.getDbleBordSep() : 0.0);
			return 1.0;
		}

		private void updateShapeFromCentre(final ISquaredShape shape, final IPoint startPt, final double endX) {
			final double sx = startPt.getX();
			final double radius = Math.max(sx < endX ? endX - sx : sx - endX, getGap(shape));
			shape.setPosition(sx - radius, startPt.getY() + radius);
			shape.setWidth(radius * 2.0);
//                        
		}

		private void updateShapeFromDiag(final IRectangularShape shape, final IPoint startPt, final IPoint endPt) {
			final double gap = getGap(shape);
			double v1 = startPt.getX();
			double v2 = endPt.getX();
			double tlx = Double.NaN;
			double tly = Double.NaN;
			double brx = Double.NaN;
			double bry = Double.NaN;
			boolean ok = true;

			if(Math.abs(v1 - v2) > gap) {
				if(v1 < v2) {
					brx = v2;
					tlx = v1;
				}else {
					brx = v1;
					tlx = v2;
				}
			}else ok = false;

			v1 = startPt.getY();
			v2 = endPt.getY();

			if(Math.abs(v1 - v2) > gap) {
				if(v1 < v2) {
					bry = v2;
					tly = v1;
				}else {
					bry = v1;
					tly = v2;
				}
			}else ok = false;

			if(ok) {
				shape.setPosition(tlx, bry);
				shape.setWidth(brx - tlx);
				shape.setHeight(bry - tly);
			}
		}

		@Override
		public boolean isConditionRespected() {
			final EShapeTypes ec = instrument.currentChoice;
			return interaction.getButton().orElse(MouseButton.NONE) == MouseButton.PRIMARY &&
				(ec == EShapeTypes.RECT || ec == EShapeTypes.ELLIPSE || ec == EShapeTypes.SQUARE ||
				ec == EShapeTypes.CIRCLE || ec == EShapeTypes.RHOMBUS ||
				ec == EShapeTypes.TRIANGLE || ec == EShapeTypes.OPEN_ARC || 
                                ec == EShapeTypes.CHORD || ec == EShapeTypes.WEDGE);
		}
	}


	private class MultiClic2AddShape extends PencilInteractor<MultiClick> {
		MultiClic2AddShape() throws InstantiationException, IllegalAccessException {
			super(MultiClick.class);
		}

		// To avoid the overlapping with the DnD2AddShape, the starting interaction must be
		// aborted when the condition is not respected, i.e. when the selected shape type is not devoted to the multi-click interaction.
		@Override
		public boolean isInteractionMustBeAborted() {
			return !isConditionRespected();
		}

		@Override
		public void updateAction() {
			action.getShape().ifPresent(sh -> {
				final List<Point3D> pts = interaction.getPoints();
				final IPoint currPoint = instrument.getAdaptedPoint(interaction.getCurrentPosition());
				final IModifiablePointsShape shape = (IModifiablePointsShape) sh;

				if(shape.getNbPoints() == pts.size() && !interaction.isLastPointFinalPoint()) {
					shape.addPoint(ShapeFactory.INST.createPoint(currPoint.getX(), currPoint.getY()), -1);
				}else {
					shape.setPoint(currPoint.getX(), currPoint.getY(), -1);
				}

				// Curves need to be balanced.
				if(sh instanceof IControlPointShape) {
					((IControlPointShape) sh).balance();
				}

				shape.setModified(true);
				action.getDrawing().ifPresent(dr -> dr.setModified(true));
			});
		}

		@Override
		public void initAction() {
			super.initAction();
			action.getShape().ifPresent(shape -> {
				if(shape instanceof IModifiablePointsShape) {
					final IModifiablePointsShape modShape = (IModifiablePointsShape) shape;
					final IPoint pt = instrument.getAdaptedPoint(interaction.getPoints().get(0));
					modShape.setPoint(pt, 0);
					modShape.setPoint(pt.getX() + 1.0, pt.getY() + 1.0, 1);
				}
			});
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.currentChoice == EShapeTypes.POLYGON || instrument.currentChoice == EShapeTypes.LINES ||
				instrument.currentChoice == EShapeTypes.BEZIER_CURVE;
		}

		@Override
		public void interactionStarts(final Interaction inter) throws MustAbortStateMachineException {
			super.interactionStarts(inter);
			interaction.setMinPoints(instrument.currentChoice == EShapeTypes.POLYGON ? 3 : 2);
		}
	}


//	
}
