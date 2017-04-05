
package vectordraw.actions.shape;

import java.util.Optional;
import vectordraw.actions.AShapeActionImpl;
import vectordraw.util.MathUtils;
import vectordraw.models.interfaces.shape.IDrawing;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.util.LangTool;
import org.malai.undo.Undoable;
import vectordraw.actions.IDrawingAction;
import vectordraw.actions.IModifying;

/**
 * This action translates shapes.
 */
public class TranslateShapes extends AShapeActionImpl<IGroup> implements IDrawingAction, Undoable, IModifying {
	/** The x vector translation. */
	double tx;

	/** The y vector translation. */
	double ty;

	/**
	 * The x vector translation that has been already performed. This attribute is needed since
	 * this action can be executed several times.
	 */
	double performedTx;

	/**
	 * The y vector translation that has been already performed. This attribute is needed since
	 * this action can be executed several times.
	 */
	double performedTy;

	/** The drawing that will be handled by the action. */
	protected Optional<IDrawing> drawing;


	public TranslateShapes() {
		super();
		drawing = Optional.empty();
	}

	@Override
	public boolean isRegisterable() {
		return hadEffect();
	}

	@Override
	public boolean hadEffect() {
		return !MathUtils.INST.equalsDouble(performedTx, 0.0) || !MathUtils.INST.equalsDouble(performedTy, 0.0);
	}

	@Override
	protected void doActionBody() {
		shape.ifPresent(sh -> drawing.ifPresent(dr -> {
			if(!MathUtils.INST.equalsDouble(tx - performedTx, 0.0) || !MathUtils.INST.equalsDouble(ty - performedTy, 0.0)) {
				sh.translate(tx - performedTx, ty - performedTy);
				sh.setModified(true);
				dr.setModified(true);
				performedTx = tx;
				performedTy = ty;
			}
		}));
	}

	@Override
	public boolean canDo() {
		return super.canDo() && drawing.isPresent() && !shape.get().isEmpty() && MathUtils.INST.isValidPt(tx, ty);
	}

	@Override
	public void undo() {
		shape.ifPresent(sh -> drawing.ifPresent(dr -> {
			sh.translate(-tx, -ty);
			sh.setModified(true);
			dr.setModified(true);
		}));
	}

	@Override
	public void redo() {
		shape.ifPresent(sh -> drawing.ifPresent(dr -> {
			sh.translate(tx, ty);
			sh.setModified(true);
			dr.setModified(true);
		}));
	}

	@Override
	public String getUndoName() {
		return LangTool.INSTANCE.getBundle().getString("Actions.32");
	}

	/**
	 * @param theTx The x vector translation.
	 */
	public void setTx(final double theTx) {
		tx = theTx;
	}

	/**
	 * @param theTy The y vector translation.
	 */
	public void setTy(final double theTy) {
		ty = theTy;
	}

	@Override
	public void setDrawing(final IDrawing dr) {
		drawing = Optional.ofNullable(dr);
	}

	@Override
	public Optional<IDrawing> getDrawing() {
		return drawing;
	}
}
