
package vectordraw.actions.shape;

import java.util.Optional;
import vectordraw.actions.AShapeActionImpl;
import vectordraw.models.interfaces.shape.IDrawing;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.util.LangTool;
import org.malai.undo.Undoable;
import vectordraw.actions.IDrawingAction;
import vectordraw.actions.IModifying;

/**
 * This action adds a shape to a drawing.
 */
public class AddShape extends AShapeActionImpl<IShape> implements IDrawingAction, Undoable, IModifying {
	/** The drawing that will be handled by the action. */
	protected Optional<IDrawing> drawing;

	public AddShape() {
		super();
		drawing = Optional.empty();
	}


	@Override
	protected void doActionBody() {
		drawing.ifPresent(dr -> shape.ifPresent(sh -> {
			dr.addShape(sh);
			dr.setModified(true);
		}));
	}

	@Override
	public boolean isRegisterable() {
		return true;
	}

	@Override
	public String getUndoName() {
		return LangTool.INSTANCE.getBundle().getString("UndoRedoManager.create"); 
	}

	@Override
	public void redo() {
		doActionBody();
	}

	@Override
	public void undo() {
		drawing.ifPresent(dr -> shape.ifPresent(sh -> {
			dr.removeShape(sh);
			dr.setModified(true);
		}));
	}

	@Override
	public void setDrawing(final IDrawing dr) {
		drawing = Optional.ofNullable(dr);
	}

	@Override
	public Optional<IDrawing> getDrawing() {
		return drawing;
	}

	@Override
	public boolean canDo() {
		return drawing.isPresent() && shape.isPresent();
	}
}
