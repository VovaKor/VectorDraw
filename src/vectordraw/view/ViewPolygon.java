package vectordraw.view;

import javafx.scene.shape.ClosePath;
import vectordraw.models.interfaces.shape.IPolygon;

public class ViewPolygon extends AViewPolyPoint<IPolygon> {
	/**
	 * Creates the view.
	 * @param sh The model.
	 */
	public ViewPolygon(final IPolygon sh) {
		super(sh);
		border.getElements().add(new ClosePath());
	}
}
