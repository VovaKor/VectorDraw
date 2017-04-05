
package vectordraw.actions.shape;

import java.util.List;
import vectordraw.models.interfaces.prop.IArcProp;
import vectordraw.models.interfaces.prop.ILineArcProp;

import vectordraw.models.interfaces.shape.IGroup;
import org.malai.undo.Undoable;
import vectordraw.actions.IModifying;

/**
 * This action modifies a shape property of the given shape.
 */
public class ModifyShapeProperty extends AShapePropertyAction implements Undoable, IModifying {
	/** The shape to modify. */
	protected IGroup shapes;

	/** The old value of the property. */
	protected List<?> oldValue;


    @Override
	public void flush() {
		super.flush();

		if(shapes!=null) {
			shapes.clear();
			shapes = null;
		}

		if(oldValue!=null) {
			oldValue.clear();
			oldValue = null;
		}
	}


	@Override
	public boolean canDo() {
		return super.canDo() && shapes!=null;
	}


	@Override
	public void undo() {
		property.setPropertyValueList(shapes, oldValue);
		shapes.setModified(true);
	}


	@Override
	public void redo() {
		applyValue(value);
	}


	@Override
	public String getUndoName() {
		return property==null ? "" : property.getMessage(); 
	}


	@Override
	public boolean isRegisterable() {
		return true;
	}


	@Override
	protected void applyValue(final Object obj) {
		property.setPropertyValue(shapes, obj);
		shapes.setModified(true);
	}


	@Override
	protected void doActionBody() {
		if(oldValue==null)
			oldValue = property.getPropertyValues(shapes);
		applyValue(value);
	}


	/**
	 * Sets the group of shapes to modify.
	 * @param group The group of shapes to modify.
	 * *
	 */
	public void setGroup(final IGroup group) {
		this.shapes = group;
	}



	@Override
	protected boolean isPropertySupported() {
		if(shapes!=null && super.isPropertySupported())
			switch(property) {
				case SHOW_POINTS: return shapes.isShowPtsable();
				
				case BORDER_POS:		return shapes.isBordersMovable();
				case COLOUR_FILLING:	return shapes.isFillable();
				case COLOUR_GRADIENT_END:
				case COLOUR_GRADIENT_START:
				case LINE_STYLE:		return shapes.isLineStylable();
				case LINE_THICKNESS:	return shapes.isThicknessable();
				case DBLE_BORDERS_SIZE:
				case DBLE_BORDERS:
				case COLOUR_DBLE_BORD:	return shapes.isDbleBorderable();
				case SHADOW_ANGLE:
				case SHADOW_SIZE:
				case SHADOW_COLOUR:
				case SHADOW:			return shapes.isShadowable();
				case GRAD_ANGLE:
				case GRAD_MID_POINT:
				case HATCHINGS_ANGLE:
				case HATCHINGS_SEP:
				case HATCHINGS_WIDTH:
				case COLOUR_HATCHINGS:
				case FILLING_STYLE:		return shapes.isInteriorStylable();

				case ARC_END_ANGLE:
				case ARC_START_ANGLE:
				case ARC_STYLE: return shapes.isTypeOf(IArcProp.class);
				case ROUND_CORNER_VALUE: return shapes.isTypeOf(ILineArcProp.class);

				case COLOUR_LINE:		return true;

			}

		return false;
	}
}
