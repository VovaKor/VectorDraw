
package vectordraw.view;

import javafx.beans.binding.Bindings;
import javafx.scene.shape.Ellipse;
import vectordraw.models.interfaces.shape.ISingleShape;


/**
 * A JFX abstract view to factorise code of views based on a JFX ellipse.
 */
abstract class AViewEllipseBased<T extends ISingleShape> extends AViewSingleShape<T, Ellipse> {
	/**
	 * Creates the view.
	 * @param sh The model.
	 */
	AViewEllipseBased(final  T sh) {
		super(sh);

		if(dblBorder != null) {
			dblBorder.centerXProperty().bind(border.centerXProperty());
			dblBorder.centerYProperty().bind(border.centerYProperty());
			dblBorder.radiusXProperty().bind(Bindings.subtract(border.radiusXProperty(), getDbleBorderGap()));
			dblBorder.radiusYProperty().bind(Bindings.subtract(border.radiusYProperty(), getDbleBorderGap()));
		}

		if(shadow != null) {
			shadow.centerXProperty().bind(border.centerXProperty());
			shadow.centerYProperty().bind(border.centerYProperty());
			shadow.radiusXProperty().bind(border.radiusXProperty());
			shadow.radiusYProperty().bind(border.radiusYProperty());
		}
	}

	private static void unbindEll(Ellipse sh) {
		if(sh != null) {
			sh.centerXProperty().unbind();
			sh.centerYProperty().unbind();
			sh.radiusXProperty().unbind();
			sh.radiusYProperty().unbind();
		}
	}

	@Override
	protected Ellipse createJFXShape() {
		return new Ellipse();
	}

	@Override
	public void flush() {
		unbindEll(border);
		unbindEll(dblBorder);
		unbindEll(shadow);
	}
}
