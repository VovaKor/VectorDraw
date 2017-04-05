package vectordraw.models.interfaces.shape;

public interface ISquaredShape extends IPositionShape {
	/**
	 * Sets the width of the squared shape (the reference point is the bottom-left point of the shape).
	 * @param width The new width.
	 *
	 */
	void setWidth(final double width);
}
