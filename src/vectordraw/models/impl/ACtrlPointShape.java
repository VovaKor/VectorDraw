
package vectordraw.models.impl;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IControlPointShape;
import vectordraw.models.interfaces.shape.ILine;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.EPosition;

/**
 * An implementation of a abstract shape that contains control points.
 */
abstract class ACtrlPointShape extends AModifiablePointsShape implements IControlPointShape {
	/** The default balance gap used to balance all the points of the bézier curve. */
	protected int DEFAULT_BALANCE_GAP = 50;
	/** This vector contains the points which allows to change the angles of the curves */
	protected final List<IPoint> firstCtrlPts;
	/** Contains the second control points of each points; useful for closed curve. */
	protected final List<IPoint> secondCtrlPts;


	/**
	 * Creates the shape.
	 */
	ACtrlPointShape() {
		super();
		firstCtrlPts = new ArrayList<>();
		secondCtrlPts = new ArrayList<>();
	}


	@Override
	public void scale(final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		super.scale(prevWidth, prevHeight, pos, bound);
		scaleSetPoints(firstCtrlPts, prevWidth, prevHeight, pos, bound);
		scaleSetPoints(secondCtrlPts, prevWidth, prevHeight, pos, bound);
	}

	@Override
	public void scaleWithRatio(final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		super.scaleWithRatio(prevWidth, prevHeight, pos, bound);
		scaleSetPointsWithRatio(firstCtrlPts, prevWidth, prevHeight, pos, bound);
		scaleSetPointsWithRatio(secondCtrlPts, prevWidth, prevHeight, pos, bound);
	}

	/**
	 * Method used by the balance method. Just returns the balanced control points of the given points.
	 */
	private IPoint[] getBalancedPoints(final IPoint pt, final IPoint prevPt, final IPoint nextPt) {
		final ILine line = ShapeFactory.INST.createLine(prevPt, nextPt);

		if(line.isHorizontalLine()) {
			line.setLine(pt.getX(), pt.getY(), pt.getX() + 10.0, pt.getY());
		}else {
			final double b = pt.getY() - line.getA() * pt.getX();
			line.setLine(pt.getX(), pt.getY(), pt.getX() + 10.0, line.getA() * (pt.getX() + 10.0) + b);
		}

		return line.findPoints(pt, DEFAULT_BALANCE_GAP);
	}


	/**
	 * Method used by the balance method. Just sets the given control points at the given position.
	 */
	private void setControlPoints(final int position, final IPoint[] ctrlPts) {
		if(ctrlPts == null || ctrlPts.length != 2) return;

		// If there exists an intersection point between the two lines created using control points and points,
		// where is a loop that must be removed by inverting the control points.
		// For the first point, the lines are created differently.
		final int posPrev = position == 0 ? 1 : position - 1;
		final int posNext = position == 0 ? points.size() - 1 : position == points.size() - 1 ? 0 : position + 1;
		final ILine line1 = ShapeFactory.INST.createLine(getPtAt(posPrev), ctrlPts[0]);
		final ILine line2 = ShapeFactory.INST.createLine(getPtAt(posNext), ctrlPts[1]);

		if(line1.getIntersectionSegment(line2) == null) {
			firstCtrlPts.get(position).setPoint(ctrlPts[0]);
			secondCtrlPts.get(position).setPoint(ctrlPts[1]);
		}else {
			firstCtrlPts.get(position).setPoint(ctrlPts[1]);
			secondCtrlPts.get(position).setPoint(ctrlPts[0]);
		}
	}


	@Override
	public void balance() {
		final int size = getNbPoints();

		if(size < 3) return;//Works only with more than 2 points.

		IPoint ptPrev;
		IPoint ptNext;

		// Balancing all the points except the first and the last one.
		for(int i = 1; i < size - 1; i++) {
			ptPrev = points.get(i - 1);
			ptNext = points.get(i + 1);
			setControlPoints(i, getBalancedPoints(points.get(i), ptPrev, ptNext));
		}

		// Balancing the first and the last points.
		ptPrev = points.get(size - 1);
		ptNext = points.get(1);
		setControlPoints(0, getBalancedPoints(points.get(0), ptPrev, ptNext));
		ptPrev = points.get(size - 2);
		ptNext = points.get(0);
		setControlPoints(size - 1, getBalancedPoints(points.get(size - 1), ptPrev, ptNext));
	}


	@Override
	public IPoint getFirstCtrlPtAt(final int position) {
		if(firstCtrlPts.isEmpty() || position < -1 || position >= firstCtrlPts.size()) {
			return null;
		}
		return position == -1 ? firstCtrlPts.get(firstCtrlPts.size() - 1) : firstCtrlPts.get(position);
	}


	@Override
	public List<IPoint> getFirstCtrlPts() {
		return firstCtrlPts;
	}


