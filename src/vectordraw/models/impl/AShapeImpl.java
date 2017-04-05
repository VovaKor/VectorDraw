package vectordraw.models.impl;

import java.awt.geom.Rectangle2D;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.EBorderPos;
import vectordraw.models.interfaces.shape.EFillingStyle;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.ISingleShape;
import vectordraw.models.interfaces.shape.ELineStyle;
import vectordraw.models.interfaces.shape.EPosition;
import vectordraw.util.Constants;

import org.malai.mapping.MappingRegistry;
import vectordraw.models.interfaces.shape.IColor;

/**
 * The base shape model.
 */
abstract class AShapeImpl implements ISingleShape {
	/** The thickness of the lines of the shape in pixels. */
	protected final  DoubleProperty thickness;

	/** The colour of the lines. */
	protected final  ObjectProperty<IColor> lineColour;

	/** The style of the lines. */
	protected final  ObjectProperty<ELineStyle> lineStyle;

	/** The style of the interior of the shape. */
	protected final  ObjectProperty<EFillingStyle> fillingStyle;

	/** The white dash separator for dashed lines in pixel. */
	protected final  DoubleProperty dashSepWhite;

	/** The black dash separator for dashed lines in pixel. */
	protected final  DoubleProperty dashSepBlack;

	/** The dot separator for dotted lines. */
	protected final  DoubleProperty dotSep;

	/** The colour of the interior of the shape. */
	protected final  ObjectProperty<IColor> fillingCol;

	/** The start colour of the gradient. */
	protected final  ObjectProperty<IColor> gradColStart;

	/** The end colour of the gradient. */
	protected final  ObjectProperty<IColor> gradColEnd;

	/** The angle of the gradient in radian. */
	protected final  DoubleProperty gradAngle;

	/** The middle point of the gradient. */
	protected final  DoubleProperty gradMidPt;

	/** The separation size between hatchings in pixel. */
	protected final  DoubleProperty hatchingsSep;

	/** The colour of the hatchings. */
	protected final  ObjectProperty<IColor> hatchingsCol;

	/** The angle of the hatchings in radian. */
	protected final  DoubleProperty hatchingsAngle;

	/** The thickness of the hatchings in pixel. */
	protected final  DoubleProperty hatchingsWidth;

	/** The rotation angle of the shape. */
	protected double rotationAngle;

	/** Defines if the points of the shape must be considered. */
	protected boolean showPts;

	/** Defines if the shape has double borders. */
	protected final  BooleanProperty hasDbleBord;

	/** The colour of the double borders. */
	protected final  ObjectProperty<IColor> dbleBordCol;

	/** The separation size of the double borders in pixel. */
	protected final  DoubleProperty dbleBordSep;

	/** Defines if the shape has a shadow. */
	protected final  BooleanProperty hasShadow;

	/** The colour of the shadow. */
	protected final  ObjectProperty<IColor> shadowCol;

	/** The angle of the shadow in radian. */
	protected final  DoubleProperty shadowAngle;

	/** The size of the shadow in pixel. */
	protected final  DoubleProperty shadowSize;

	/** The position of the border of the shape. */
	protected final  ObjectProperty<EBorderPos> bordersPosition;

	/** The points of the shape. */
	protected final ObservableList<IPoint> points;

	/** Defined if the shape has been modified. */
	protected boolean modified;

