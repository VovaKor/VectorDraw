
package vectordraw.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import vectordraw.actions.shape.SelectShapes;
import vectordraw.actions.shape.TranslateShapes;

import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;

import vectordraw.view.AViewShape;
import org.malai.javafx.action.library.MoveCamera;
import org.malai.javafx.instrument.JfxInteractor;
import org.malai.javafx.interaction.library.DnD;
import org.malai.javafx.interaction.library.KeysPressure;
import org.malai.javafx.interaction.library.Press;

/**
 * This instrument allows to manipulate (e.g. move or select) shapes.
 * 
 */
public class HandInstrument extends ACanvasInstrument {
	
	public HandInstrument() {
		super();
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		canvas.getViews().getChildren().addListener((ListChangeListener<Node>) evt -> {
			while(evt.next()) {
				if(evt.wasAdded()) {
					evt.getAddedSubList().forEach(v -> {
						v.setOnMouseEntered(mouseEvt -> {
							if(isActivated()) {
								canvas.setCursor(Cursor.HAND);
							}
						});
						v.setOnMouseExited(mouseEvt -> {
							if(activated) {
								canvas.setCursor(Cursor.DEFAULT);
							}
						});
					});
				}
			}
		});

		addInteractor(new Press2Select(this));
		addInteractor(new DnD2Select(this));
		addInteractor(new DnD2Translate(this));
		addInteractor(new DnD2MoveViewport(this));
		
		addInteractor(new CtrlA2SelectAllShapes(this));
//		
	}

	@Override
	public void setActivated(final boolean activ) {
		if(activated != activ) {
			super.setActivated(activ);
		}
	}

	@Override
	public void interimFeedback() {
		// The rectangle used for the interim feedback of the selection is removed.
		canvas.setOngoingSelectionBorder(null);
		canvas.setCursor(Cursor.DEFAULT);
	}



	private static class Press2Select extends JfxInteractor<SelectShapes, Press, HandInstrument> {
		Press2Select(final HandInstrument hand) throws InstantiationException, IllegalAccessException {
			super(hand, false, SelectShapes.class, Press.class, hand.canvas);
		}

		@Override
		public void initAction() {
			action.setDrawing(instrument.canvas.getDrawing());
		}

		@Override
		public void updateAction() {
			interaction.getSrcObject().ifPresent(target -> {
				final IShape targetSh = ((AViewShape<?>) target.getParent()).getModel();

				if(interaction.isShiftPressed()) {
					instrument.canvas.getDrawing().getSelectedShapes().getShapes().stream().filter(sh -> sh != targetSh).forEach(sh -> action.addShape(sh));
				}else {
					if(interaction.isCtrlPressed()) {
						instrument.canvas.getDrawing().getSelectedShapes().getShapes().forEach(sh -> action.addShape(sh));
						action.addShape(targetSh);
					}else {
						action.setShape(targetSh);
					}
				}
			});
		}

		@Override
		public boolean isConditionRespected() {
			final Optional<Node> obj = interaction.getSrcObject();
			return obj.isPresent() && obj.get().getParent() instanceof AViewShape<?>;
		}
	}


	private static class CtrlA2SelectAllShapes extends JfxInteractor<SelectShapes, KeysPressure, HandInstrument> {
		CtrlA2SelectAllShapes(final HandInstrument hand) throws InstantiationException, IllegalAccessException {
			super(hand, false, SelectShapes.class, KeysPressure.class, hand.canvas);
		}

		@Override
		public void initAction() {
			instrument.canvas.getDrawing().getShapes().forEach(sh -> action.addShape(sh));
			action.setDrawing(instrument.canvas.getDrawing());
		}

		@Override
		public boolean isConditionRespected() {
			return interaction.getKeyCodes().size() == 2 && interaction.getKeyCodes().contains(KeyCode.A);
                        //&& interaction.getKeyCodes().contains(LSystem.INSTANCE.getControlKey());
		}
	}


//	

	private static class DnD2Translate extends JfxInteractor<TranslateShapes, DnD, HandInstrument> {
		DnD2Translate(final HandInstrument hand) throws IllegalAccessException, InstantiationException {
			super(hand, true, TranslateShapes.class, DnD.class, hand.canvas);
		}

		@Override
		public void initAction() {
			action.setDrawing(instrument.canvas.getDrawing());
			action.setShape(instrument.canvas.getDrawing().getSelectedShapes().duplicateDeep(false));
		}


		@Override
		public void updateAction() {
			interaction.getSrcPoint().ifPresent(start -> interaction.getEndPt().ifPresent(end -> {
                            final IPoint startPt = ShapeFactory.INST.createPoint(start.getX(), start.getY());
				final IPoint endPt = ShapeFactory.INST.createPoint(end.getX(), end.getY());
//				
				action.setTx(endPt.getX() - startPt.getX());
				action.setTy(endPt.getY() - startPt.getY());
			}));
		}

