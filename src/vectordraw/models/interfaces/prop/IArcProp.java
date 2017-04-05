package vectordraw.models.interfaces.prop;

import vectordraw.models.interfaces.shape.EArcStyle;

/**
 * Properties of arc shapes.

 */
public interface IArcProp {
	/**
	 * @return the style of the arc.
	  */
	EArcStyle getArcStyle();

	/**
	 * @param style the arc style to set.
	  */
	void setArcStyle(final EArcStyle style);

	/**
	 * @return the angleStart.
	  */
	double getAngleStart();

	/**
	 * @param angleStart the angleStart to set.
	 
	 */
	void setAngleStart(final double angleStart);

	/**
	 * @return the angleEnd.
	 
	 */
	double getAngleEnd();

	/**
	 * @param angleEnd the angleEnd to set.
	
	 */
	void setAngleEnd(final double angleEnd);
}
