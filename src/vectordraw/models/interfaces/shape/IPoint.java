package vectordraw.models.interfaces.shape;

import java.awt.geom.Point2D;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point3D;


/**
 * Defines an interface that classes defining a point should implement.
 * 
 */
public interface IPoint {
	/**
	 * @return The Y coordinate of the point.
	 * 
	 */
	double getY();

	/**
	 * @return The X coordinate of the point.
	 * 
	 */
	double getX();

	/**
	 * Changes the coordinates of the point.
	 * @param pt The new position.
	 *
	 */
	void setPoint(final IPoint pt);

	/**
	 * Sets the X coordinate of the point.
	 * @param newX The new X coordinate. Must be valid (not equal too NaN,...).
	 * 
	 */
	void setX(final double newX);

	/**
	 * Sets the Y coordinate of the point.
	 * @param newY The new Y coordinate. Must be valid (not equal too NaN,...).
	 * 
	 */
	void setY(final double newY);

	/**
	 * Sets the coordinates of the point.
	 * @param newX The new X coordinate. Must be valid (not equal too NaN,...).
	 * @param newY The new Y coordinate. Must be valid (not equal too NaN,...).
	 * 
	 */
	void setPoint(final double newX, final double newY);

	/**
	 * Gets a point by central symmetry.
	 * @param pt The centre of the symmetry.
	 * @return The resulting point.
	 */
	IPoint centralSymmetry(final IPoint pt);

	/**
	 * Returns horizontally the point.
	 * @param origin The location of the horizontal axe.
	 * @return the computed point or null is the given point is not valid or if a problem occurs.
	 */
	IPoint horizontalSymmetry(final IPoint origin);

	/**
	 * Returns vertically the point.
	 * @param origin The location of the vertical axe.
	 * @return the computed point or null is the given point is not valid or if a problem occurs.
	 */
	IPoint verticalSymmetry(final IPoint origin);

	/**
	 * Adds the given pt to the current one: this+pt
	 * @param pt The second point of the subtraction
	 * @return The result of the subtraction.
	 * 
	 */
	IPoint add(final IPoint pt);

	/**
	 * Subtracts the given pt to the current one: this-pt
	 * @param pt The second point of the subtraction
	 * @return The result of the subtraction.
	 * 
	 */
	IPoint substract(final IPoint pt);

	/**
	 * Normalizes the point (considered here as a vector).
	 * @return The vector normalization.
	 * 
	 */
	 IPoint normalise();

	/**
	 * The magnitude (length) of the point considered here as a vector.
	 * @return The computed magnitude.
	 * 
	 */
	double magnitude();

	/**
	 * Computes the angle of the given point where the calling point is used as
	 * the gravity centre.
	 * @param pt The point used to compute the angle.
	 * @return The angle or NaN if the given point is not valid.
	 */
	double computeAngle(final IPoint pt);

	/**
	 * Computes the angle of rotation between two points where the calling point is
	 * used as the gravity centre.
	 * @param pt1 The first point.
	 * @param pt2 The second point.
	 * @return The rotation point or NaN if one of the given point is not valid.
	 */
	double computeRotationAngle(final IPoint pt1, final IPoint pt2);

	/**
	 * Allows to know if the point p is equal to the current point considering a gap.
	 * @param p The point to compare.
	 * @param gap The approximation gap.
	 * @return True if they are equals considering the gap.
	 * @exception IllegalArgumentException When <code>gap<0</code>
	 */
	boolean equals(final IPoint p, final double gap);

	/**
	 * @param p The second point.
	 * @return The middle point of the current and given points.
	 */
	IPoint getMiddlePoint(final IPoint p);

	/**
	 * Rotates a point with as reference another point.
	 * @param gravityC The point of reference.
	 * @param theta The angle of rotation in radian.
	 * @return The rotated point.
	 * 
	 */
	IPoint rotatePoint(final IPoint gravityC, final double theta);

	/**
	 * Creates a new point zoomed using the calling points.
	 * @param zoomLevel The zoom level.
	 * @return The zoomed point.
	 * 
	 */
	IPoint zoom(final double zoomLevel);

	/**
	 * Translates the point. If one of the given coordinate is not valid (NaN, infinite,...), then
	 * the translation does not occur.
	 * @param tx The X translation.
	 * @param ty The Y translation.
	 */
	void translate(final double tx, final double ty);

	/**
	 * @param pt The second point.
	 * @return The distance between the two points.
	 * 
	 */
	double distance(final IPoint pt);

	/**
	 * @param x The x coordinate of the second point.
	 * @param y The y coordinate of the second point.
	 * @return The distance between the two points.
	 * 
	 */
	double distance(final double x, final double y);

	/**
	 * @return A Point2D.Double point equivalent to the current point.
	 * 
	 */
	Point2D.Double toPoint2D();

	/**
	 * @return A Point3D point equivalent to the current point.
	 */
	Point3D toPoint3D();

	/**
	 * Defines the current point using the given point.
	 * @param pt The point 2D to copy.
	 * 
	 */
	void setPoint2D(Point2D pt);
	
	/**
	 * @return The X property of the point.
	 */
	 DoubleProperty xProperty();
	
	/**
	 * @return The Y property of the point.
	 */
	 DoubleProperty yProperty();
}
