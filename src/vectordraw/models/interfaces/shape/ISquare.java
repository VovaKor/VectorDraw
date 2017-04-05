package vectordraw.models.interfaces.shape;

import javafx.beans.property.DoubleProperty;
import vectordraw.models.interfaces.prop.ILineArcProp;


/**
 * Defines an interface that classes defining a square should implement.
 *
 *
 *
 *
 
 
 *
 *
 * 07/03/2009
*
 
 * *
 */
public interface ISquare extends ISquaredShape, ILineArcProp {
	 DoubleProperty frameArcProperty();
	//
}
