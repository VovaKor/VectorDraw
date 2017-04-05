package vectordraw.controllers;

import vectordraw.models.EShapeTypes;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import vectordraw.actions.ModifyPencilStyle;
import vectordraw.view.Canvas;
import org.malai.action.Action;
import org.malai.javafx.action.library.ActivateInactivateInstruments;
import org.malai.javafx.instrument.JfxInstrument;
import org.malai.javafx.instrument.library.ToggleButtonInteractor;

/**
 * This instrument selects the pencil or the hand.
 */
public class ShapeTypesBarController extends JfxInstrument implements Initializable {
	/** The button that allows to select the instrument HandInstrument. */
	@FXML ToggleButton handB;

	/** The button that allows to select the instrument PencilInstrument to add rectangles. */
	@FXML ToggleButton recB;

	/** The button that allows to select the instrument PencilInstrument to add squares. */
	@FXML ToggleButton squareB;

	/** The button that allows to select the instrument PencilInstrument to add ellipses. */
	@FXML ToggleButton ellipseB;

	/** The button that allows to select the instrument PencilInstrument to add circles. */
	@FXML ToggleButton circleB;

	/** The button that allows to select the instrument PencilInstrument to add lines. */
	@FXML ToggleButton linesB;

	/** The button that allows to select the instrument PencilInstrument to add polygons. */
	@FXML ToggleButton polygonB;

	/** The button that allows to select the instrument PencilInstrument to add bezier curves. */
	@FXML ToggleButton bezierB;

	/** The button that allows to select the instrument PencilInstrument to add rhombuses. */
	@FXML ToggleButton rhombusB;

	/** The button that allows to select the instrument PencilInstrument to add triangles. */
	@FXML ToggleButton triangleB;

	/** The button that allows to select the instrument PencilInstrument to add arcs. */
	@FXML ToggleButton arcB;

	/** The instrument HandInstrument. */
	@Inject HandInstrument hand;

	/** The instrument PencilInstrument. */
	@Inject PencilInstrument pencil;

	/** The instrument that manages instruments that customise shapes and the pencil. */
	@Inject ShapePropertiesController shapePropertiesCustomiser;

	/** The instrument used to delete shapes. */
	@Inject ShapeDeleteController deleter;

	@Inject Canvas canvas;

	final Map<ToggleButton, EShapeTypes> button2EditingChoiceMap;


	/**
	 * Creates the instrument.
	*
	 */
	public ShapeTypesBarController() {
		super();
		button2EditingChoiceMap = new HashMap<>();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		button2EditingChoiceMap.put(arcB, EShapeTypes.OPEN_ARC);
		button2EditingChoiceMap.put(bezierB, EShapeTypes.BEZIER_CURVE);
		button2EditingChoiceMap.put(circleB, EShapeTypes.CIRCLE);
		button2EditingChoiceMap.put(ellipseB, EShapeTypes.ELLIPSE);
		button2EditingChoiceMap.put(linesB, EShapeTypes.LINES);
		button2EditingChoiceMap.put(polygonB, EShapeTypes.POLYGON);
		button2EditingChoiceMap.put(recB, EShapeTypes.RECT);
		button2EditingChoiceMap.put(rhombusB, EShapeTypes.RHOMBUS);
		button2EditingChoiceMap.put(squareB, EShapeTypes.SQUARE);
		button2EditingChoiceMap.put(triangleB, EShapeTypes.TRIANGLE);
		setActivated(true);
		handB.setSelected(true);
		hand.setActivated(true);
		pencil.setActivated(false);
		shapePropertiesCustomiser.update();
	}


	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		final List<Node> nodes = new ArrayList<>(button2EditingChoiceMap.keySet());
		nodes.add(handB);
		
		addInteractor(new ButtonPressed2DefineStylePencil(this));
		addInteractor(new ButtonPressed2ActivateIns(this, nodes));
		
	}

	@Override
	public void setActivated(final boolean isActivated) {
		super.setActivated(isActivated);
		button2EditingChoiceMap.keySet().forEach(but -> but.setVisible(activated));
		handB.setVisible(activated);
		
	}


	@Override
	public void onActionDone(final Action action) {
		super.onActionDone(action);
		canvas.requestFocus();
	}

	private static class ButtonPressed2DefineStylePencil extends ToggleButtonInteractor<ModifyPencilStyle, ShapeTypesBarController> {
		ButtonPressed2DefineStylePencil(final ShapeTypesBarController ins) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyPencilStyle.class, new ArrayList<>(ins.button2EditingChoiceMap.keySet()));
		}

		@Override
		public void initAction() {
			action.setEditingChoice(instrument.button2EditingChoiceMap.get(interaction.getWidget()));
			action.setPencil(instrument.pencil);
		}
	}

	private static class ButtonPressed2ActivateIns extends ToggleButtonInteractor<ActivateInactivateInstruments, ShapeTypesBarController> {
		ButtonPressed2ActivateIns(final ShapeTypesBarController ins, final List<Node> nodes) throws InstantiationException, IllegalAccessException {
			super(ins, ActivateInactivateInstruments.class, nodes);
		}

		@Override
		public void initAction() {
			final ToggleButton button = interaction.getWidget();

			action.setActivateFirst(false);

			/* Selection of the instruments to activate/desactivate. */
			if(button == instrument.handB) {
				final boolean noSelection = instrument.hand.canvas.getDrawing().getSelectedShapes().isEmpty();
				action.addInstrumentToActivate(instrument.hand);

				if(!noSelection) action.addInstrumentToActivate(instrument.deleter);

				action.addInstrumentToInactivate(instrument.pencil);

				if(noSelection) {
					action.addInstrumentToInactivate(instrument.shapePropertiesCustomiser);
					
				}else {
					action.addInstrumentToActivate(instrument.shapePropertiesCustomiser);
					
				}
			}else {
				action.addInstrumentToInactivate(instrument.hand);
				action.addInstrumentToInactivate(instrument.deleter);
				action.addInstrumentToActivate(instrument.pencil);
				action.addInstrumentToActivate(instrument.shapePropertiesCustomiser);
			}
		}
	}

}