		@Override
		public boolean isConditionRespected() {
			final Optional<Node> startObject = interaction.getSrcObject();
			final MouseButton button = interaction.getButton().orElse(MouseButton.NONE);

			return startObject.isPresent() && !instrument.canvas.getDrawing().getSelectedShapes().isEmpty() &&
				(startObject.get() == instrument.canvas && button == MouseButton.SECONDARY ||
				startObject.get().getParent() instanceof AViewShape<?> && (button == MouseButton.PRIMARY || button == MouseButton.SECONDARY));
		}


		@Override
		public void interimFeedback() {
			super.interimFeedback();
			instrument.canvas.setCursor(Cursor.MOVE);
		}
	}


	private static class DnD2Select extends JfxInteractor<SelectShapes, DnD, HandInstrument> {
		/** The is rectangle is used as interim feedback to show the rectangle made by the user to select some shapes. */
		private Bounds selectionBorder;
		List<IShape> selectedShapes;
		List<AViewShape<?>> selectedViews;

		DnD2Select(final HandInstrument hand) throws IllegalAccessException, InstantiationException {
			super(hand, true, SelectShapes.class, DnD.class, hand.canvas);
		}

		@Override
		public void initAction() {
			action.setDrawing(instrument.canvas.getDrawing());
			selectedShapes = new ArrayList<>(instrument.canvas.getDrawing().getSelectedShapes().getShapes());
			selectedViews = instrument.canvas.getSelectedViews();
			Platform.runLater(() -> instrument.canvas.requestFocus());
		}

		@Override
		public void updateAction() {
			interaction.getEndPt().ifPresent(endPt -> interaction.getSrcPoint().ifPresent(startPt -> {
				final IPoint start = instrument.getAdaptedOriginPoint(startPt);
				final IPoint end = instrument.getAdaptedOriginPoint(endPt);
				final double minX = Math.min(start.getX(), end.getX());
				final double maxX = Math.max(start.getX(), end.getX());
				final double minY = Math.min(start.getY(), end.getY());
				final double maxY = Math.max(start.getY(), end.getY());
				final double zoom = instrument.canvas.getZoom();

				// Updating the rectangle used for the interim feedback and for the selection of shapes.
				selectionBorder = new BoundingBox(minX, minY, Math.max(maxX - minX, 1d), Math.max(maxY - minY, 1d));
				// Cleaning the selected shapes in the action.
				action.setShape(null);

				if(interaction.isShiftPressed()) {
					selectedViews.stream().filter(view -> !view.intersects(selectionBorder)).forEach(view -> action.addShape(view.getModel()));
				}else {
					if(interaction.isCtrlPressed()) {
						selectedShapes.forEach(sh -> action.addShape(sh));
					}
					if(!selectionBorder.isEmpty()) {
						instrument.canvas.getViews().getChildren().stream().filter(view -> view.intersects(selectionBorder)).
							forEach(view -> action.addShape((IShape) view.getUserData()));
					}
				}
			}));
		}

		@Override
		public boolean isConditionRespected() {
			return interaction.getButton().orElse(MouseButton.NONE) == MouseButton.PRIMARY && interaction.getSrcObject().orElse(null) == instrument.canvas;
		}

		@Override
		public void interimFeedback() {
			instrument.canvas.setOngoingSelectionBorder(selectionBorder);
			selectionBorder = null;
		}
	}
}


class DnD2MoveViewport extends JfxInteractor<MoveCamera, DnD, ACanvasInstrument> {
	private IPoint pt = ShapeFactory.INST.createPoint();

	DnD2MoveViewport(final ACanvasInstrument ins) throws IllegalAccessException, InstantiationException {
		super(ins, true, MoveCamera.class, DnD.class, ins.canvas);
	}

	@Override
	public void initAction() {
		action.setScrollPane(instrument.canvas.getScrollPane());
		interaction.getSrcPoint().ifPresent(start -> pt.setPoint(start.getX(), start.getY()));
	}

	@Override
	public void updateAction() {
		interaction.getSrcPoint().ifPresent(start -> interaction.getEndPt().ifPresent(end -> {
			final ScrollPane pane = instrument.canvas.getScrollPane();
			action.setPx(pane.getHvalue() - (end.getX() - pt.getX()) / instrument.canvas.getWidth());
			action.setPy(pane.getVvalue() - (end.getY() - pt.getY()) / instrument.canvas.getHeight());
			pt = pt.centralSymmetry(ShapeFactory.INST.createPoint(start));
		}));
	}

	@Override
	public boolean isConditionRespected() {
		return interaction.getButton().orElse(MouseButton.NONE) == MouseButton.MIDDLE;
	}

	@Override
	public void interimFeedback() {
		instrument.canvas.setCursor(Cursor.MOVE);
	}
}
