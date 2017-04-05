package vectordraw.actions;


import vectordraw.actions.shape.AShapePropertyAction;
import vectordraw.controllers.PencilInstrument;
import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.EBorderPos;
import vectordraw.models.interfaces.shape.EFillingStyle;
import vectordraw.models.interfaces.shape.ELineStyle;
import vectordraw.models.interfaces.shape.IColor;


/**
 * This action modifies a parameter of the pencil and updates its corresponding instrument.
 */
public class ModifyPencilParameter extends AShapePropertyAction {
	/** The pencil to modify. */
	protected PencilInstrument pencil;


    @Override
	public void flush() {
		super.flush();
		pencil = null;
	}


	@Override
	public boolean canDo() {
		return super.canDo() && pencil!=null;
	}


	@Override
	protected boolean isPropertySupported() {
	return super.isPropertySupported();	
//           
	}


	@Override
	protected void doActionBody() {
		// Modification of the pencil.
		applyValue(value);
	}


	@Override
	public boolean isRegisterable() {
		return false;
	}


	/**
	 * Defines the pencil to modify.
	 * @param pen The pencil to modify.
	 * *
	 */
	public void setPencil(final PencilInstrument pen) {
		pencil = pen;
	}


	@Override
	protected void applyValue(final Object obj) {
		switch(property) {
			case BORDER_POS:
				pencil.getGroupParams().setBordersPosition((EBorderPos) value);
				break;
			case COLOUR_DBLE_BORD:
				pencil.getGroupParams().setDbleBordCol((IColor) value);
				break;
			case COLOUR_FILLING:
				pencil.getGroupParams().setFillingCol((IColor) value);
				break;
			case COLOUR_GRADIENT_END:
				pencil.getGroupParams().setGradColEnd((IColor) value);
				break;
			case COLOUR_GRADIENT_START:
				pencil.getGroupParams().setGradColStart((IColor) value);
				break;
			case COLOUR_HATCHINGS:
				pencil.getGroupParams().setHatchingsCol((IColor) value);
				break;
			case COLOUR_LINE:
				pencil.getGroupParams().setLineColour((IColor) value);
				break;
			case SHADOW_COLOUR:
				pencil.getGroupParams().setShadowCol((IColor) value);
				break;
			case DBLE_BORDERS:
				pencil.getGroupParams().setHasDbleBord((Boolean) value);
				break;
//			
			case FILLING_STYLE:
				pencil.getGroupParams().setFillingStyle((EFillingStyle) value);
				break;
			case LINE_STYLE:
				pencil.getGroupParams().setLineStyle((ELineStyle) value);
				break;
			case LINE_THICKNESS:
				pencil.getGroupParams().setThickness((Double) value);
				break;
			case SHADOW:
				pencil.getGroupParams().setHasShadow((Boolean) value);
				break;
			case ROUND_CORNER_VALUE:
				pencil.getGroupParams().setLineArc((Double)value);
				break;
			case DBLE_BORDERS_SIZE:
				pencil.getGroupParams().setDbleBordSep((Double)value);
				break;
			case SHADOW_ANGLE:
				pencil.getGroupParams().setShadowAngle((Double)value);
				break;
			case SHADOW_SIZE:
				pencil.getGroupParams().setShadowSize((Double)value);
				break;
			
			case HATCHINGS_ANGLE:
				pencil.getGroupParams().setHatchingsAngle((Double)value);
				break;
			case HATCHINGS_SEP:
				pencil.getGroupParams().setHatchingsSep((Double)value);
				break;
			case HATCHINGS_WIDTH:
				pencil.getGroupParams().setHatchingsWidth((Double)value);
				break;
//			
			case ARC_END_ANGLE	: pencil.getGroupParams().setAngleEnd((Double)value); 	break;
			case ARC_START_ANGLE: pencil.getGroupParams().setAngleStart((Double)value);	break;
			case ARC_STYLE		: pencil.getGroupParams().setArcStyle((EArcStyle)value);		break;
//			
			case SHOW_POINTS: pencil.getGroupParams().setShowPts((Boolean)value); break;
//			
		}
	}
}
