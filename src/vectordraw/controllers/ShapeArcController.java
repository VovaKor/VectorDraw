
package vectordraw.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import vectordraw.actions.ModifyPencilParameter;
import vectordraw.actions.shape.ModifyShapeProperty;
import vectordraw.actions.shape.EShapeProperties;
import vectordraw.actions.shape.AShapePropertyAction;
import vectordraw.models.interfaces.prop.IArcProp;
import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.IGroup;
import org.malai.javafx.instrument.library.ToggleButtonInteractor;

/**
 * This instrument modifies arc parameters.
 * 
 */
public class ShapeArcController extends AShapePropertyCustomiser implements Initializable {
	/** The toggle button that selects the arc style. */
	@FXML protected ToggleButton arcB;

	/** The toggle button that selects the wedge style. */
	@FXML protected ToggleButton wedgeB;

	/** The toggle button that selects the chord style. */
	@FXML protected ToggleButton chordB;

	@FXML protected TitledPane mainPane;

	/**
	 * Creates the instrument.
	 */
	public ShapeArcController() {
		super();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		mainPane.managedProperty().bind(mainPane.visibleProperty());
		
	}

	@Override
	protected void setWidgetsVisible(final boolean visible) {
		mainPane.setVisible(visible);
	}

	@Override
	protected void update(final IGroup shape) {
		if(shape.isTypeOf(IArcProp.class)) {
			final EArcStyle type = shape.getArcStyle();
			arcB.setSelected(type == EArcStyle.ARC);
			wedgeB.setSelected(type == EArcStyle.WEDGE);
			chordB.setSelected(type == EArcStyle.CHORD);
			
			setActivated(true);
		}else {
			setActivated(false);
		}
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		
		addInteractor(new Button2SelectionArcStyle(this));
		addInteractor(new Button2PencilArcStyle(this));
	}

	private abstract static class Button2ArcStyle<T extends AShapePropertyAction> extends ToggleButtonInteractor<T, ShapeArcController> {
		Button2ArcStyle(final ShapeArcController ins, final Class<T> act) throws InstantiationException, IllegalAccessException {
			super(ins, act, ins.arcB, ins.chordB, ins.wedgeB);
		}

		@Override
		public void initAction() {
			final ToggleButton button = interaction.getWidget();
			final EArcStyle style;

			if(button == instrument.arcB) {
				style = EArcStyle.ARC;                             
			}
			else {
				if(button == instrument.chordB) {
					style = EArcStyle.CHORD;                                      
				}
				else {
					style = EArcStyle.WEDGE;                                       
				}
			}

			action.setProperty(EShapeProperties.ARC_STYLE);
			action.setValue(style);
		}
	}

	private static class Button2PencilArcStyle extends Button2ArcStyle<ModifyPencilParameter> {
		Button2PencilArcStyle(final ShapeArcController ins) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyPencilParameter.class);
		}

		@Override
		public void initAction() {
			super.initAction();
                        
			action.setPencil(instrument.pencil);
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.pencil.isActivated();
		}
	}

	private static class Button2SelectionArcStyle extends Button2ArcStyle<ModifyShapeProperty> {
		Button2SelectionArcStyle(final ShapeArcController ins) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyShapeProperty.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.setGroup(instrument.drawing.getSelectedShapes().duplicateDeep(false));
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.hand.isActivated();
		}
	}
}
