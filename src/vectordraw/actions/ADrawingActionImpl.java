
package vectordraw.actions;

import java.util.Optional;
import vectordraw.models.interfaces.shape.IDrawing;
import org.malai.action.ActionImpl;

public abstract class ADrawingActionImpl extends ActionImpl {
	/** The drawing that will be handled by the action. */
	protected Optional<IDrawing> drawing;

	protected ADrawingActionImpl() {
		super();
		drawing = Optional.empty();
	}

	public void setDrawing(final IDrawing dr) {
		drawing = Optional.ofNullable(dr);
	}

	public Optional<IDrawing> getDrawing() {
		return drawing;
	}

	@Override
	public boolean canDo() {
		return drawing.isPresent();
	}
}
