
package vectordraw.models.impl;

import javafx.beans.property.DoubleProperty;
import vectordraw.models.interfaces.prop.ILineArcProp;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.ISquare;


/**
 * A model of a square.
 */
class VDSquare extends ASquaredShape implements ISquare {
	private final VDLineArcProp lineArcProp;

	VDSquare(final IPoint tl, final double width) {
		super(tl, width);
		lineArcProp = new VDLineArcProp();
	}

	@Override
	public void copy(final IShape sh) {
		super.copy(sh);
		if(sh instanceof ILineArcProp) {
			setLineArc(((ILineArcProp) sh).getLineArc());
		}
	}

	@Override
	public double getLineArc() {
		return lineArcProp.getLineArc();
	}

	@Override
	public void setLineArc(final double lineArc) {
		lineArcProp.setLineArc(lineArc);
	}

	@Override
	public boolean isRoundCorner() {
		return lineArcProp.isRoundCorner();
	}

	@Override
	public  DoubleProperty frameArcProperty() {
		return lineArcProp.frameArc;
	}
}
