
package vectordraw.models.impl;

import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IPolygon;

/**
 * A model of a polygon.
 */
class VDPolygon extends AModifiablePointsShape implements IPolygon {
	/**
	 * Creates a model with no point.
	 */
	VDPolygon() {
		super();
	}

	/**
	 * Creates a model with two points.
	 * @throws IllegalArgumentException If one of the two points is null.
	 */
	VDPolygon(final IPoint point, final IPoint point2) {
		this();

		if(point == null || point2 == null) throw new IllegalArgumentException();

		addPoint(point);
		addPoint(point2);
	}

	@Override
	public boolean isDbleBorderable() {
		return true;
	}

	@Override
	public boolean isFillable() {
		return true;
	}

	@Override
	public boolean isInteriorStylable() {
		return true;
	}

	@Override
	public boolean isLineStylable() {
		return true;
	}

	@Override
	public boolean isShadowable() {
		return true;
	}

	@Override
	public boolean isThicknessable() {
		return true;
	}
}
