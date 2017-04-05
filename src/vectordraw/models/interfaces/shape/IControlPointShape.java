package vectordraw.models.interfaces.shape;

import java.util.List;

/**
 * Defines an interface that classes defining a shape, containg control points, should implement.
 *
 *
 *
 *
 
 
 *
 *
 * 07/03/2009
*
 
 * *
 */
public interface IControlPointShape extends IModifiablePointsShape {
	/** The default position gap of control points against their reference point. */
	int DEFAULT_POSITION_CTRL = 40;

	/**
	 * Balances all the control points in order the create a rounded shape.
	 */
	void balance();

	/**
	 * @param position The position of the wanted points (-1 for the last point).
	 * @return The first control point at the given position or null if the position is not valid.
	 * *
	 */
	IPoint getFirstCtrlPtAt(final int position);

	/**
	 * @param position The position of the wanted points (-1 for the last point).
	 * @return The second control point at the given position or null if the position is not valid.
	 * *
	 */
	IPoint getSecondCtrlPtAt(final int position);

	/**
	 * Updates the second control points by using the first control points.
	 * @since 1.9
	 */
	void updateSecondControlPoints();

	/**
	 * Sets the X-coordinate of one of the first control point.
	 * @param x The new X-coordinate.
	 * @param id The position of the point to set.
	 * @since 1.9
	 */
	void setXFirstCtrlPt(final double x, final int id);

	/**
	 * Sets the Y-coordinate of one of the first control point.
	 * @param y The new Y-coordinate.
	 * @param id The position of the point to set.
	 * @since 1.9
	 */
	void setYFirstCtrlPt(final double y, final int id);

	/**
	 * Sets the X-coordinate of one of the second control point.
	 * @param x The new X-coordinate.
	 * @param id The position of the point to set.
	 * @since 1.9
	 */
	void setXSecondCtrlPt(final double x, final int id);

	/**
	 * Sets the Y-coordinate of one of the second control point.
	 * @param y The new Y-coordinate.
	 * @param id The position of the point to set.
	 * @since 1.9
	 */
	void setYSecondCtrlPt(final double y, final int id);

	/**
	 * @return the firstCtrlPts.
	 */
	List<IPoint> getFirstCtrlPts();

	/**
	 * @return the secondCtrlPts.
	 */
	List<IPoint> getSecondCtrlPts();
}
