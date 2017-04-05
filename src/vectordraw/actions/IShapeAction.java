/*
  *
  *
  *
  *
  
  
  *
  *
 */
package vectordraw.actions;

import java.util.Optional;
import vectordraw.models.interfaces.shape.IShape;
import org.malai.action.Action;

/**
 * This trait encapsulates a shape attribute.
 */
public interface IShapeAction<T extends IShape> extends Action {
	/**
	 * Sets the shape to add.
	 * @param sh The shape to add.
	 * *
	 */
	void setShape(final T sh);

	/**
	 * @return The shape to modify.
	 * *
	 */
	Optional<T> getShape();

	@Override
	default boolean canDo() {
		return getShape().isPresent();
	}
}
