/*
*
 *
*
 *
*
*
 *
*
*
 */
package vectordraw.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import vectordraw.actions.shape.EShapeProperties;
import vectordraw.models.interfaces.shape.EFillingStyle;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.view.IWidgetCreator;

/**
 * This instrument modifies filling properties of shapes or the pencil.
*
 */
public class ShapeFillingController extends AShapePropertyCustomiser implements Initializable, IWidgetCreator {
	/** Sets the colour of the interior of a shape. */
	@FXML protected ColorPicker fillColButton;

	/** Sets the colour of the hatchings. */
	@FXML protected ColorPicker hatchColButton;

	/** Changes the first colour of a gradient. */
	@FXML protected ColorPicker gradStartColButton;

	/** Changes the second colour of a gradient. */
	@FXML protected ColorPicker gradEndColButton;

	/** Changes the style of filling. */
	@FXML protected ComboBox<EFillingStyle> fillStyleCB;

	/** Changes the mid point of the gradient. */
	@FXML protected Spinner<Double> gradMidPtField;

	/** Changes the angle of the gradient. */
	@FXML protected Spinner<Double> gradAngleField;

	/** Changes the separation of the hatchings. */
	@FXML protected Spinner<Double> hatchSepField;

	/** Changes the angle of the hatchings. */
	@FXML protected Spinner<Double> hatchAngleField;

	/** Changes the width of the hatchings. */
	@FXML protected Spinner<Double> hatchWidthField;

	@FXML protected AnchorPane fillPane;
	@FXML protected AnchorPane hatchingsPane;
	@FXML protected AnchorPane gradientPane;
	@FXML protected TitledPane mainPane;

	/**
	 * Creates the instrument.
	 */
	public ShapeFillingController() {
		super();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		mainPane.managedProperty().bind(mainPane.visibleProperty());

		final Map<EFillingStyle, Image> cache = new HashMap<>();
		cache.put(EFillingStyle.NONE, new Image("/res/hatch/hatch.none.png"));
		cache.put(EFillingStyle.PLAIN, new Image("/res/hatch/hatch.solid.png"));
		cache.put(EFillingStyle.CLINES, new Image("/res/hatch/hatch.cross.png"));
		cache.put(EFillingStyle.CLINES_PLAIN, new Image("/res/hatch/hatchf.cross.png"));
		cache.put(EFillingStyle.HLINES, new Image("/res/hatch/hatch.horiz.png"));
		cache.put(EFillingStyle.HLINES_PLAIN, new Image("/res/hatch/hatchf.horiz.png"));
		cache.put(EFillingStyle.VLINES, new Image("/res/hatch/hatch.vert.png"));
		cache.put(EFillingStyle.VLINES_PLAIN, new Image("/res/hatch/hatchf.vert.png"));
		cache.put(EFillingStyle.GRAD, new Image("/res/hatch/gradient.png"));
		initComboBox(fillStyleCB, cache, EFillingStyle.values());

		scrollOnSpinner(hatchAngleField);
		scrollOnSpinner(hatchSepField);
		scrollOnSpinner(hatchWidthField);
		scrollOnSpinner(gradAngleField);
		scrollOnSpinner(gradMidPtField);
	}

	@Override
	protected void update(final IGroup shape) {
		if(shape.isInteriorStylable()) {
			setActivated(true);
			final EFillingStyle style = shape.getFillingStyle();
			final boolean isFillable = style.isFilled();
			final boolean hatchings = style.isHatchings();
			final boolean gradient = style.isGradient();

			// Updating the visibility of the widgets.
			gradientPane.setVisible(gradient);
			hatchingsPane.setVisible(hatchings);
			fillPane.setVisible(isFillable);

			fillStyleCB.getSelectionModel().select(style);
			if(isFillable) {
				fillColButton.setValue(shape.getFillingCol().toJFX());
			}
			if(hatchings) {
				hatchColButton.setValue(shape.getHatchingsCol().toJFX());
				hatchAngleField.getValueFactory().setValue(Math.toDegrees(shape.getHatchingsAngle()));
				hatchSepField.getValueFactory().setValue(shape.getHatchingsSep());
				hatchWidthField.getValueFactory().setValue(shape.getHatchingsWidth());
			}else if(gradient) {
				gradStartColButton.setValue(shape.getGradColStart().toJFX());
				gradEndColButton.setValue(shape.getGradColEnd().toJFX());
				gradAngleField.getValueFactory().setValue(Math.toDegrees(shape.getGradAngle()));
				gradMidPtField.getValueFactory().setValue(shape.getGradMidPt());
			}
		}else {
			setActivated(false);
		}
	}

	@Override
	protected void setWidgetsVisible(final boolean visible) {
		mainPane.setVisible(visible);
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		addInteractor(new List4Pencil(this, fillStyleCB, EShapeProperties.FILLING_STYLE));
		addInteractor(new List4Selection(this, fillStyleCB, EShapeProperties.FILLING_STYLE));
		addInteractor(new ColourPicker4Selection(this, fillColButton, EShapeProperties.COLOUR_FILLING));
		addInteractor(new ColourPicker4Selection(this, gradStartColButton, EShapeProperties.COLOUR_GRADIENT_START));
		addInteractor(new ColourPicker4Selection(this, gradEndColButton, EShapeProperties.COLOUR_GRADIENT_END));
		addInteractor(new ColourPicker4Selection(this, hatchColButton, EShapeProperties.COLOUR_HATCHINGS));
		addInteractor(new ColourPicker4Pencil(this, fillColButton, EShapeProperties.COLOUR_FILLING));
		addInteractor(new ColourPicker4Pencil(this, gradStartColButton, EShapeProperties.COLOUR_GRADIENT_START));
		addInteractor(new ColourPicker4Pencil(this, gradEndColButton, EShapeProperties.COLOUR_GRADIENT_END));
		addInteractor(new ColourPicker4Pencil(this, hatchColButton, EShapeProperties.COLOUR_HATCHINGS));
		addInteractor(new Spinner4Pencil(this, gradMidPtField, EShapeProperties.GRAD_MID_POINT, false));
		addInteractor(new Spinner4Pencil(this, gradAngleField, EShapeProperties.GRAD_ANGLE, true));
		addInteractor(new Spinner4Pencil(this, hatchAngleField, EShapeProperties.HATCHINGS_ANGLE, true));
		addInteractor(new Spinner4Pencil(this, hatchWidthField, EShapeProperties.HATCHINGS_WIDTH, false));
		addInteractor(new Spinner4Pencil(this, hatchSepField, EShapeProperties.HATCHINGS_SEP, false));
		addInteractor(new Spinner4Selection(this, gradMidPtField, EShapeProperties.GRAD_MID_POINT, false));
		addInteractor(new Spinner4Selection(this, gradAngleField, EShapeProperties.GRAD_ANGLE, true));
		addInteractor(new Spinner4Selection(this, hatchAngleField, EShapeProperties.HATCHINGS_ANGLE, true));
		addInteractor(new Spinner4Selection(this, hatchWidthField, EShapeProperties.HATCHINGS_WIDTH, false));
		addInteractor(new Spinner4Selection(this, hatchSepField, EShapeProperties.HATCHINGS_SEP, false));
	}
}
