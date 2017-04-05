package vectordraw.models.interfaces.shape;

import javafx.beans.property.ObjectProperty;
import vectordraw.models.interfaces.prop.IArcProp;

/**
 * Defines an interface that classes defining arc should implement.
 *
 */
public interface IArc extends IPositionShape, IArcProp, ICircle {
	/**
	 * @return The coordinate of the start point of the arc.
	 * @since 1.9
	 */
	IPoint getStartPoint();

	/**
	 * @return The coordinate of the end point of the arc.
	 * @since 1.9
	 */
	IPoint getEndPoint();
        
        double getLength();
        
        void setLength(double length);
        
        ObjectProperty<EArcStyle> arcStyleProperty();
}
