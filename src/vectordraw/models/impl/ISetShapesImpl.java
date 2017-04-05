
package vectordraw.models.impl;

import java.util.List;
import vectordraw.models.interfaces.prop.ISetShapesProp;
import vectordraw.models.interfaces.shape.IShape;

/**
 * This trait implements the ISetShapes interface.
 * To turn as a full Java trait as soon as Java will support private attributes in interfaces.
 */
interface ISetShapesImpl extends ISetShapesProp {
	@Override
	default boolean contains(final IShape sh) {
		return sh != null && getShapes().contains(sh);
	}

	@Override
	default void addShape(final IShape sh) {
		if(sh != null && (!(sh instanceof ISetShapesProp) || !((ISetShapesProp) sh).isEmpty())) {
			getShapes().add(sh);
		}
	}

	@Override
	default void addShape(final IShape sh, final int index) {
		final List<IShape> shapes = getShapes();
		if(sh != null && index <= shapes.size() && (index == -1 || index >= 0) && (!(sh instanceof ISetShapesProp) || !((ISetShapesProp) sh).isEmpty())) {
			if(index == -1 || index == shapes.size()) {
				shapes.add(sh);
			}else {
				shapes.add(index, sh);
			}
		}
	}

	@Override
	default void clear() {
		final List<IShape> shapes = getShapes();
		if(!shapes.isEmpty()) {
			shapes.clear();
		}
	}

	@Override
	default IShape getShapeAt(final int i) {
		final List<IShape> shapes = getShapes();
		if(i < -1 || i >= shapes.size()) {
			return null;
		}
		if(i == -1) {
			return shapes.get(shapes.size() - 1);
		}
		return shapes.get(i);
	}


	@Override
	default boolean isEmpty() {
		return getShapes().isEmpty();
	}


	@Override
	default boolean removeShape(final IShape sh) {
		return sh != null && getShapes().remove(sh);
	}


	@Override
	default IShape removeShape(final int i) {
		final List<IShape> shapes = getShapes();
		if(shapes.isEmpty() || i < -1 || i >= shapes.size()) {
			return null;
		}
		if(i == -1) {
			return shapes.remove(shapes.size() - 1);
		}
		return shapes.remove(i);
	}


	@Override
	default int size() {
		return getShapes().size();
	}
}
