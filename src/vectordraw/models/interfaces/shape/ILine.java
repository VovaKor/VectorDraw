package vectordraw.models.interfaces.shape;


/**
 * Defines an interface that classes defining a line should implement.
 * 
 */
public interface ILine {
	/**
	 * Computes the angle of the line.
	 * @return The angle of the line.
	 * 
	 */
	double getLineAngle();

	/**
	 * @return The x coordinate of the first point.
	 * 
	 */
	double getX1();

	/**
	 * @return The x coordinate of the second point.
	 * 
	 */
	double getX2();

	/**
	 * @return The y coordinate of the first point.
	 * 
	 */
	double getY1();

	/**
	 * @return The y coordinate of the second point.
	 * 
	 */
	double getY2();

	/**
	 * @return The first point.
	 * 
	 */
	IPoint getPoint1();

	/**
	 * @return The second point.
	 * 
	 */
	IPoint getPoint2();


	/**
	 * @return True if the line is vertical.
	 * 
	 */
	boolean isVerticalLine();


	/**
	 * @return True if the line is horizontal.
	 * 
	 */
	boolean isHorizontalLine();

	/**
	 * @return True if the segment defined by the line contains the given point. False otherwise or
	 * if the given point is null.
	 * @param pt The point to check.
	 * 
	 */
	boolean isInSegment(final IPoint pt);

	/**
	 * Sets the position of the line. Do nothing if one of the given parameter
	 * is not valid.
	 * @param x1 The x coordinate of the first point.
	 * @param y1 The y coordinate of the first point.
	 * @param x2 The x coordinate of the second point.
	 * @param y2 The y coordinate of the second point.
	 * 
	 */
	void setLine(final double x1, final double y1, final double x2, final double y2);

	/**
	 * Sets the x coordinate of the first point.
	 * @param x1 The new x coordinate of the first point.
	 * 
	 */
	void setX1(final double x1);

	/**
	 * Sets the x coordinate of the second point.
	 * @param x2 The new x coordinate of the second point.
	 * 
	 */
	void setX2(final double x2);

	/**
	 * Sets the y coordinate of the first point.
	 * @param y1 The new y coordinate of the first point.
	 * 
	 */
	void setY1(final double y1);

	/**
	 * Sets the y coordinate of the second point.
	 * @param y2 The new y coordinate of the second point.
	 * 
	 */
	void setY2(final double y2);

	/**
	 * Sets the first point.
	 * @param pt The new first point.
	 * 
	 */
	void setP1(final IPoint pt);

	/**
	 * Sets the second point.
	 * @param pt The new second point.
	 * 
	 */
	void setP2(final IPoint pt);

	/**
	 * @return the a parameter of the line.
	 */
	double getA();

	/**
	 * @return the b parameter of the line.
	 */
	double getB();

	/**
	 * @return The top left point of the line (may be not a point on the line).
	 */
	IPoint getTopLeftPoint();

	/**
	 * @return The bottom right point of the line (may be not a point on the line).
	 */
	IPoint getBottomRightPoint();

	/**
	 * Creates the line which is perpendicular to the current line at the point pt.
	 * @param pt The point of crossing between the two lines.
	 * @return The perpendicular line.
	 */
	ILine getPerpendicularLine(final IPoint pt);

	/**
	 * @param l The second lines
	 * @return The intersection between two lines. Null if the given is not valid or
	 * if both lines are parallels or both lines are identical.
	 */
	IPoint getIntersection(final ILine l);

	/**
	 * @param l The second line.
	 * @return The point of the intersection between of the two segments.
	 */
	IPoint getIntersectionSegment(final ILine l);

	/**
	 * Gets the points which are on the line and at the distance
	 * "distance" of the point "p" of the line.
	 * @param x The x-coordinate of the point of reference.
	 * @param y The y-coordinate of the point of reference.
	 * @param distance The distance between p and the points we must find.
	 * @return The found points or null if a problem occurs.
	 */
	IPoint[] findPoints(final double x, final double y, final double distance);

	/**
	 * Gets the points which are on the line and at the distance
	 * "distance" of the point "p" of the line.
	 * @param p The point of reference.
	 * @param distance The distance between p and the points we must find.
	 * @return The found points or null if a problem occurs.
	 */
	IPoint[] findPoints(final IPoint p, final double distance);

	/**
	 * Update the y-intercept <code>b</code> and slope <code>a</code>.
	 */
	void updateAandB();
}
