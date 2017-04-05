
package vectordraw.actions.shape;

import java.util.ArrayList;
import java.util.List;
import vectordraw.actions.ADrawingActionImpl;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;
import org.malai.action.Action;
import vectordraw.actions.IModifying;
import vectordraw.actions.IShapesAction;

/**
 * This action allows to (un-)select shapes.
 */
public class SelectShapes extends ADrawingActionImpl implements IShapesAction, IModifying {
	/** The shapes to handle. */
	final List<IShape> shapes;

	public SelectShapes() {
		super();
		shapes = new ArrayList<>();
	}

	@Override
	public void doActionBody() {
		drawing.ifPresent(dr -> {
			final IGroup selection = dr.getSelectedShapes();

			if(shapes.isEmpty()) selection.clear();
			else {
				for(int i = selection.size() - 1; i >= 0; i--) {
					if(!shapes.contains(selection.getShapeAt(i))) selection.removeShape(i);
				}
				shapes.forEach(sh -> {
					if(!selection.contains(sh)) selection.addShape(sh);
				});
			}
		});
	}

	@Override
	public boolean isRegisterable() {
		return true;
	}

	@Override
	public boolean cancelledBy(final Action action) {
		return action instanceof SelectShapes || action instanceof DeleteShapes;
	}

	@Override
	public List<IShape> getShapes() {
		return shapes;
	}
}
