package vectordraw.models.interfaces.shape;

/**
 * Defines an interface that classes defining a Bezier curve should implement.
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
public interface IBezierCurve extends IControlPointShape {
	/**
	 * @return the isClosed.
	 */
	boolean isClosed();

	/**
	 * Defines if the shape is closed.
	 * @param isClosed True: the shape will be closed.
	 * *
	 */
	void setIsClosed(final boolean isClosed);
}
