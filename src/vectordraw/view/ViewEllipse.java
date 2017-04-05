
package vectordraw.view;

import javafx.beans.binding.Bindings;
import vectordraw.models.interfaces.shape.IEllipse;


/**
 * The JFX shape view for ellipses.
 */
public class ViewEllipse extends AViewEllipseBased<IEllipse> {
	/**
	 * Creates the ellipse view.
	 * @param sh The model.
	 */
	public ViewEllipse(final  IEllipse sh) {
		super(sh);
		border.centerXProperty().bind(Bindings.createDoubleBinding(() -> model.getCenter().getX(), model.getPtAt(2).xProperty(), model.getPtAt(3).xProperty()));
		border.centerYProperty().bind(Bindings.createDoubleBinding(() -> model.getCenter().getY(), model.getPtAt(0).yProperty(), model.getPtAt(3).yProperty()));
		border.radiusXProperty().bind(Bindings.createDoubleBinding(() -> model.getWidth() / 2d, model.getPtAt(0).xProperty(), model.getPtAt(1).xProperty()));
		border.radiusYProperty().bind(Bindings.createDoubleBinding(() -> model.getHeight() / 2d, model.getPtAt(1).yProperty(), model.getPtAt(2).yProperty()));
	}
}
