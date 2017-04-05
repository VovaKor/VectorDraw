/*
*
 *
*
*
*
*
*
*
 */
package vectordraw.models.impl;

import java.awt.geom.Line2D;
import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.ILine;
import vectordraw.models.interfaces.shape.IPoint;

/**
 * A model of a line (not a shape).
 */
class VDLine extends Line2D.Double implements ILine {
	private static final long serialVersionUID = 1L;

	/** The director coefficient of the line (y=ax+b). */
	private double a;
	/** y=ax+b. */
	private double b;


	/**
	 * Constructs a line from the specified coordinates.
	 * @param x1 the X coordinate of the start point.
	 * @param y1 the Y coordinate of the start point.
	 * @param x2 the X coordinate of the end point.
	 * @param y2 the Y coordinate of the end point.
	 * @throws IllegalArgumentException If one of the given coordinate is not valid.
	 */
	VDLine(final double x1, final double y1, final double x2, final double y2) {
		this(ShapeFactory.INST.createPoint(x1, y1), ShapeFactory.INST.createPoint(x2, y2));
	}

	/**
	 * Creates a line by creating a second point with:
	 * @param b y = ax+ b
	 * @param p1 The first point.
	 * @throws IllegalArgumentException If one of the given parameter is not valid.
	 */
	VDLine(final double b, final IPoint p1) {
		this(p1, ShapeFactory.INST.createPoint(0.0, b));
	}

	/**
	 * Constructs a line from the specified <code>Point2D</code> objects.
	 * @param p1 the start <code>Point2D</code> of this line segment.
	 * @param p2 the end <code>Point2D</code> of this line segment.
	 * @throws IllegalArgumentException If one of the given points is not valid.
	 */
	VDLine(final IPoint p1, final IPoint p2) {
		super();

		if(!MathUtils.INST.isValidPt(p1) || !MathUtils.INST.isValidPt(p2)) throw new IllegalArgumentException();

		setP1(p1);
		setP2(p2);
		updateAandB();
	}

	@Override
	public void setLine(final double x1, final double y1, final double x2, final double y2) {
		if(MathUtils.INST.isValidPt(x1, y1) && MathUtils.INST.isValidPt(x2, y2)) {
			super.setLine(x1, y1, x2, y2);
			updateAandB();
		}
	}


	@Override
	public boolean isInSegment(final IPoint pt) {
		if(pt == null) return false;

		final double minX = Math.min(x1, x2);
		final double maxX = Math.max(x1, x2);
		final double minY = Math.min(y1, y2);
		final double maxY = Math.max(y1, y2);
		final double x = pt.getX();
		final double y = pt.getY();

		if(isHorizontalLine()) return MathUtils.INST.equalsDouble(y, minY) && x >= minX && x <= maxX;
		if(isVerticalLine()) return MathUtils.INST.equalsDouble(x, minX) && y >= minY && y <= maxY;
		return y >= minY && y <= maxY && x >= minX && x <= maxX && MathUtils.INST.equalsDouble(y, getA() * x + getB());
	}


	@Override
	public void updateAandB() {
		if(isVerticalLine()) {
			a = java.lang.Double.NaN;
			b = java.lang.Double.NaN;
		}else {
			a = (y1 - y2) / (x1 - x2);
			b = y1 - a * x1;
		}
	}


	@Override
	public IPoint[] findPoints(final IPoint p, final double distance) {
		return p == null ? null : findPoints(p.getX(), p.getY(), distance);
	}


	@Override
	public IPoint[] findPoints(final double x, final double y, final double distance) {
		if(!MathUtils.INST.isValidPt(x, y) || !MathUtils.INST.isValidCoord(distance)) return null;

		if(MathUtils.INST.equalsDouble(distance, 0.)) {
			final IPoint[] sol = new VDPoint[1];
			sol[0] = ShapeFactory.INST.createPoint(x, y);

			return sol;
		}

		if(isVerticalLine()) {
			if(isHorizontalLine())// The line is a point. So no position can be computed.
				return null;

			final IPoint[] sol = new VDPoint[2];
			sol[0] = ShapeFactory.INST.createPoint(x, y - distance);
			sol[1] = ShapeFactory.INST.createPoint(x, y + distance);

			return sol;
		}

		final double aLine = a * a + 1.0;
		final double bLine = -2.0 * (x + y * a - a * b);
		final double cLine = b * b - 2.0 * y * b + y * y + x * x - distance * distance;
		final double delta = bLine * bLine - 4.0 * aLine * cLine;

		if(delta > 0.0) {
			final double x1b;
			final double x2b;
			final double y1b;
			final double y2b;
			final IPoint[] sol = new VDPoint[2];

			x1b = (-bLine + Math.sqrt(delta)) / (2 * aLine);
			x2b = (-bLine - Math.sqrt(delta)) / (2 * aLine);
			y1b = a * x1b + b;
			y2b = a * x2b + b;
			sol[0] = ShapeFactory.INST.createPoint(x1b, y1b);
			sol[1] = ShapeFactory.INST.createPoint(x2b, y2b);

			return sol;
		}else if(MathUtils.INST.equalsDouble(delta, 0.0)) {
			final double x2b;
			final double y2b;
			final IPoint[] sol = new VDPoint[1];

			x2b = -bLine / 2 * aLine;
			y2b = a * x2b + b;
			sol[0] = ShapeFactory.INST.createPoint(x2b, y2b);

			return sol;
		}else return null;
	}


