
package vectordraw.models.impl;

import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.ITriangle;

/**
 * A model of a triangle.
 */
class VDTriangle extends ARectangularShape implements ITriangle {
	
    /**
	 * Creates a triangle at the position (0,0).
	 */
	VDTriangle() {
		this(ShapeFactory.INST.createPoint(), 1, 1);
               
	}

	/**
	 * Creates a triangle.
	 * @param pos The north-west point of the triangle.
	 * @param width The width of the triangle.
	 * @param height The height of the triangle.
	 * @throws IllegalArgumentException If the width or the height is not valid.
	 */
	VDTriangle(final IPoint pos, final double width, final double height) {
		super(pos, pos == null ? null : ShapeFactory.INST.createPoint(pos.getX() + width, pos.getY() + height));
               
	}

    
}

