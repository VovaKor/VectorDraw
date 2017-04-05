
package vectordraw.models.impl;

import java.util.Objects;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IPolyline;
import vectordraw.models.interfaces.shape.IShape;

/**
 * An implementation of a polyline.
 */
class VDPolyline extends VDPolygon implements IPolyline {
	
	VDPolyline() {
		super();
	}

	VDPolyline(final IPoint point, final IPoint point2) {
		this();
		addPoint(Objects.requireNonNull(point));
		addPoint(Objects.requireNonNull(point2));
	}

	@Override
	public void copy(final IShape sh) {
		super.copy(sh);		
	}

	@Override
	public boolean isFillable() {
		return getNbPoints() > 2;
	}

	@Override
	public boolean shadowFillsShape() {
		return false;
	}

}
