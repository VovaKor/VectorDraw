
package vectordraw.models.impl;

import vectordraw.util.MathUtils;
import vectordraw.models.interfaces.shape.IModifiablePointsShape;
import vectordraw.models.interfaces.shape.IPoint;

/**
 * A model of a shape that contains points that can be modified.
 */
abstract class AModifiablePointsShape extends AShapeImpl implements IModifiablePointsShape {
	/**
	 * Creates the shape.
	 */
	AModifiablePointsShape() {
		super();
	}


	@Override
	public void rotate(final IPoint point, final double angle) {
		setRotationAngle(point, angle);
	}


	public void setRotationAngle(final IPoint gc, final double angle) {
		if(MathUtils.INST.isValidCoord(angle)) {
			final double diff = angle - this.rotationAngle;
			final IPoint gc2 = gc == null ? getGravityCentre() : gc;

			super.setRotationAngle(angle);
			points.forEach(pt -> pt.setPoint(pt.rotatePoint(gc2, diff)));
		}
	}


	@Override
	public void setRotationAngle(final double angle) {
		setRotationAngle(null, angle);
	}


	@Override
	public boolean setPoint(final IPoint p, final int position) {
		return p != null && setPoint(p.getX(), p.getY(), position);
	}


	@Override
	public boolean setPoint(final double x, final double y, final int position) {
		if(!MathUtils.INST.isValidPt(x, y) || position < -1 || position > points.size() || points.isEmpty()) return false;

		final IPoint p = position == -1 ? points.get(points.size() - 1) : points.get(position);
		p.setPoint(x, y);

		return true;
	}


	@Override
	public boolean removePoint(final IPoint pt) {
		if(pt == null) return false;
		final int ind = points.indexOf(pt);
		return ind != -1 && removePoint(ind) != null;
	}


	@Override
	public IPoint removePoint(final int position) {
		if(position >= -1 && position < points.size()) return points.remove(position == -1 ? points.size() - 1 : position);
		return null;
	}


	@Override
	public IPoint replacePoint(final IPoint pt, final int position) {
		if(!MathUtils.INST.isValidPt(pt) || points.contains(pt) || position < -1 || position > points.size()) return null;

		final IPoint pRemoved = points.remove(position == -1 ? points.size() - 1 : position);

		if(position == -1 || points.isEmpty()) points.add(pt);
		else points.add(position, pt);

		return pRemoved;
	}


	@Override
	public void addPoint(final IPoint pt) {
		addPoint(pt, -1);
	}


	@Override
	public void addPoint(final IPoint pt, final int position) {
		if(MathUtils.INST.isValidPt(pt) && position >= -1 && position <= points.size())
			if(position == -1 || position == points.size()) points.add(pt);
			else points.add(position, pt);
	}
}
