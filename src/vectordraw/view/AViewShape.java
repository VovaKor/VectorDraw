
package vectordraw.view;

import javafx.scene.Group;
import vectordraw.models.interfaces.shape.IShape;


/**
 * The base class of a JFX shape view.
 * @param <S> The type of the model.
 */
public abstract class AViewShape<S extends IShape> extends Group {
	/** The model of the view. */
	protected final  S model;

	/**
	 * Creates the view.
	 * @param sh The model.
	 */
	protected AViewShape(final  S sh) {
		super();
		model = sh;

		setUserData(model);
		setFocusTraversable(false);
	}

	/**
	 * Flushes the view.
	 */
	public void flush() {
		setUserData(null);
		getChildren().clear();
		// Should be overridden to flush the bindings.
	}

	public  S getModel() {
		return model;
	}
}
