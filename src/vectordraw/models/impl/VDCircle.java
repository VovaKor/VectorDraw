
package vectordraw.models.impl;

import vectordraw.models.interfaces.shape.ICircle;
import vectordraw.models.interfaces.shape.IPoint;

/**
 * An implementation of a circle.
 */
class VDCircle extends ASquaredShape implements ICircle {

	VDCircle(final IPoint pos, final double width) {
		super(pos, width);
	}

	@Override
	public IPoint getCenter() {
		return getGravityCentre();
	}

	@Override
	public double getRadius() {
		return getWidth() / 2.0;
	}
}
