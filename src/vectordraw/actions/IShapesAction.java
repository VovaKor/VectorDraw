/*
  *
  *
  *
  *
  
  
  *
  *
 */
package vectordraw.actions;

import java.util.List;
import vectordraw.models.interfaces.shape.IShape;
import org.malai.action.Action;

/**
 * An action that handles a set of shapes attribute.
 */
public interface IShapesAction extends Action {
	/**
	 * Sets the shape to handle.
	 * @param shape The shape to handle. Can be null.
	 * *
	 */
	default void setShape(final IShape shape) {
		getShapes().clear();

		if(shape != null) {
			getShapes().add(shape);
		}
	}

	/**
	 * Add a shape to the list of shapes to handle.
	 * @param shape The shape to handle.
	 * *
	 */
	default void addShape(final IShape shape) {
		if(shape != null) {
			getShapes().add(shape);
		}
	}

	/**
	 * @return The shapes to handle.
	 * *
	 */
	List<IShape> getShapes();
}
