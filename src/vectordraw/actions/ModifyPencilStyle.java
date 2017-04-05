
package vectordraw.actions;

import vectordraw.models.EShapeTypes;
import vectordraw.controllers.PencilInstrument;
import org.malai.action.ActionImpl;

/**
 * This action allows to set the kind of shape that the pencil must draw.
 */
public class ModifyPencilStyle extends ActionImpl {
	/** The pencil to set. */
	protected PencilInstrument pencil;

	/** The new editing choice to set. */
	protected EShapeTypes editingChoice;


	@Override
	protected void doActionBody() {
		pencil.setCurrentChoice(editingChoice);
	}

	@Override
	public boolean canDo() {
		return pencil != null && editingChoice != null && pencil.getCurrentChoice() != editingChoice;
	}

	@Override
	public boolean isRegisterable() {
		return false;
	}

	/**
	 * Sets the pencil to parameterise.
	 * @param pen The pencil.
	 * *
	 */
	public void setPencil(final PencilInstrument pen) {
		pencil = pen;
	}

	/**
	 * Sets the new editing choice of the pencil.
	 * @param choice The new editing choice (can be null).
	 * *
	 */
	public void setEditingChoice(final EShapeTypes choice) {
		editingChoice = choice;
	}
}
