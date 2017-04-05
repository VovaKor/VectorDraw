
package vectordraw.actions;

import java.util.Optional;
import vectordraw.models.interfaces.shape.IShape;
import org.malai.action.ActionImpl;

public abstract class AShapeActionImpl<T extends IShape> extends ActionImpl implements IShapeAction<T> {
	/** The shape to add. */
	protected Optional<T> shape;

	protected AShapeActionImpl() {
		super();
		shape = Optional.empty();
	}

	@Override
	public void setShape(final T sh) {
		shape = Optional.ofNullable(sh);
	}

	@Override
	public Optional<T> getShape() {
		return shape;
	}

	@Override
	public boolean canDo() {
		return shape.isPresent();
	}
}
