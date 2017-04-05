
package vectordraw.models.impl;

import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IPositionShape;

/**
 * A model of a shape that has a position.
 */
abstract class APositionShape extends AShapeImpl implements IPositionShape {
	/**
	 * Creates a VDPositionShape with a predefined point.
	 * @param pt The position. If pt is not valid, a point at position (0,0) is used.
	 */
	APositionShape(final IPoint pt) {
		super();
		points.add(MathUtils.INST.isValidPt(pt) ? pt : ShapeFactory.INST.createPoint());
	}


	@Override
	public void setPosition(final IPoint pt) {
		if(MathUtils.INST.isValidPt(pt)) setPosition(pt.getX(), pt.getY());
	}


	@Override
	public void setPosition(final double x, final double y) {
		if(MathUtils.INST.isValidPt(x, y)) {
			final IPoint pos = getPosition();
			translate(x - pos.getX(), y - pos.getY());
		}
	}


	@Override
	public void setX(final double x) {
		if(MathUtils.INST.isValidCoord(x)) translate(x - getPosition().getX(), 0);
	}


	@Override
	public void setY(final double y) {
		if(MathUtils.INST.isValidCoord(y)) translate(0, y - getPosition().getY());
	}


	@Override
	public IPoint getPosition() {
		return getBottomLeftPoint();
	}


	@Override
	public double getX() {
		return getPosition().getX();
	}


	@Override
	public double getY() {
		return getPosition().getY();
	}
}
