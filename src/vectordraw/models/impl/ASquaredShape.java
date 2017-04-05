
package vectordraw.models.impl;

import java.awt.geom.Rectangle2D;
import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.ISquaredShape;
import vectordraw.models.interfaces.shape.EPosition;

/**
 * A model of a squared shape.
 */
abstract class ASquaredShape extends APositionShape implements ISquaredShape {

	ASquaredShape(final IPoint tl, final double width) {
		super(tl);

		if(!(MathUtils.INST.isValidPt(tl) && width > 0 && MathUtils.INST.isValidCoord(width))) throw new IllegalArgumentException();

		points.add(ShapeFactory.INST.createPoint(tl));
		points.add(ShapeFactory.INST.createPoint(tl));
		points.add(ShapeFactory.INST.createPoint(tl));
		setWidth(width);
	}

	@Override
	public void scale(final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		if(bound != null && pos != null) {
			scaleSetPointsWithRatio(points, prevWidth, prevHeight, pos, bound);
		}
	}

	@Override
	public void setWidth(final double width) {
		if(MathUtils.INST.isValidCoord(width) && width > 0) {
			final IPoint pt = points.get(points.size() - 1);
			final double xPos = pt.getX() + width;
			final double yPos = pt.getY() - width;
			points.get(1).setX(xPos);
			points.get(2).setX(xPos);
			points.get(0).setY(yPos);
			points.get(1).setY(yPos);
		}
	}

	@Override
	public double getHeight() {
		return getWidth();
	}

	@Override
	public double getWidth() {
		return points.get(1).getX() - points.get(0).getX();
	}

	@Override
	public boolean isBordersMovable() {
		return true;
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