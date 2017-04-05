package vectordraw.models.interfaces.shape;

/**
 * Defines an interface for rectangular shapes.
 
 */
public interface IRectangularShape extends IPositionShape {
	/**
	 * Sets the width of the rectangle (the reference point is the bottom-left point of the rectangle).
	 * @param width The new width.
	 * *
	 */
	void setWidth(final double width);

	/**
	 * Sets the height of the rectangle (the reference point is the bottom-left point of the rectangle).
	 * @param height The new height.
	 * *
	 */
	void setHeight(final double height);
}
