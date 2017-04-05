package vectordraw.models.interfaces.shape;



/**
 * Defines an interface that classes defining an ellipse should implement.
 *
 *
 *
 *
 
 
 *
 *
 * 07/02/2009
*
 
 * *
 */
public interface IEllipse extends IRectangularShape {
	/**
	 * @return The half of the biggest axe.
	 * *
	 */
	double getA();


	/**
	 * @return The half of the smallest axe.
	 * *
	 */
	double getB();


	/**
	 * Translates the shape to its new centre.
	 * @param centre The new centre.
	 * *
	 */
	void setCentre(final IPoint centre);

	/**
	 * @return The center of the ellipse. Cannot be null.
	 */
	 IPoint getCenter();
}
