
package vectordraw.actions.shape;

import org.malai.action.library.ModifyValue;

/**
 * This action modifies a shape property of an object.
 */
public abstract class AShapePropertyAction extends ModifyValue {
	/** The property to set. */
	protected EShapeProperties property;


	/**
	 * Creates and initialises the action.
	 * *
	 */
	protected AShapePropertyAction() {
		super();
	}


	@Override
	public void flush() {
		super.flush();
		property = null;
	}


	@Override
	protected boolean isValueMatchesProperty() {
		return isPropertySupported() && property.isValueValid(value);
	}


	/**
	 * @return True if the property to modify is supported.
	 * *
	 */
	protected boolean isPropertySupported() {
		return property != null;
	}


	/**
	 * Defines the property to modify
	 * @param prop The property to modify.
	 * *
	 */
	public void setProperty(final EShapeProperties prop) {
		property = prop;
	}
}
