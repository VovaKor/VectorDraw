package vectordraw.view;

import vectordraw.models.interfaces.shape.IPolyline;

public class ViewPolyline extends AViewPolyPoint<IPolyline> {
	/**
	 * Creates the view.
	 * @param sh The model.
	 */
	public ViewPolyline(final IPolyline sh) {
		super(sh);
	}
}