	/**
	 * The second default constructor
	 */
	AShapeImpl() {
		super();
		modified = false;
		thickness = new SimpleDoubleProperty(2d);
		rotationAngle = 0d;
		shadowAngle = new SimpleDoubleProperty(-Math.PI / 4d);
		gradAngle = new SimpleDoubleProperty(0d);
		hatchingsAngle = new SimpleDoubleProperty(0d);
		hasShadow = new SimpleBooleanProperty(false);
		hasDbleBord = new SimpleBooleanProperty(false);
		lineStyle = new SimpleObjectProperty<>(ELineStyle.SOLID);
		lineColour = new SimpleObjectProperty<>(Constants.DEFAULT_LINE_COLOR);
		dotSep = new SimpleDoubleProperty(Constants.DEFAULT_DOT_STEP * PPC);
		dashSepBlack = new SimpleDoubleProperty(Constants.DEFAULT_DASH_BLACK * PPC);
		dashSepWhite = new SimpleDoubleProperty(Constants.DEFAULT_DASH_WHITE * PPC);
		hatchingsCol = new SimpleObjectProperty<>(Constants.DEFAULT_HATCHING_COLOR);
		hatchingsSep = new SimpleDoubleProperty(Constants.DEFAULT_HATCH_SEP * PPC);
		hatchingsWidth = new SimpleDoubleProperty(Constants.DEFAULT_HATCH_WIDTH * PPC);
		fillingStyle = new SimpleObjectProperty<>(EFillingStyle.NONE);
		fillingCol = new SimpleObjectProperty<>(Constants.DEFAULT_INTERIOR_COLOR);
		bordersPosition = new SimpleObjectProperty<>(EBorderPos.INTO);
		dbleBordCol = new SimpleObjectProperty<>(Constants.DEFAULT_DOUBLE_COLOR);
		dbleBordSep = new SimpleDoubleProperty(6d);
		shadowCol = new SimpleObjectProperty<>(Constants.DEFAULT_SHADOW_COLOR);
		shadowSize = new SimpleDoubleProperty(Constants.DEFAULT_SHADOW_SIZE * PPC);
		gradColStart = new SimpleObjectProperty<>(Constants.DEFAULT_GRADIENT_START_COLOR);
		gradColEnd = new SimpleObjectProperty<>(Constants.DEFAULT_GRADIENT_END_COLOR);
		gradMidPt = new SimpleDoubleProperty(Constants.DEFAULT_GRADIENT_MID_POINT);
		showPts = false;
		points = FXCollections.observableArrayList();
	}

	@Override
	public double getFullThickness() {
		return isDbleBorderable() && hasDbleBord() ? getThickness() * 2d + getDbleBordSep() : getThickness();
	}

	@Override
	public void copy(final IShape s) {
		if(s == null) return;

		setThickness(s.getThickness());
		setRotationAngle(s.getRotationAngle());
		setShadowAngle(s.getShadowAngle());
		setGradAngle(s.getGradAngle());
		setHatchingsAngle(s.getHatchingsAngle());
		setHasShadow(s.hasShadow());
		setHasDbleBord(s.hasDbleBord());
		setLineStyle(s.getLineStyle());
		setLineColour(s.getLineColour());
		setDotSep(s.getDotSep());
		setDashSepBlack(s.getDashSepBlack());
		setDashSepWhite(s.getDashSepWhite());
		setHatchingsCol(s.getHatchingsCol());
		setHatchingsSep(s.getHatchingsSep());
		setHatchingsWidth(s.getHatchingsWidth());
		setFillingStyle(s.getFillingStyle());
		setFillingCol(s.getFillingCol());
		setBordersPosition(s.getBordersPosition());
		setDbleBordCol(s.getDbleBordCol());
		setDbleBordSep(s.getDbleBordSep());
		setShadowCol(s.getShadowCol());
		setShadowSize(s.getShadowSize());
		setGradColStart(s.getGradColStart());
		setGradColEnd(s.getGradColEnd());
		setGradMidPt(s.getGradMidPt());
		setShowPts(s.isShowPts());

		copyPoints(s);
	}

	protected void copyPoints(final IShape sh) {
		if(sh == null || !getClass().isInstance(sh)) return;
		points.clear();
		sh.getPoints().forEach(pt -> points.add(ShapeFactory.INST.createPoint(pt)));
	}

	@Override
	public void addToRotationAngle(final IPoint gravCentre, final double angle) {
		if(MathUtils.INST.isValidCoord(angle)) {
			setRotationAngle(getRotationAngle() + angle);

			if(gravCentre != null) {
				final IPoint gravityCentre = getGravityCentre();
				final IPoint rotatedGC = gravityCentre.rotatePoint(gravCentre, angle);
				translate(rotatedGC.getX() - gravityCentre.getX(), rotatedGC.getY() - gravityCentre.getY());
			}
		}
	}

	@Override
	public double getBorderGap() {
		switch(bordersPosition.get()) {
			case MID:
				return hasDbleBord() ? thickness.doubleValue() + getDbleBordSep() / 2d : thickness.doubleValue() / 2d;
			case OUT:
				return hasDbleBord() ? thickness.doubleValue() * 2d + getDbleBordSep() : thickness.doubleValue();
			case INTO:
				return 0d;
		}
		return 0d;
	}

