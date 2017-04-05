package vectordraw.models.interfaces.shape;

/**
 * Defines an interface for shapes that contain modifiable points.
 
 */
public interface IModifiablePointsShape extends ISingleShape {
	/**
	 * Adds a point to the shape model.
	 * @param pt The point to add. Must be valid.
	 * *
	 */
	void addPoint(final IPoint pt);

	/**
	 * Adds the given point to the points list at the given position. The model is not updated!
	 * @param pt The point to add.
	 * @param position The position of insertion (-1 corresponds to the last point).
	 * *
	 */
	void addPoint(final IPoint pt, final int position);

	/**
	 * Removes the given point of the shape.
	 * @param pt The point to remove.
	 * @return True if the point is removed. False otherwise.
	 * *
	 */
	boolean removePoint(final IPoint pt);

	/**
	 * Removes the point at the given position.
	 * @param position The position of the point to remove (-1 corresponds to the last point).
	 * @return The removed point or null.
	 * *
	 */
	IPoint removePoint(final int position);

	/**
	 * Sets the point at the given position to the given coordinate. The model is not updated!
	 * @param p The new position of the wanted point.
	 * @param position The position of the point to move in the points list (-1 corresponds to the last point).
	 * @return true if the operation is successful.
	 * *
	 */
	boolean setPoint(final IPoint p, final int position);

	/**
	 * Sets the point at the given position to the given coordinate.
	 * @param x The new X-coordinate. The model is not updated!
	 * @param y The new Y-coordinate.
	 * @param position The position of the point to move in the points list (-1 corresponds to the last point).
	 * @return true if the operation is successful.
	 * *
	 */
	boolean setPoint(final double x, final double y, final int position);

	/**
	 * Replaces the point at the given position by the given point.
	 * @param pt The new point. Must not be a point of the shape.
	 * @param position The position of the point to remove (-1 corresponds to the last point).
	 * @return The removed point or null.
	 * *
	 */
	IPoint replacePoint(final IPoint pt, final int position);
}
