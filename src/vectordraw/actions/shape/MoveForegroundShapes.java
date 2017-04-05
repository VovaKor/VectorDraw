
package vectordraw.actions.shape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import vectordraw.actions.IModifying;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.util.LangTool;
import org.malai.undo.Undoable;
import vectordraw.actions.ADrawingActionImpl;
import vectordraw.actions.IShapesAction;

/**
 * This action puts in background / foreground shapes.
 */
public class MoveForegroundShapes extends ADrawingActionImpl implements IShapesAction, Undoable, IModifying {
/** The index of the deleted shapes into the original list. */
	List<Integer> positionShapes;

	/** The shapes to handle. */
	final List<IShape> shapes;


	public MoveForegroundShapes() {
		super();
		shapes = new ArrayList<>();
	}

	@Override
	public boolean isRegisterable() {
		return true;
	}

	@Override
	protected void doActionBody() {
		
                drawing.ifPresent(dr -> {
			final List<IShape> drawingSh = dr.getShapes();
                        positionShapes = shapes.stream().mapToInt(sh -> {
				int pos = drawingSh.indexOf(sh);
				IShape tempShape = dr.removeShape(pos);
                                dr.addShape(tempShape);
				return pos;
			}).boxed().collect(Collectors.toList());
			dr.setModified(true);
		});
	}

	@Override
	public boolean canDo() {
		return drawing.isPresent() && !shapes.isEmpty();
	}

	@Override
	public void undo() {
		drawing.ifPresent(dr -> {
			for(int i = positionShapes.size() - 1; i >= 0; i--) {
				dr.addShape(shapes.get(i), positionShapes.get(i));
			}
			dr.setModified(true);
		});
	}

	@Override
	public void redo() {
		doActionBody();
	}

	@Override
	public String getUndoName() {
		return LangTool.INSTANCE.getBundle().getString("Actions.5");
	}

	@Override
	public List<IShape> getShapes() {
		return shapes;
	}
}