	@Override
	public  EBorderPos getBordersPosition() {
		return bordersPosition.get();
	}

	@Override
	public double getDashSepBlack() {
		return dashSepBlack.doubleValue();
	}

	@Override
	public double getDashSepWhite() {
		return dashSepWhite.doubleValue();
	}

	@Override
	public IColor getDbleBordCol() {
		return dbleBordCol.get();
	}

	@Override
	public double getDbleBordSep() {
		return dbleBordSep.doubleValue();
	}

	@Override
	public double getDotSep() {
		return dotSep.doubleValue();
	}

	@Override
	public IColor getFillingCol() {
		return fillingCol.get();
	}

	@Override
	public  EFillingStyle getFillingStyle() {
		return fillingStyle.get();
	}

	@Override
	public IPoint getFullBottomRightPoint() {
		final double gap = getBorderGap();
		final IPoint br = getBottomRightPoint();

		br.translate(gap, gap);

		return br;
	}

	@Override
	public IPoint getFullTopLeftPoint() {
		final double gap = getBorderGap();
		final IPoint tl = getTopLeftPoint();

		tl.translate(-gap, -gap);

		return tl;
	}

	@Override
	public double getGradAngle() {
		return gradAngle.doubleValue();
	}

	@Override
	public IColor getGradColEnd() {
		return gradColEnd.get();
	}

	@Override
	public IColor getGradColStart() {
		return gradColStart.get();
	}

	@Override
	public double getGradMidPt() {
		return gradMidPt.doubleValue();
	}

	@Override
	public IPoint getGravityCentre() {
		return points.isEmpty() ? ShapeFactory.INST.createPoint() : getTopLeftPoint().getMiddlePoint(getBottomRightPoint());
	}

	@Override
	public double getHatchingsAngle() {
		return hatchingsAngle.doubleValue();
	}

	@Override
	public IColor getHatchingsCol() {
		return hatchingsCol.get();
	}

	@Override
	public double getHatchingsSep() {
		return hatchingsSep.doubleValue();
	}

	@Override
	public double getHatchingsWidth() {
		return hatchingsWidth.doubleValue();
	}

	@Override
	public  IColor getLineColour() {
		return lineColour.get();
	}

	@Override
	public  ELineStyle getLineStyle() {// TODO add  to the generics but sbt crashes... 
		return lineStyle.get();
	}

	@Override
	public int getNbPoints() {
		return points.size();
	}

	@Override
	public ObservableList<IPoint> getPoints() {
		return points;
	}

	@Override
	public IPoint getPtAt(final int position) {
		final IPoint point;

		if(points.isEmpty() || position < -1 || position >= points.size()) point = null;
		else point = position == -1 ? points.get(points.size() - 1) : points.get(position);

		return point;
	}

	@Override
	public double getRotationAngle() {
		return rotationAngle;
	}

	@Override
	public double getShadowAngle() {
		return shadowAngle.get();
	}

	@Override
	public IColor getShadowCol() {
		return shadowCol.get();
	}

	@Override
	public double getShadowSize() {
		return shadowSize.get();
	}

	@Override
	public double getThickness() {
		return thickness.doubleValue();
	}

	@Override
	public boolean hasDbleBord() {
		return hasDbleBord.get();
	}

	@Override
	public boolean hasGradient() {
		return isInteriorStylable() && fillingStyle.get() == EFillingStyle.GRAD;
	}

	@Override
	public boolean hasHatchings() {
		return isInteriorStylable() && fillingStyle.get().isHatchings();
	}

	@Override
	public boolean hasShadow() {
		return hasShadow.get();
	}

	@Override
	public boolean isFilled() {
		return fillingStyle.get().isFilled();
	}

	@Override
	public boolean isShowPts() {
		return showPts;
	}

	@Override
	public boolean isColourable() {
		return true;
	}

	

	@Override
	public void setBordersPosition(final EBorderPos position) {
		if(position != null && isBordersMovable()) bordersPosition.set(position);
	}

	@Override
	public void setDashSepBlack(final double dash) {
		if(dash > 0d && MathUtils.INST.isValidCoord(dash)) {
			dashSepBlack.set(dash);
		}
	}

