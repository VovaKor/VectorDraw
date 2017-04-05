package vectordraw.models.interfaces.shape;

import java.util.List;
import vectordraw.models.interfaces.prop.ISetShapesProp;
import org.malai.presentation.AbstractPresentation;

/**
 * Defines an interface of a drawing that contains a set of shapes
 * and a set of selected shapes.
 * 
 */
public interface IDrawing extends ISetShapesProp, AbstractPresentation {
	/**
	 * @return The group that contains the selected shape. Cannot be null.
	 * 
	 */
	IGroup getSelectedShapes();

	/**
	 * Selects the given shapes and unselect the already selected shapes.
	 * @param shapes The shapes to select. Cannot be null.
	 * @throws NullPointerException when shapes is null.
	 *
	 */
	void setSelection(final List<IShape> shapes);
}