	@Override
	public IPoint getSecondCtrlPtAt(final int position) {
		if(secondCtrlPts.isEmpty() || position < -1 || position >= secondCtrlPts.size()) {
			return null;
		}
		return position == -1 ? secondCtrlPts.get(secondCtrlPts.size() - 1) : secondCtrlPts.get(position);
	}


	@Override
	public List<IPoint> getSecondCtrlPts() {
		return secondCtrlPts;
	}


	@Override
	public void setXFirstCtrlPt(final double x, final int id) {
		if(MathUtils.INST.isValidCoord(x) && id >= 0 && id < firstCtrlPts.size()) {
			firstCtrlPts.get(id).setX(x);
		}
	}


	@Override
	public void setXSecondCtrlPt(final double x, final int id) {
		if(MathUtils.INST.isValidCoord(x) && id >= 0 && id < secondCtrlPts.size()) {
			secondCtrlPts.get(id).setX(x);
		}
	}


	@Override
	public void setYFirstCtrlPt(final double y, final int id) {
		if(MathUtils.INST.isValidCoord(y) && id >= 0 && id < firstCtrlPts.size()) {
			firstCtrlPts.get(id).setY(y);
		}
	}


	@Override
	public void setYSecondCtrlPt(final double y, final int id) {
		if(MathUtils.INST.isValidCoord(y) && id >= 0 && id < secondCtrlPts.size()) {
			secondCtrlPts.get(id).setY(y);
		}
	}


	@Override
	public void updateSecondControlPoints() {
		for(int i = 0, size = points.size(); i < size; i++) {
			secondCtrlPts.get(i).setPoint(firstCtrlPts.get(i).centralSymmetry(points.get(i)));
		}
	}


	@Override
	public boolean setPoint(final double x, final double y, final int position) {
		final IPoint pt = getPtAt(position);

		if(pt == null || !MathUtils.INST.isValidPt(x, y)) {
			return false;
		}

		final double tx = x - pt.getX();
		final double ty = y - pt.getY();
		getFirstCtrlPtAt(position).translate(tx, ty);
		getSecondCtrlPtAt(position).translate(tx, ty);
		super.setPoint(x, y, position);

		return true;
	}


	@Override
	public void setRotationAngle(final double angle) {
		if(MathUtils.INST.isValidCoord(angle)) {
			final double diff = angle - this.rotationAngle;
			final IPoint gc = getGravityCentre();

			super.setRotationAngle(angle);
			firstCtrlPts.forEach(pt -> pt.setPoint(pt.rotatePoint(gc, diff)));
			updateSecondControlPoints();
		}
	}


	@Override
	public boolean removePoint(final IPoint pt) {
		return removePoint(points.indexOf(pt)) != null;
	}


	@Override
	public IPoint removePoint(final int position) {
		final IPoint deleted = super.removePoint(position);

		if(deleted != null) {
			firstCtrlPts.remove(position == -1 ? firstCtrlPts.size() - 1 : position);
			secondCtrlPts.remove(position == -1 ? secondCtrlPts.size() - 1 : position);
		}

		return deleted;
	}


	@Override
	public void addPoint(final IPoint pt, final int position) {
		// Adding the control points.
		if(MathUtils.INST.isValidPt(pt) && position >= -1 && position < points.size()+1) {
			final IPoint ctrlPt = ShapeFactory.INST.createPoint(pt.getX(), pt.getY() + DEFAULT_POSITION_CTRL);
			if(position == -1) {
				firstCtrlPts.add(ctrlPt);
				secondCtrlPts.add(ctrlPt.centralSymmetry(pt));
			}else {
				firstCtrlPts.add(position, ctrlPt);
				secondCtrlPts.add(position, ctrlPt.centralSymmetry(pt));
			}

			super.addPoint(pt, position);
		}
	}


	@Override
	protected void copyPoints(final IShape sh) {
		if(sh instanceof IControlPointShape) {
			final IControlPointShape cpSh = (IControlPointShape) sh;
			List<IPoint> pts = cpSh.getFirstCtrlPts();

			firstCtrlPts.clear();
			pts.forEach(pt -> firstCtrlPts.add(ShapeFactory.INST.createPoint(pt)));
			pts = cpSh.getSecondCtrlPts();
			secondCtrlPts.clear();
			pts.forEach(pt -> secondCtrlPts.add(ShapeFactory.INST.createPoint(pt)));
		}

		super.copyPoints(sh);
	}


	@Override
	public void translate(final double tx, final double ty) {
		// Translating control points.
		if(MathUtils.INST.isValidPt(tx, ty)) {
			firstCtrlPts.forEach(pt -> pt.translate(tx, ty));
			secondCtrlPts.forEach(pt -> pt.translate(tx, ty));
			super.translate(tx, ty);
		}
	}
}