	@Override
	public void setDashSepWhite(final double dash) {
		if(dash > 0d && MathUtils.INST.isValidCoord(dash)) {
			dashSepWhite.set(dash);
		}
	}

	@Override
	public void setDbleBordCol(final IColor col) {
		if(col != null && isDbleBorderable()) {
			dbleBordCol.set(col);
		}
	}

	@Override
	public void setDbleBordSep(final double sep) {
		if(sep >= 0 && isDbleBorderable() && MathUtils.INST.isValidCoord(sep)) {
			dbleBordSep.set(sep);
		}
	}

	@Override
	public void setDotSep(final double sep) {
		if(sep >= 0 && MathUtils.INST.isValidCoord(sep)) {
			dotSep.set(sep);
		}
	}

	@Override
	public void setFilled(final boolean isFilled) {
		if(!isFillable()) return;

		if(isFilled) switch(fillingStyle.get()) {
			case CLINES:
				fillingStyle.set(EFillingStyle.CLINES_PLAIN);
				break;
			case VLINES:
				fillingStyle.set(EFillingStyle.VLINES_PLAIN);
				break;
			case HLINES:
				fillingStyle.set(EFillingStyle.HLINES_PLAIN);
				break;
			case NONE:
				fillingStyle.set(EFillingStyle.PLAIN);
				break;
			case PLAIN:
			case GRAD:
			case CLINES_PLAIN:
			case HLINES_PLAIN:
			case VLINES_PLAIN:
					/* Nothing to do. */
				break;
		}
		else switch(fillingStyle.get()) {
			case CLINES_PLAIN:
				fillingStyle.set(EFillingStyle.CLINES);
				break;
			case VLINES_PLAIN:
				fillingStyle.set(EFillingStyle.VLINES);
				break;
			case HLINES_PLAIN:
				fillingStyle.set(EFillingStyle.HLINES);
				break;
			case PLAIN:
				fillingStyle.set(EFillingStyle.NONE);
				break;
			case NONE:
			case GRAD:
			case CLINES:
			case HLINES:
			case VLINES:
					/* Nothing to do. */
				break;
		}
	}

	@Override
	public void setFillingCol(final IColor col) {
		if(col != null && isFillable()) {
			fillingCol.set(col);
		}
	}

	@Override
	public void setFillingStyle(final EFillingStyle style) {
		if(style != null && isFillable()) fillingStyle.set(style);
	}

	@Override
	public void setGradAngle(final double angle) {
		if(MathUtils.INST.isValidCoord(angle) && isInteriorStylable()) {
			gradAngle.set(angle);
		}
	}

	@Override
	public void setGradColEnd(final IColor col) {
		if(col != null && isInteriorStylable()) {
			gradColEnd.set(col);
		}
	}

	@Override
	public void setGradColStart(final IColor col) {
		if(col != null && isInteriorStylable()) {
			gradColStart.set(col);
		}
	}

	@Override
	public void setGradMidPt(final double pt) {
		if(pt >= 0 && pt <= 1 && isInteriorStylable()) {
			gradMidPt.set(pt);
		}
	}

	@Override
	public void setHasDbleBord(final boolean bord) {
		if(isDbleBorderable()) {
			hasDbleBord.set(bord);
		}
	}

	@Override
	public void setHasShadow(final boolean shad) {
		if(isShadowable()) {
			hasShadow.set(shad);
		}
	}

	@Override
	public void setHatchingsAngle(final double angle) {
		if(MathUtils.INST.isValidCoord(angle) && isInteriorStylable()) {
			hatchingsAngle.set(angle);
		}
	}

	@Override
	public void setHatchingsCol(final IColor col) {
		if(col != null && isInteriorStylable()) {
			hatchingsCol.set(col);
		}
	}

	@Override
	public void setHatchingsSep(final double sep) {
		if(MathUtils.INST.isValidCoord(sep) && sep >= 0d && isInteriorStylable()) {
			hatchingsSep.set(sep);
		}
	}

	@Override
	public void setHatchingsWidth(final double width) {
		if(MathUtils.INST.isValidCoord(width) && width > 0d && isInteriorStylable()) {
			hatchingsWidth.set(width);
		}
	}

	@Override
	public void setLineColour(final IColor col) {
		if(col != null) {
			lineColour.set(col);
		}
	}

