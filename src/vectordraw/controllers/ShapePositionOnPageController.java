
package vectordraw.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import vectordraw.actions.shape.TranslateShapes;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IPoint;
import org.malai.javafx.instrument.library.SpinnerInteractor;

/**
 * This instrument modifies arc dimensions and coordinates of shapes or pencil parameters.
*
 */
public class ShapePositionOnPageController extends AShapePropertyCustomiser implements Initializable {
	/** Sets the X-coordinate of the top-left position. */
	@FXML Spinner<Double> tlxS;

	/** Sets the Y-coordinate of the top-left position. */
	@FXML Spinner<Double> tlyS;

	@FXML TitledPane mainPane;

	/**
	 * Creates the instrument.
	 */
	public ShapePositionOnPageController() {
		super();
	}

	@Override
	protected void update(final IGroup shape) {
		if(shape.isEmpty() || !hand.isActivated()) {
			setActivated(false);
		}
		else {
			setActivated(true);
			final IPoint tl = shape.getTopLeftPoint();
			tlxS.getValueFactory().setValue(tl.getX());
			tlyS.getValueFactory().setValue(tl.getY());
		}
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		mainPane.managedProperty().bind(mainPane.visibleProperty());

		scrollOnSpinner(tlxS);
		scrollOnSpinner(tlyS);
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		addInteractor(new Spinner2TranslateShape(this));
	}

	@Override
	protected void setWidgetsVisible(final boolean visible) {
		mainPane.setVisible(visible);
	}

	private static class Spinner2TranslateShape extends SpinnerInteractor<TranslateShapes, ShapePositionOnPageController> {
		Spinner2TranslateShape(final ShapePositionOnPageController ins) throws InstantiationException, IllegalAccessException {
			super(ins, TranslateShapes.class, ins.tlxS, ins.tlyS);
		}

		@Override
		public void initAction() {
			final IPoint tl = instrument.drawing.getSelectedShapes().getTopLeftPoint();
			final double value = (Double) interaction.getWidget().getValue();

			action.setDrawing(instrument.drawing);
			action.setShape(instrument.drawing.getSelectedShapes().duplicateDeep(false));

			if(interaction.getWidget() == instrument.tlxS) {
				action.setTx(value - tl.getX());
			}
			else {
				action.setTy(value - tl.getY());
			}
		}
	}
}
