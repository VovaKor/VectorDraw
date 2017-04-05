package vectordraw.actions.shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.malai.undo.Undoable;
import vectordraw.actions.ADrawingActionImpl;
import vectordraw.actions.IModifying;
import vectordraw.actions.IShapesAction;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;
/**
 * This action joins shapes.
 * @author Volodymyr Korobko
 */
public class JoinShapes extends ADrawingActionImpl implements IShapesAction, IModifying  {
    /** The added group of shapes. */
	final IGroup addedGroup;

	/** The shapes to handle. */
	final List<IShape> shapes;

	public JoinShapes() {
		super();
		addedGroup = ShapeFactory.INST.createGroup();
		shapes = new ArrayList<>();
	}

	@Override
	protected void doActionBody() {
		joinShapes();
	}

	@Override
	public boolean canDo() {
		return super.canDo() && !shapes.isEmpty();
	}

	private void joinShapes() {
		drawing.ifPresent(dr -> {
//                    
			final List<IShape> drawingSh = dr.getShapes();
			shapes.stream().sorted((s1, s2) -> drawingSh.indexOf(s1) < drawingSh.indexOf(s2) ? -1 : 1)
                                .forEach(sh -> {
			          addedGroup.addShape(sh);
				  dr.removeShape(sh);
			});

			dr.addShape(addedGroup);
			dr.setModified(true);
		});
	}

	@Override
	public boolean isRegisterable() {
		return true;
	}

	@Override
	public List<IShape> getShapes() {
		return shapes;
	}
}
