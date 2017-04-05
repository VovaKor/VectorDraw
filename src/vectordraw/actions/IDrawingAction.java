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
import vectordraw.models.interfaces.shape.IDrawing;

/**
 * This trait encapsulates a drawing attribute.
 */
public interface IDrawingAction {
	/**
	 * @param dr The drawing that will be handled by the action
	 * *
	 */
	void setDrawing(final IDrawing dr);

	/**
	 * @return The drawing that will be handled by the action
	 * *
	 */
	Optional<IDrawing> getDrawing();
}
