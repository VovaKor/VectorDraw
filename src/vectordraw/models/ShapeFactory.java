
package vectordraw.models;

import vectordraw.models.impl.VDShapeFactory;
import vectordraw.models.interfaces.shape.IShapeFactory;


/**
 * This singleton to use the shape factory.
 */
public final class ShapeFactory extends VDShapeFactory {
	public static final IShapeFactory INST = new VDShapeFactory();

	private ShapeFactory() {
		super();
	}
}
