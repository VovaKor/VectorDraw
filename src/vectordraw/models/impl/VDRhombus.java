
package vectordraw.models.impl;

import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IRhombus;

/**
 * A model of a rhombus.
 */
class VDRhombus extends ARectangularShape implements IRhombus {
	/**
	 * Creates a rhombus at the bottom-left position (0,0) with width=height=1.
	 */
	VDRhombus() {
		this(ShapeFactory.INST.createPoint(0.5, -0.5), 1d, 1d);
	}

	/**
	 * Creates a rhombus.
	 * @param centre The centre of the rhombus.
	 * @param width The width of the rhombus.
	 * @param height The height of the rhombus.
	 * @throws IllegalArgumentException If the width or the height is not valid.
	 * @throws NullPointerException If the given point is null.
	 */
	VDRhombus(final IPoint centre, final double width, final double height) {
		super(ShapeFactory.INST.createPoint(centre.getX() - width / 2d, centre.getY() - height / 2d),
			ShapeFactory.INST.createPoint(centre.getX() + width / 2d, centre.getY() + height / 2d));
	}
}
