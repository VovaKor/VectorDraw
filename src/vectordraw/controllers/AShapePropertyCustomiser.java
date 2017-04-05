package vectordraw.controllers;

import com.google.inject.Inject;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import vectordraw.actions.ModifyPencilParameter;
import vectordraw.actions.shape.ModifyShapeProperty;
import vectordraw.actions.shape.EShapeProperties;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IDrawing;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.view.Canvas;
import org.malai.action.Action;
import org.malai.javafx.instrument.JfxInstrument;
import org.malai.javafx.instrument.library.CheckboxInteractor;
import org.malai.javafx.instrument.library.ColorPickerInteractor;
import org.malai.javafx.instrument.library.ComboBoxInteractor;
import org.malai.javafx.instrument.library.SpinnerInteractor;
import org.malai.undo.Undoable;

/**
 * This abstract instrument defines the base definition of instruments that customise shape properties.
*
 */
public abstract class AShapePropertyCustomiser extends JfxInstrument {
	/** The HandInstrument instrument. */
	@Inject protected HandInstrument hand;

	/** The PencilInstrument instrument. */
	@Inject protected PencilInstrument pencil;

	@Inject protected Canvas canvas;

	@Inject protected IDrawing drawing;


	/**
	 * Creates the instrument.
	 */
	protected AShapePropertyCustomiser() {
		super();
	}

	static void scrollOnSpinner(final Spinner<?> spinner) {
		spinner.setOnScroll(event -> {
			if(event.getDeltaY() < 0d) {
				spinner.decrement();
			}else if(event.getDeltaY() > 0d) {
				spinner.increment();
			}
		});
	}

	@Override
	public void onActionDone(final Action action) {
		update();
	}

	@Override
	public void onUndoableUndo(final Undoable undoable) {
		update();
	}

	@Override
	public void onUndoableRedo(final Undoable undoable) {
		update();
	}

	/**
	 * Updates the instrument and its widgets
	 * *
	 */
	public void update() {
		if(pencil.isActivated()) {
			update(ShapeFactory.INST.createGroup(pencil.createShapeInstance()));
		}
		else {
			update(drawing.getSelectedShapes());
		}
	}

	/**
	 * Updates the widgets using the given shape.
	 * @param shape The shape used to update the widgets. If null, nothing is performed.
	 * *
	 */
	protected abstract void update(final IGroup shape);

	/**
	 * Sets the widgets of the instrument visible or not.
	 * @param visible True: they are visible.
	 * *
	 */
	protected abstract void setWidgetsVisible(final boolean visible);

	@Override
	public void setActivated(final boolean act) {
		super.setActivated(act);
		setWidgetsVisible(act);
	}

	static class List4Pencil extends ComboBoxInteractor<ModifyPencilParameter, AShapePropertyCustomiser> {
		EShapeProperties prop;

