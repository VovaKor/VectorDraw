
package vectordraw.models.impl;

import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IEllipse;
import vectordraw.models.interfaces.shape.IPoint;


/**
 * An implementation of an ellipse.
 */
class VDEllipse extends ARectangularShape implements IEllipse {
	/**
	 * Creates an Ellipse.
	 * *
	 */
	VDEllipse() {
		this(ShapeFactory.INST.createPoint(), ShapeFactory.INST.createPoint(1.0, 1.0));
	}

	/**
	 * Creates an ellipse.
	 * @param tl The top-left point of the ellipse.
	 * @param br The bottom-right point of the ellipse.
	 * @throws IllegalArgumentException If a or b is not valid.
	 */
	VDEllipse(final IPoint tl, final IPoint br) {
		super(tl, br);
	}

	
	@Override
	public void setCentre(final IPoint centre) {
		if(MathUtils.INST.isValidPt(centre)) {
			final IPoint gc = getGravityCentre();
			translate(centre.getX() - gc.getX(), centre.getY() - gc.getY());
		}
	}

	@Override
	public  IPoint getCenter() {
		return getGravityCentre();
	}


	@Override
	public double getA() {
		final double rx = getWidth() / 2.0;
		final double ry = getHeight() / 2.0;
		return rx < ry ? ry : rx;
	}


	@Override
	public double getB() {
		final double rx = getWidth() / 2.0;
		final double ry = getHeight() / 2.0;
		return rx > ry ? ry : rx;
	}
}
