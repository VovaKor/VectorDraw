
package vectordraw.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vectordraw.actions.shape.EShapeProperties;
import vectordraw.models.interfaces.prop.ILineArcProp;
import vectordraw.models.interfaces.shape.EBorderPos;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.ELineStyle;
import vectordraw.view.IWidgetCreator;

/**
 * This instrument modifies border properties of shapes or the pencil.
 * 
 */
public class ShapeBorderController extends AShapePropertyCustomiser implements Initializable, IWidgetCreator {
	/** The field which allows to change shapes thickness. */
	@FXML protected Spinner<Double> thicknessField;

	/** Allows to set the colour of the borders of shapes. */
	@FXML protected ColorPicker lineColButton;

	/** Allows to change the style of the borders */
	@FXML protected ComboBox<ELineStyle> lineCB;

	/** Allows to select the position of the borders of the shape. */
	@FXML protected ComboBox<EBorderPos> bordersPosCB;

	/** Allows to change the angle of the round corner. */
	@FXML protected Spinner<Double> frameArcField;

	@FXML protected ImageView thicknessPic;
	@FXML protected ImageView frameArcPic;
	@FXML protected TitledPane linePane;

	/**
	 * Creates the instrument.
	 */
	public ShapeBorderController() {
		super();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		linePane.managedProperty().bind(linePane.visibleProperty());

		thicknessPic.visibleProperty().bind(thicknessField.visibleProperty());
		frameArcPic.visibleProperty().bind(frameArcField.visibleProperty());

		Map<EBorderPos, Image> cachePos = new HashMap<>();
		cachePos.put(EBorderPos.INTO, new Image("/res/doubleBoundary/double.boundary.into.png"));
		cachePos.put(EBorderPos.MID, new Image("/res/doubleBoundary/double.boundary.middle.png"));
		cachePos.put(EBorderPos.OUT, new Image("/res/doubleBoundary/double.boundary.out.png"));
		initComboBox(bordersPosCB, cachePos, EBorderPos.values());

		Map<ELineStyle, Image> cacheStyle = new HashMap<>();
		cacheStyle.put(ELineStyle.SOLID, new Image("/res/lineStyles/lineStyle.none.png"));
		cacheStyle.put(ELineStyle.DASHED, new Image("/res/lineStyles/lineStyle.dashed.png"));
		cacheStyle.put(ELineStyle.DOTTED, new Image("/res/lineStyles/lineStyle.dotted.png"));
		initComboBox(lineCB, cacheStyle, ELineStyle.values());

		scrollOnSpinner(frameArcField);
		scrollOnSpinner(thicknessField);
	}

	@Override
	protected void update(final IGroup shape) {
		if(shape.isEmpty()) {
			setActivated(false);
		}
		else {
			setActivated(true);
			final boolean isTh = shape.isThicknessable();
			final boolean isStylable = shape.isLineStylable();
			final boolean isMvble = shape.isBordersMovable();
			final boolean isColor = shape.isColourable();
			final boolean supportRound = shape.isTypeOf(ILineArcProp.class);
			final boolean showPts = shape.isShowPtsable();

			thicknessField.setVisible(isTh);
			lineCB.setVisible(isStylable);
			bordersPosCB.setVisible(isMvble);
			frameArcField.setVisible(supportRound);
			lineColButton.setVisible(isColor);
			

			if(isColor) lineColButton.setValue(shape.getLineColour().toJFX());
			if(isTh) thicknessField.getValueFactory().setValue(shape.getThickness());
			if(isStylable) lineCB.getSelectionModel().select(shape.getLineStyle());
			if(isMvble) bordersPosCB.getSelectionModel().select(shape.getBordersPosition());
			if(supportRound) frameArcField.getValueFactory().setValue(shape.getLineArc());
			
		}
	}

	@Override
	protected void setWidgetsVisible(final boolean visible) {
		linePane.setVisible(visible);
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		addInteractor(new Spinner4Pencil(this, thicknessField, EShapeProperties.LINE_THICKNESS, false));
		addInteractor(new Spinner4Selection(this, thicknessField, EShapeProperties.LINE_THICKNESS, false));
		addInteractor(new Spinner4Pencil(this, frameArcField, EShapeProperties.ROUND_CORNER_VALUE, false));
		addInteractor(new Spinner4Selection(this, frameArcField, EShapeProperties.ROUND_CORNER_VALUE, false));
		addInteractor(new List4Pencil(this, bordersPosCB, EShapeProperties.BORDER_POS));
		addInteractor(new List4Pencil(this, lineCB, EShapeProperties.LINE_STYLE));
		addInteractor(new List4Selection(this, bordersPosCB, EShapeProperties.BORDER_POS));
		addInteractor(new List4Selection(this, lineCB, EShapeProperties.LINE_STYLE));
		addInteractor(new ColourPicker4Selection(this, lineColButton, EShapeProperties.COLOUR_LINE));
		addInteractor(new ColourPicker4Pencil(this, lineColButton, EShapeProperties.COLOUR_LINE));
		
	}
}
