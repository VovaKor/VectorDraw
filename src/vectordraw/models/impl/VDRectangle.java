
package vectordraw.models.impl;

import javafx.beans.property.DoubleProperty;
import vectordraw.models.interfaces.prop.ILineArcProp;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IRectangle;
import vectordraw.models.interfaces.shape.IShape;


/**
 * A model of a rectangle.
 */
class VDRectangle extends ARectangularShape implements IRectangle {
	private final VDLineArcProp lineArcProp;

	VDRectangle(final IPoint tl, final IPoint br) {
		super(tl, br);
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