		List4Pencil(final AShapePropertyCustomiser ins, final ComboBox<?> combo, EShapeProperties property) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyPencilParameter.class, combo);
			prop = property;
		}

		@Override
		public void initAction() {
			action.setPencil(instrument.pencil);
			action.setProperty(prop);
			action.setValue(interaction.getWidget().getSelectionModel().getSelectedItem());
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.pencil.isActivated();
		}
	}

	static class List4Selection extends ComboBoxInteractor<ModifyShapeProperty, AShapePropertyCustomiser> {
		EShapeProperties prop;

		List4Selection(final AShapePropertyCustomiser ins, final ComboBox<?> combo, EShapeProperties property) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyShapeProperty.class, combo);
			prop = property;
		}

		@Override
		public void initAction() {
			action.setGroup(instrument.canvas.getDrawing().getSelectedShapes().duplicateDeep(false));
			action.setProperty(prop);
			action.setValue(interaction.getWidget().getSelectionModel().getSelectedItem());
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.hand.isActivated();
		}
	}

	static class Spinner4Pencil extends SpinnerInteractor<ModifyPencilParameter, AShapePropertyCustomiser> {
		EShapeProperties prop;
		boolean angle;

		Spinner4Pencil(final AShapePropertyCustomiser ins, final Spinner<?> widget, EShapeProperties property, boolean isAngle) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyPencilParameter.class, widget);
			prop = property;
			angle = isAngle;
		}

		@Override
		public void initAction() {
			action.setProperty(prop);
			action.setPencil(instrument.pencil);
			if(angle) {
				action.setValue(Math.toRadians((Double) interaction.getWidget().getValue()));
			}
			else {
				action.setValue(interaction.getWidget().getValue());
			}
		}

		
		@Override
		public boolean isConditionRespected() {
			return instrument.pencil.isActivated();
		}
	}

	static class Spinner4Selection extends SpinnerInteractor<ModifyShapeProperty, AShapePropertyCustomiser> {
		EShapeProperties prop;
		boolean angle;

		Spinner4Selection(final AShapePropertyCustomiser ins, final Spinner<?> widget, EShapeProperties property, boolean isAngle) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyShapeProperty.class, widget);
			prop = property;
			angle = isAngle;
		}

		@Override
		public void initAction() {
			action.setProperty(prop);
			action.setGroup(instrument.canvas.getDrawing().getSelectedShapes().duplicateDeep(false));
			if(angle) {
				action.setValue(Math.toRadians((Double) interaction.getWidget().getValue()));
			}
			else {
				action.setValue(interaction.getWidget().getValue());
			}
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.hand.isActivated();
		}
	}

	static class ColourPicker4Pencil extends ColorPickerInteractor<ModifyPencilParameter, AShapePropertyCustomiser> {
		EShapeProperties prop;

		ColourPicker4Pencil(final AShapePropertyCustomiser ins, ColorPicker picker, EShapeProperties property) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyPencilParameter.class, picker);
			prop = property;
		}

		@Override
		public void initAction() {
			action.setValue(ShapeFactory.INST.createColorFX(interaction.getWidget().getValue()));
			action.setPencil(instrument.pencil);
			action.setProperty(prop);
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.pencil.isActivated();
		}
	}

	static class ColourPicker4Selection extends ColorPickerInteractor<ModifyShapeProperty, AShapePropertyCustomiser> {
		EShapeProperties prop;

		ColourPicker4Selection(final AShapePropertyCustomiser ins, ColorPicker picker, EShapeProperties property) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyShapeProperty.class, picker);
			prop = property;
		}

		@Override
		public void initAction() {
			action.setGroup(instrument.canvas.getDrawing().getSelectedShapes().duplicateDeep(false));
			action.setValue(ShapeFactory.INST.createColorFX(interaction.getWidget().getValue()));
			action.setProperty(prop);
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.hand.isActivated();
		}
	}

	static class Checkbox4Pencil extends CheckboxInteractor<ModifyPencilParameter, AShapePropertyCustomiser> {
		EShapeProperties prop;

		Checkbox4Pencil(final AShapePropertyCustomiser ins, ButtonBase widget, EShapeProperties property) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyPencilParameter.class, widget);
			prop = property;
		}

		@Override
		public void initAction() {
			action.setProperty(prop);
			action.setPencil(instrument.pencil);
			action.setValue(interaction.getWidget().isSelected());
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.pencil.isActivated();
		}
	}

	static class Checkbox4Selection extends CheckboxInteractor<ModifyShapeProperty, AShapePropertyCustomiser> {
		EShapeProperties prop;

		Checkbox4Selection(final AShapePropertyCustomiser ins, ButtonBase widget, EShapeProperties property) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyShapeProperty.class, widget);
			prop = property;
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.hand.isActivated();
		}

		@Override
		public void initAction() {
			action.setProperty(prop);
			action.setGroup(instrument.canvas.getDrawing().getSelectedShapes().duplicateDeep(false));
			action.setValue(interaction.getWidget().isSelected());
		}
	}
}
