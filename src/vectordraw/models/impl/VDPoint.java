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

import java.awt.geom.Point2D;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IPoint;


import static java.lang.Math.PI;
import static java.lang.Math.atan;

/**
 * Defines a model of a point. Not a shape.
 */
class VDPoint implements IPoint {
	private final  DoubleProperty x;
	private final  DoubleProperty y;

	/**
	 * Creates a Point2D with coordinates (0, 0).
	 */
	VDPoint() {
		this(0d, 0d);
	}

	/**
	 * Creates a point from a IPoint
	 * @param pt The IPoint, if null the default value (0,0) will be used.
	 */
	VDPoint(final IPoint pt) {
		this(pt == null ? 0d : pt.getX(), pt == null ? 0d : pt.getY());
	}

	/**
	 * Creates a Point2D with the specified coordinates.
	 * @param xCoord The X-coordinate to set.
	 * @param yCoord The Y-coordinate to set.
	 */
	VDPoint(final double xCoord, final double yCoord) {
		super();
		x = new SimpleDoubleProperty(xCoord);
		y = new SimpleDoubleProperty(yCoord);
	}

	@Override
	public double computeAngle(final IPoint pt) {
		if(!MathUtils.INST.isValidPt(pt)) return java.lang.Double.NaN;

		double angle;
		final double x2 = pt.getX() - getX();
		final double y2 = pt.getY() - getY();

		if(MathUtils.INST.equalsDouble(x2, 0d)) {
			angle = Math.PI / 2d;

			if(y2 < 0d) {
				angle = Math.PI * 2d - angle;
			}
		}else {
			angle = x2 < 0d ? Math.PI - atan(-y2 / x2) : atan(y2 / x2);
		}

		return angle;
	}

	@Override
	public IPoint zoom(final double zoomLevel) {
		return ShapeFactory.INST.createPoint(getX() * zoomLevel, getX() * zoomLevel);
	}

	@Override
	public double computeRotationAngle(final IPoint pt1, final IPoint pt2) {
		if(!MathUtils.INST.isValidPt(pt1) || !MathUtils.INST.isValidPt(pt2)) {
			return Double.NaN;
		}

		final double thetaOld = computeAngle(pt1);
		final double thetaNew = computeAngle(pt2);

		return thetaNew - thetaOld;
	}

	@Override
	public IPoint centralSymmetry(final IPoint centre) {
		return rotatePoint(centre, Math.PI);
	}

	@Override
	public IPoint rotatePoint(final IPoint gravityC, final double theta) {
		if(!MathUtils.INST.isValidPt(gravityC) || !MathUtils.INST.isValidCoord(theta)) {
			return null;
		}

		final IPoint pt = ShapeFactory.INST.createPoint();
		final double cosTheta;
		final double sinTheta;
		double angle = theta;
		final double gx = gravityC.getX();
		final double gy = gravityC.getY();

		if(angle < 0.) {
			angle = 2. * PI + angle;
		}

		angle %= 2. * PI;

		if(MathUtils.INST.equalsDouble(angle, 0.)) {
			return new VDPoint(this);
		}

		if(MathUtils.INST.equalsDouble(angle - PI / 2., 0.)) {
			cosTheta = 0.;
			sinTheta = 1.;
		}else if(MathUtils.INST.equalsDouble(angle - PI, 0.)) {
			cosTheta = -1.;
			sinTheta = 0.;
		}else if(MathUtils.INST.equalsDouble(angle - 3. * PI / 2., 0.)) {
			cosTheta = 0.;
			sinTheta = -1.;
		}else {
			cosTheta = Math.cos(angle);
			sinTheta = Math.sin(angle);
		}

		pt.setX(cosTheta * (getX() - gx) - sinTheta * (getY() - gy) + gx);
		pt.setY(sinTheta * (getX() - gx) + cosTheta * (getY() - gy) + gy);

		return pt;
	}