	@Override
	public void setLineStyle(final ELineStyle style) {
		if(style != null && isLineStylable()) {
			lineStyle.setValue(style);
		}
	}

	@Override
	public void scale(final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		if(bound == null || pos == null) return;
		scaleSetPoints(points, prevWidth, prevHeight, pos, bound);
	}

	@Override
	public void scaleWithRatio(final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		if(bound == null || pos == null) return;
		scaleSetPointsWithRatio(points, prevWidth, prevHeight, pos, bound);
	}

	protected void scaleSetPointsWithRatio(final List<IPoint> pts, final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		final double s = Math.max(prevWidth / bound.getWidth(), prevHeight / bound.getHeight());
		final IPoint refPt = pos.getReferencePoint(bound);
		final double refX = refPt.getX();
		final double refY = refPt.getY();

		for(final IPoint pt : pts) {
			if(!MathUtils.INST.equalsDouble(pt.getX(), refX)) pt.setX(refX + (pt.getX() - refX) * s);
			if(!MathUtils.INST.equalsDouble(pt.getY(), refY)) pt.setY(refY + (pt.getY() - refY) * s);
		}
	}

	protected void scaleSetPoints(final List<IPoint> pts, final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		final double sx = prevWidth / bound.getWidth();
		final double sy = prevHeight / bound.getHeight();
		final boolean xScale = pos.isEast() || pos.isWest();
		final boolean yScale = pos.isNorth() || pos.isSouth();
		final IPoint refPt = pos.getReferencePoint(bound);
		final double refX = refPt.getX();
		final double refY = refPt.getY();

		pts.forEach(pt -> {
			if(xScale && !MathUtils.INST.equalsDouble(pt.getX(), refX)) pt.setX(refX + (pt.getX() - refX) * sx);
			if(yScale && !MathUtils.INST.equalsDouble(pt.getY(), refY)) pt.setY(refY + (pt.getY() - refY) * sy);
		});
	}

	@Override
	public void setRotationAngle(final double angle) {
		if(MathUtils.INST.isValidCoord(angle)) rotationAngle = angle;
	}

	@Override
	public void setShadowAngle(final double angle) {
		if(isShadowable() && MathUtils.INST.isValidCoord(angle)) {
			shadowAngle.set(angle);
		}
	}

	@Override
	public void setShadowCol(final IColor col) {
		if(col != null && isShadowable()) {
			shadowCol.set(col);
		}
	}

	@Override
	public void setShadowSize(final double size) {
		if(isShadowable() && size > 0 && MathUtils.INST.isValidCoord(size)) {
			shadowSize.set(size);
		}
	}

	@Override
	public void setShowPts(final boolean pts) {
		if(isShowPtsable()) showPts = pts;
	}

	@Override
	public void setThickness(final double thick) {
		if(thick > 0 && isThicknessable() && MathUtils.INST.isValidCoord(thick)) {
			thickness.setValue(thick);
		}
	}

	@Override
	public boolean shadowFillsShape() {
		return true;
	}

	@Override
	public void translate(final double tx, final double ty) {
		if(MathUtils.INST.isValidPt(tx, ty)) points.forEach(pt -> pt.translate(tx, ty));
	}

	@Override
	public void mirrorHorizontal(final IPoint origin) {
		if(MathUtils.INST.isValidPt(origin)) points.forEach(pt -> pt.setPoint(pt.horizontalSymmetry(origin)));
	}

	@Override
	public void mirrorVertical(final IPoint origin) {
		if(MathUtils.INST.isValidPt(origin)) points.forEach(pt -> pt.setPoint(pt.verticalSymmetry(origin)));
	}

