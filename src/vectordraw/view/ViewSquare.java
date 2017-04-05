
package vectordraw.view;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import vectordraw.models.interfaces.shape.ISquare;


/**
 * The JFX shape view for squares.
 */
public class ViewSquare extends AViewRectangularBased<ISquare> {
	final  ChangeListener<Bounds> lineArcUp = (observable, oldValue, newValue) -> lineArcCall.changed(model.frameArcProperty(), model.getLineArc(), model.getLineArc());

	/**
	 * Creates the square view.
	 * @param sh The model.
	 */
	public ViewSquare(final  ISquare sh) {
		super(sh);
		border.xProperty().bind(model.getPtAt(0).xProperty());
		border.yProperty().bind(model.getPtAt(0).yProperty());
		border.widthProperty().bind(Bindings.createDoubleBinding(model::getWidth, model.getPtAt(0).xProperty(), model.getPtAt(1).xProperty()));
		border.heightProperty().bind(Bindings.createDoubleBinding(model::getWidth, model.getPtAt(0).xProperty(), model.getPtAt(1).xProperty()));
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