	@Override
	public boolean equals(final IPoint p, final double gap) {
		return !(!MathUtils.INST.isValidCoord(gap) || !MathUtils.INST.isValidPt(p)) && MathUtils.INST.equalsDouble(getX(), p.getX(), gap) &&
			MathUtils.INST.equalsDouble(getY(), p.getY(), gap);
	}

	@Override
	public IPoint getMiddlePoint(final IPoint p) {
		return p == null ? null : ShapeFactory.INST.createPoint((getX() + p.getX()) / 2., (getY() + p.getY()) / 2d);
	}

	@Override
	public void translate(final double tx, final double ty) {
		if(MathUtils.INST.isValidPt(tx, ty)) setPoint(getX() + tx, getY() + ty);
	}

	@Override
	public IPoint horizontalSymmetry(final IPoint origin) {
		if(!MathUtils.INST.isValidPt(origin)) return null;

		return ShapeFactory.INST.createPoint(2d * origin.getX() - getX(), getY());
	}

	@Override
	public IPoint verticalSymmetry(final IPoint origin) {
		if(!MathUtils.INST.isValidPt(origin)) return null;

		return ShapeFactory.INST.createPoint(getX(), 2d * origin.getY() - getY());
	}

	@Override
	public void setPoint(final double newX, final double newY) {
		setX(newX);
		setY(newY);
	}

	@Override
	public void setX(final double newX) {
		if(MathUtils.INST.isValidCoord(newX)) x.set(newX);
	}

	@Override
	public void setY(final double newY) {
		if(MathUtils.INST.isValidCoord(newY)) y.set(newY);
	}

	@Override
	public void setPoint(final IPoint pt) {
		if(pt != null) setPoint(pt.getX(), pt.getY());
	}

	@Override
	public double distance(final IPoint pt) {
		return pt == null ? java.lang.Double.NaN : distance(pt.getX(), pt.getY());
	}

	@Override
	public Point2D.Double toPoint2D() {
		return new Point2D.Double(x.getValue(), y.getValue());
	}

	@Override
	public Point3D toPoint3D() {
		return new Point3D(x.getValue(), y.getValue(), 0d);
	}

	@Override
	public void setPoint2D(final Point2D pt) {
		if(pt != null) setPoint(pt.getX(), pt.getY());
	}

	@Override
	public IPoint substract(final IPoint pt) {
		if(pt == null) return null;
		return ShapeFactory.INST.createPoint(getX() - pt.getX(), getY() - pt.getY());
	}

	@Override
	public IPoint normalise() {
		final double magnitude = magnitude();
		return ShapeFactory.INST.createPoint(getX() / magnitude, getY() / magnitude);
	}

	@Override
	public double magnitude() {
		return Math.sqrt(getX() * getX() + getY() * getY());
	}

	@Override
	public IPoint add(final IPoint pt) {
		final IPoint added = ShapeFactory.INST.createPoint(this);
		if(pt != null) added.translate(pt.getX(), pt.getY());
		return added;
	}

	@Override
	public  DoubleProperty xProperty() {
		return x;
	}

	@Override
	public  DoubleProperty yProperty() {
		return y;
	}

	@Override
	public double getY() {
		return y.get();
	}

	@Override
	public double getX() {
		return x.get();
	}

	@Override
	public double distance(double xCoord, double yCoord) {
		return Math.sqrt(Math.pow(xCoord - x.get(), 2) + Math.pow(yCoord - y.get(), 2));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp = Double.doubleToLongBits(x.doubleValue());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y.doubleValue());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof IPoint)) return false;
		final IPoint other = (IPoint) obj;
		if(Double.doubleToLongBits(x.doubleValue()) != Double.doubleToLongBits(other.getX())) return false;
		if(Double.doubleToLongBits(y.doubleValue()) != Double.doubleToLongBits(other.getY())) return false;
		return true;
	}

	@Override
	public String toString() {
		return "LPoint [x=" + x.doubleValue() + ", y=" + y.doubleValue() + "]";
	}
}
