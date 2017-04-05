
package vectordraw.view;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import vectordraw.models.interfaces.shape.IRectangle;


/**
 * The JFX shape view for rectangles.
 */
public class ViewRectangle extends AViewRectangularBased<IRectangle> {
	final  ChangeListener<Bounds> lineArcUp = (observable, oldValue, newValue) -> lineArcCall.changed(model.frameArcProperty(), model.getLineArc(), model.getLineArc());


	/**
	 * Creates the rectangle view.
	 * @param sh The model.
	 */
	public ViewRectangle(final  IRectangle sh) {
		super(sh);
		border.xProperty().bind(model.getPtAt(0).xProperty());
		border.yProperty().bind(model.getPtAt(0).yProperty());
		border.widthProperty().bind(Bindings.createDoubleBinding(model::getWidth, model.getPtAt(0).xProperty(), model.getPtAt(1).xProperty()));
		border.heightProperty().bind(Bindings.createDoubleBinding(model::getHeight, model.getPtAt(0).yProperty(), model.getPtAt(3).yProperty()));
		model.frameArcProperty().addListener(lineArcCall);
		border.boundsInLocalProperty().addListener(lineArcUp);
		lineArcCall.changed(model.frameArcProperty(), model.getLineArc(), model.getLineArc());
	}

	@Override
	public void flush() {
		super.flush();
		model.frameArcProperty().removeListener(lineArcCall);
		border.boundsInLocalProperty().removeListener(lineArcUp);
	}
}