	@Override
	public ILine getPerpendicularLine(final IPoint pt) {
		/*//TODO
		if(isVerticalLine())
			return new Line(new Point2D.Double(-10000, y), new Point2D.Double(10000, y));

		if(isHorizontalLine())
			return new Line(new Point2D.Double(x, -10000), new Point2D.Double(x, 10000));

		final double a2 = -1./getA();
		final double b2 = y-a2*x;

		return new Line(new Point2D.Double(x, y), new Point2D.Double(-b2/a2, 0.));
		 */
		if(!MathUtils.INST.isValidPt(pt)) return null;

		if(isVerticalLine())//FIXME must always create a perpendicular line + add test
			return MathUtils.INST.equalsDouble(pt.getX(), x1) ? ShapeFactory.INST.createLine(pt.getY(), ShapeFactory.INST.createPoint(pt)) : null;

		if(MathUtils.INST.equalsDouble(pt.getX(), 0.0)) {
			final IPoint pt3 = ShapeFactory.INST.createPoint(getPoint2());
			final IPoint pt2 = pt3.rotatePoint(pt, Math.PI / 2.0);

			return ShapeFactory.INST.createLine(pt2, pt);
		}

		if(MathUtils.INST.equalsDouble(a, 0.0)) return ShapeFactory.INST.createLine(pt.getX(), pt.getY(), pt.getX(), pt.getY() - 10.0);

		final double a2 = -1.0 / a;

		return ShapeFactory.INST.createLine(pt.getY() - a2 * pt.getX(), pt);
	}


	@Override
	public boolean isVerticalLine() {
		return MathUtils.INST.equalsDouble(x1, x2);
	}


	@Override
	public boolean isHorizontalLine() {
		return MathUtils.INST.equalsDouble(y1, y2);
	}


	@Override
	public IPoint getIntersection(final ILine l) {
		if(l == null) return null;
		if(MathUtils.INST.equalsDouble(a, l.getA(), 0.00000000001)) return null;

		final boolean verticalLine1 = isVerticalLine();
		final boolean verticalLine2 = l.isVerticalLine();
		final double x;
		final double y;

		if(verticalLine2) {
			if(verticalLine1)// The two lines a parallels
				return null;

			if(l.isHorizontalLine())// Points of the line l are equal.
				return null;

			if(isHorizontalLine()) {
				x = l.getX1();
				y = getY1();
			}else {
				y = a * l.getX1() + b;
				x = (y - b) / a;
			}
		}else {
			final double la = l.getA();
			final double lb = l.getB();

			if(verticalLine1) {
				if(l.isHorizontalLine()) {
					x = getX1();
					y = l.getY1();
				}else {
					y = la * getX1() + lb;
					x = (y - lb) / la;
				}
			}else {
				x = (b - lb) / (la - a);
				y = a * x + b;
			}
		}

		return ShapeFactory.INST.createPoint(x, y);
	}


	@Override
	public IPoint getIntersectionSegment(final ILine l) {
		final IPoint p = getIntersection(l);

		if(p == null) return null;

		final double px = p.getX();
		final double py = p.getY();
		final IPoint tl = getTopLeftPoint();
		final IPoint br = getBottomRightPoint();
		final IPoint tl2 = l.getTopLeftPoint();
		final IPoint br2 = l.getBottomRightPoint();

		if(px >= tl.getX() && px <= br.getX() && py >= tl.getY() && py <= br.getY() && px >= tl2.getX() && px <= br2.getX() && py >= tl2.getY() && py <= br2.getY())
			return p;

		return null;
	}


	@Override
	public IPoint getTopLeftPoint() {
		final IPoint pt1 = getPoint1();
		final IPoint pt2 = getPoint2();

		return ShapeFactory.INST.createPoint(pt1.getX() < pt2.getX() ? pt1.getX() : pt2.getX(), pt1.getY() < pt2.getY() ? pt1.getY() : pt2.getY());
	}


	@Override
	public IPoint getBottomRightPoint() {
		final IPoint pt1 = getPoint1();
		final IPoint pt2 = getPoint2();

		return ShapeFactory.INST.createPoint(pt1.getX() < pt2.getX() ? pt2.getX() : pt1.getX(), pt1.getY() < pt2.getY() ? pt2.getY() : pt1.getY());
	}


	@Override
	public double getA() {
		return a;
	}


	@Override
	public double getB() {
		return b;
	}


	@Override
	public IPoint getPoint1() {
		return ShapeFactory.INST.createPoint(x1, y1);
	}


	@Override
	public IPoint getPoint2() {
		return ShapeFactory.INST.createPoint(x2, y2);
	}


	@Override
	public void setP1(final IPoint pt) {
		if(MathUtils.INST.isValidPt(pt)) {
			this.x1 = pt.getX();
			this.y1 = pt.getY();
		}
	}


	@Override
	public void setP2(final IPoint pt) {
		if(MathUtils.INST.isValidPt(pt)) {
			this.x2 = pt.getX();
			this.y2 = pt.getY();
		}
	}


	@Override
	public void setX1(final double x1) {
		if(MathUtils.INST.isValidCoord(x1)) this.x1 = x1;
	}


	@Override
	public void setX2(final double x2) {
		if(MathUtils.INST.isValidCoord(x2)) this.x2 = x2;
	}


	@Override
	public void setY1(final double y1) {
		if(MathUtils.INST.isValidCoord(y1)) this.y1 = y1;
	}


	@Override
	public void setY2(final double y2) {
		if(MathUtils.INST.isValidCoord(y2)) this.y2 = y2;
	}


	@Override
	public double getLineAngle() {
		if(isHorizontalLine()) return 0.;

		if(isVerticalLine()) return Math.PI / 2.;

		return Math.atan(getA());
	}


	@Override
	public String toString() {
		return "LLine [a=" + a + ", b=" + b + ", x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]";  //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	}
}
