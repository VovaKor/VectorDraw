
package vectordraw.models.impl;

import java.util.ArrayList;
import java.util.List;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IBezierCurve;
import vectordraw.models.interfaces.shape.ILine;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;

/**
 * An implementation of a Bezier curve.
 */
class VDBezierCurve extends ACtrlPointShape implements IBezierCurve {
	
	private boolean isClosed;

	VDBezierCurve(final boolean closed) {
		super();
		isClosed = closed;
	}

	/**
	 * Creates a bezier curve with two points.
	 * @param point The first point of the curve.
	 * @param point2 The second point of the curve.
	 */
	VDBezierCurve(final IPoint point, final IPoint point2, final boolean closed) {
		this(closed);
		addPoint(point);
		addPoint(point2);
	}

	

	@Override
	public boolean isClosed() {
		return isClosed;
	}

	@Override
	public void setIsClosed(final boolean closed) {
		isClosed = closed;
	}

	@Override
	public boolean isDbleBorderable() {
		return true;
	}

	@Override
	public boolean isFillable() {
		return true;
	}

	@Override
	public boolean isInteriorStylable() {
		return true;
	}

	@Override
	public void copy(final IShape sh) {
		super.copy(sh);
		
		if(sh instanceof IBezierCurve) {
			setIsClosed(((IBezierCurve) sh).isClosed());
		}
	}

	@Override
	public boolean isLineStylable() {
		return true;
	}

	@Override
	public boolean isShadowable() {
		return true;
	}

	@Override
	public boolean isThicknessable() {
		return true;
	}

	@Override
	public boolean shadowFillsShape() {
		return false;
	}

	@Override
	public boolean isShowPtsable() {
		return true;
	}

}
