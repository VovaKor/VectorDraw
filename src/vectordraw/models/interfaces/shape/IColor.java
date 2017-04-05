package vectordraw.models.interfaces.shape;



/**
 * Defines the concept of colour.
 *
 */
public interface IColor {
	/** @return The AWT color. */
	java.awt.Color toAWT();
	
	/** @return The JavaFX color. */
	javafx.scene.paint.Color toJFX();
	
	/**
	 * @return The red channel.
	 * @throws IllegalArgumentException If the value in not in [0,1].
	 */
	double getR();
	
	/**
	 * @return The green channel.
	 * @throws IllegalArgumentException If the value in not in [0,1].
	 */
	double getG();
	
	/**
	 * @return The blue channel.
	 * @throws IllegalArgumentException If the value in not in [0,1].
	 */
	double getB();
	
	/**
	 * @return The opacity channel.
	 * @throws IllegalArgumentException If the value in not in [0,1].
	 */
	double getO();
	
	/**
	 * Sets the red channel.
	 * @param red the red to set
	 */
	void setR(final double red);
	
	/**
	 * Sets the green channel.
	 * @param green the green to set
	 */
	void setG(final double green);
	
	/**
	 * Sets the blue channel.
	 * @param blue the blue to set
	 */
	void setB(final double blue);
	
	/**
	 * Sets the opacity channel.
	 * @param opacity the opacity to set
	 */
	void setO(final double opacity);
}
