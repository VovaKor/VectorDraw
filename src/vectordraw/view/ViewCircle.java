
package vectordraw.view;

import javafx.beans.binding.Bindings;
import vectordraw.models.interfaces.shape.ICircle;


/**
 * The JFX shape view for circles.
 */
public class ViewCircle extends AViewEllipseBased<ICircle> {
	/**
	 * Creates the circle view.
	 * @param sh The model.
	 */
	public ViewCircle(final  ICircle sh) {
		super(sh);
		border.centerXProperty().bind(Bindings.createDoubleBinding(() -> model.getCenter().getX(), model.getPtAt(2).xProperty(), model.getPtAt(3).xProperty()));
		border.centerYProperty().bind(Bindings.createDoubleBinding(() -> model.getCenter().getY(), model.getPtAt(0).yProperty(), model.getPtAt(3).yProperty()));
		border.radiusXProperty().bind(Bindings.createDoubleBinding(model::getRadius, model.getPtAt(0).xProperty(), model.getPtAt(1).xProperty()));
		border.radiusYProperty().bind(Bindings.createDoubleBinding(model::getRadius, model.getPtAt(1).yProperty(), model.getPtAt(2).yProperty()));
	           
        }
}
