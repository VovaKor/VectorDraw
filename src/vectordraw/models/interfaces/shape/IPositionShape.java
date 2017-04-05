package vectordraw.models.interfaces.shape;

/**
 * Defines an interface that classes defining a shape that has a position should implement.
 
 */
public interface IPositionShape extends ISingleShape {
	/**
	 * @return The X coordinate of the shape (of the bottom-left point of the shape).
	 * *
	 */
	double getX();

	/**
	 * @return The Y coordinate of the shape (of the bottom-left point of the shape).
	 * *
	 */
	double getY();

	/**
	 * @return The position of the shape (the bottom-left point of the shape).
	 * *
	 */
	IPoint getPosition();

	/**
	 * Sets the X coordinate of the shape (of the bottom-left point of the shape).
	 * @param x The X coordinate of the shape.
	 * *
	 */
	void setX(final double x);

	/**
	 * Sets the Y coordinate of the shape (of the bottom-left point of the shape).
	 * @param y The Y coordinate of the shape.
	 * *
	 */
	void setY(final double y);

	/**
	 * Sets the position of the shape (the bottom-left point of the shape).
	 * @param pt The new position of the shape.
	 * *
	 */
	void setPosition(final IPoint pt);

	/**
	 * Sets the position of the shape (the bottom-left point of the shape).
	 * @param x The X coordinate of the new position of the shape.
	 * @param y The Y coordinate of the new position of the shape.
	 * *
	 */
	void setPosition(final double x, final double y);
}