	@Override
	public IPoint getBottomRightPoint() {
		return points.stream().reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()))).orElse(ShapeFactory.INST.createPoint());
	}

	@Override
	public IPoint getBottomLeftPoint() {
		return points.stream().reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.min(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()))).orElse(ShapeFactory.INST.createPoint());
	}

	@Override
	public IPoint getTopLeftPoint() {
		return points.stream().reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()))).orElse(ShapeFactory.INST.createPoint());
	}

	@Override
	public IPoint getTopRightPoint() {
		return points.stream().reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.max(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()))).orElse(ShapeFactory.INST.createPoint());
	}

	@Override
	public <T extends IShape> T duplicate() {
		final T shape = ShapeFactory.INST.newShape((Class<T>)getClass()).get();
		shape.copy(this);
		return shape;
	}

	@Override
	public void setModified(final boolean changed) {
		if(changed) MappingRegistry.REGISTRY.onObjectModified(this);

		modified = changed;
	}

	@Override
	public boolean isModified() {
		return modified;
	}

	@Override
	public void rotate(final IPoint point, final double angle) {
		final IPoint gc = getGravityCentre();

		if(point != null && !gc.equals(point)) {// The position of the shape must be rotated.
			final IPoint rotGC = gc.rotatePoint(point, angle);
			translate(rotGC.getX() - gc.getX(), rotGC.getY() - gc.getY());
		}

		setRotationAngle(rotationAngle + angle);
	}

	@Override
	public boolean isBordersMovable() {
		return false;
	}

	@Override
	public boolean isDbleBorderable() {
		return false;
	}

	@Override
	public boolean isFillable() {
		return false;
	}

	@Override
	public boolean isInteriorStylable() {
		return false;
	}

	@Override
	public boolean isLineStylable() {
		return false;
	}

	@Override
	public boolean isShadowable() {
		return false;
	}

	@Override
	public boolean isShowPtsable() {
		return false;
	}

	@Override
	public boolean isThicknessable() {
		return false;
	}

	@Override
	public boolean isTypeOf(final Class<?> clazz) {
		return clazz != null && clazz.isInstance(this);
	}

	@Override
	public double getHeight() {
		return Math.abs(getBottomLeftPoint().getY() - getTopLeftPoint().getY());
	}

	@Override
	public double getWidth() {
		return Math.abs(getTopRightPoint().getX() - getTopLeftPoint().getX());
	}

	@Override
	public  DoubleProperty thicknessProperty() {
		return thickness;
	}

	@Override
	public  ObjectProperty<ELineStyle> linestyleProperty() {
		return lineStyle;
	}

	@Override
	public  ObjectProperty<EBorderPos> borderPosProperty() {
		return bordersPosition;
	}

	@Override
	public  ObjectProperty<IColor> lineColourProperty() {
		return lineColour;
	}

	@Override
	public  ObjectProperty<EFillingStyle> fillingProperty() {
		return fillingStyle;
	}

	@Override
	public  DoubleProperty dashSepWhiteProperty() {
		return dashSepWhite;
	}

	@Override
	public  DoubleProperty dashSepBlackProperty() {
		return dashSepBlack;
	}

	@Override
	public  DoubleProperty dotSepProperty() {
		return dotSep;
	}

	@Override
	public  BooleanProperty dbleBordProperty() {
		return hasDbleBord;
	}

	@Override
	public  DoubleProperty dbleBordSepProperty() {
		return dbleBordSep;
	}

	@Override
	public  ObjectProperty<IColor> dbleBordColProperty() {
		return dbleBordCol;
	}

	@Override
	public  ObjectProperty<IColor> gradColStartProperty() {
		return gradColStart;
	}

	@Override
	public  ObjectProperty<IColor> gradColEndProperty() {
		return gradColEnd;
	}

	@Override
	public  ObjectProperty<IColor> fillingColProperty() {
		return fillingCol;
	}

	@Override
	public  DoubleProperty gradAngleProperty() {
		return gradAngle;
	}

	@Override
	public  DoubleProperty gradMidPtProperty() {
		return gradMidPt;
	}

	@Override
	public  BooleanProperty shadowProperty() {
		return hasShadow;
	}

	@Override
	public  ObjectProperty<IColor> shadowColProperty() {
		return shadowCol;
	}

	@Override
	public  DoubleProperty shadowAngleProperty() {
		return shadowAngle;
	}

	@Override
	public  DoubleProperty shadowSizeProperty() {
		return shadowSize;
	}

	@Override
	public  DoubleProperty hatchingsAngleProperty() {
		return hatchingsAngle;
	}

	@Override
	public  DoubleProperty hatchingsSepProperty() {
		return hatchingsSep;
	}

	@Override
	public  DoubleProperty hatchingsWidthProperty() {
		return hatchingsWidth;
	}

	@Override
	public  ObjectProperty<IColor> hatchingsColProperty() {
		return hatchingsCol;
	}
}
