package vectordraw.models.interfaces.prop;

/**
 * Defines an interface that classes defining a shape that
 * can have round corners should implement.
 
 */
public interface ILineArcProp {
	/**
	 * @return the lineArc.
	 */
	double getLineArc();

	/**
	 * @param lineArc the lineArc to set. Must be in [0,1]
	 */
	void setLineArc(final double lineArc);

	/**
	 * @return the isCornerRound.
	 */
	boolean isRoundCorner();
}
