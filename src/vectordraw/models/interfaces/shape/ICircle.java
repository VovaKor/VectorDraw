package vectordraw.models.interfaces.shape;



/**
 * Defines an interface that classes defining a circle should implement.
 *
 
 */
public interface ICircle extends ISquaredShape {
	/**
	 * @return The centre of the circle.
	 */
	 IPoint getCenter();

	/**
	 * @return The radius of the circle.
	 */
	double getRadius();
}
