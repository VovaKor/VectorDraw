
package vectordraw.models.impl;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import vectordraw.util.MathUtils;
import vectordraw.models.interfaces.prop.ILineArcProp;


/**
 * Line arc properties.
 * To turn as a trait as soon as Java will support private attributes in interfaces.
 */
class VDLineArcProp implements ILineArcProp {
	/** The radius of arcs drawn at the corners of lines. */
	final  DoubleProperty frameArc;

	VDLineArcProp() {
		super();
		frameArc = new SimpleDoubleProperty(0d);
	}

	@Override
	public double getLineArc() {
		return frameArc.doubleValue();
	}

	@Override
	public boolean isRoundCorner() {
		return getLineArc() > 0d;
	}

	@Override
	public void setLineArc(final double arc) {
		if(MathUtils.INST.isValidCoord(arc) && arc >= 0d && arc <= 1d) {
			frameArc.set(arc);
		}
	}
}