package vectordraw.models.interfaces.shape;

import javafx.beans.property.DoubleProperty;
import vectordraw.models.interfaces.prop.ILineArcProp;


/**
 * Defines an interface that classes defining a rectangle should implement.
 
 */
public interface IRectangle extends IRectangularShape, ILineArcProp {
	 DoubleProperty frameArcProperty();
	//
}
