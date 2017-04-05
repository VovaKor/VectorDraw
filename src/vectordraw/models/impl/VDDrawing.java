
package vectordraw.models.impl;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IDrawing;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;
import org.malai.mapping.MappingRegistry;

/**
 * Implements the concept of drawing.
 */
class VDDrawing implements IDrawing, ISetShapesImpl {
	/** The set of shapes. */
	private final ObservableList<IShape> shapes;

	/** The selected shapes of the drawing. */
	private final IGroup selectedShapes;

	/** Defined if the shape has been modified. */
	private boolean isShapeModified;


	VDDrawing() {
		super();
		shapes = FXCollections.observableArrayList();
		selectedShapes = ShapeFactory.INST.createGroup();
		isShapeModified = false;
	}


	@Override
	public IGroup getSelectedShapes() {
		return selectedShapes;
	}

	@Override
	public void setSelection(final List<IShape> newSelection) {
		selectedShapes.clear();
		newSelection.forEach(sh -> selectedShapes.addShape(sh));
	}

	@Override
	public void clear() {
		ISetShapesImpl.super.clear();
		selectedShapes.clear();
	}

	@Override
	public ObservableList<IShape> getShapes() {
		return shapes;
	}

	@Override
	public boolean removeShape(final IShape sh) {
		selectedShapes.removeShape(sh);
		return ISetShapesImpl.super.removeShape(sh);
	}

	@Override
	public IShape removeShape(final int i) {
		// Must be removed from the selectedShapes before removing from the main list (otherwise mapping selection2border will fail.
		if(!shapes.isEmpty() && i >= -1 && i < shapes.size()) {
			if(i == -1) {
				selectedShapes.removeShape(shapes.get(shapes.size() - 1));
			}else {
				selectedShapes.removeShape(shapes.get(i));
			}
		}

		return ISetShapesImpl.super.removeShape(i);
	}

	@Override
	public void setModified(final boolean isShapeModified) {
		if(isShapeModified) MappingRegistry.REGISTRY.onObjectModified(this);
		else shapes.forEach(sh -> sh.setModified(false));

		this.isShapeModified = isShapeModified;
	}

	@Override
	public boolean isModified() {
		return isShapeModified || shapes.stream().anyMatch(sh -> sh.isModified());
	}

	@Override
	public void reinit() {
		clear();
	}
}
