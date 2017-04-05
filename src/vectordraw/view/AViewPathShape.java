
package vectordraw.view;

import javafx.scene.shape.Path;
import vectordraw.models.interfaces.shape.ISingleShape;


/**
 * The JFX view for shapes that can be painted as a path.
 * 
 * @param <S> The type of the model.
*
*
 */
public abstract class AViewPathShape<S extends ISingleShape> extends AViewSingleShape<S, Path> {
	
    /**
	 * Creates the view.
	 * @param sh The model.
	 */
	protected AViewPathShape(final  S sh) {
		super(sh);
	}

	@Override
	protected  Path createJFXShape() {
		return new Path();
	}

	@Override
	public void flush() {
		border.getElements().clear();
		super.flush();
	}
}
