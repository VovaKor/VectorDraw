package vectordraw.models.impl;

import java.awt.geom.Rectangle2D;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.EBorderPos;
import vectordraw.models.interfaces.shape.EFillingStyle;

import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.ISquaredShape;

import vectordraw.models.interfaces.shape.ELineStyle;
import vectordraw.models.interfaces.shape.EPosition;
import vectordraw.util.Constants;
import vectordraw.models.interfaces.shape.IColor;

/**
 * This trait encapsulates the code of the group related to the support of the general shape's properties.
 */
interface IGroupShape extends IGroup {
	@Override
	default void copy(final IShape sh) {
		//TODO
	}

	@Override
	default double getBorderGap() {
		return isEmpty() ? 0d : getShapes().get(0).getBorderGap();
	}

	@Override
	default double getDashSepBlack() {
		return getShapes().stream().filter(sh -> sh.isLineStylable()).findFirst().map(sh -> sh.getDashSepBlack()).orElse(Double.NaN);
	}

	@Override
	default double getDashSepWhite() {
		return getShapes().stream().filter(sh -> sh.isLineStylable()).findFirst().map(sh -> sh.getDashSepWhite()).orElse(Double.NaN);
	}

	@Override
	default double getDotSep() {
		return getShapes().stream().filter(sh -> sh.isLineStylable()).findFirst().map(sh -> sh.getDotSep()).orElse(Double.NaN);
	}

	@Override
	default IPoint getFullBottomRightPoint() {
		final double gap = getBorderGap();
		final IPoint br = getBottomRightPoint();
		br.translate(gap, gap);
		return br;
	}

	@Override
	default IPoint getFullTopLeftPoint() {
		final double gap = getBorderGap();
		final IPoint tl = getTopLeftPoint();
		tl.translate(-gap, -gap);
		return tl;
	}

	@Override
	default double getHeight() {
            final ObservableList<IShape> selection = getShapes();
		if(selection.isEmpty()) {
			return 0d;
		}
		else {
			final Rectangle2D rec = selection.parallelStream().filter(vi -> vi!=null).map(vi -> {
				IPoint point = vi.getTopLeftPoint();
//                            Bounds b = vi.getBoundsInLocal();
				return (Rectangle2D) new Rectangle2D.Double(point.getX(), point.getY(), vi.getWidth(), vi.getHeight());
			}).reduce(Rectangle2D::createUnion).orElse(new Rectangle2D.Double());

			
			return rec.getHeight();
			
		}
//		return isEmpty() ? 0d : getShapes().get(0).getHeight();
	}

	@Override
	default int getNbPoints() {
		return 0;
	}

	@Override
	default ObservableList<IPoint> getPoints() {
		return FXCollections.emptyObservableList();
	}

	@Override
	default IPoint getPtAt(final int index) {
		return null;
	}

	@Override
	default double getWidth() {
            final ObservableList<IShape> selection = getShapes();
		if(selection.isEmpty()) {
			return 0d;
		}
		else {
			final Rectangle2D rec = selection.parallelStream().filter(vi -> vi!=null).map(vi -> {
				IPoint point = vi.getTopLeftPoint();
//                            Bounds b = vi.getBoundsInLocal();
				return (Rectangle2D) new Rectangle2D.Double(point.getX(), point.getY(), vi.getWidth(), vi.getHeight());
			}).reduce(Rectangle2D::createUnion).orElse(new Rectangle2D.Double());

			
			return rec.getWidth();
			
		}
//		return isEmpty() ? 0d : getShapes().get(0).getWidth();
	}

	@Override
	default void scaleWithRatio(final double x, final double x2, EPosition x3, Rectangle2D x4) {
		//TODO ?
	}

	@Override
	default void setDashSepBlack(final double dash) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setDashSepBlack(dash));
	}

	@Override
	default void setDashSepWhite(final double dash) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setDashSepWhite(dash));
	}

	@Override
	default void setDotSep(final double dot) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setDotSep(dot));
	}

	@Override
	default boolean shadowFillsShape() {
		return getShapes().stream().anyMatch(sh -> sh.shadowFillsShape());
	}

	@Override
	default void mirrorHorizontal(final IPoint origin) {
		getShapes().forEach(sh -> sh.mirrorHorizontal(origin));
	}

	@Override
	default void mirrorVertical(final IPoint origin) {
		getShapes().forEach(sh -> sh.mirrorVertical(origin));
	}

	@Override
	default void setThickness(final double thickness) {
		getShapes().forEach(sh -> sh.setThickness(thickness));
	}

	@Override
	default void scale(final double prevWidth, final double prevHeight, final EPosition pos, final Rectangle2D bound) {
		if(getShapes().stream().anyMatch(sh -> sh instanceof ISquaredShape)) {
			getShapes().forEach(sh -> sh.scaleWithRatio(prevWidth, prevHeight, pos, bound));
		}else {
			getShapes().forEach(sh -> sh.scale(prevWidth, prevHeight, pos, bound));
		}
	}

	@Override
	default double getThickness() {
		return getShapes().stream().filter(sh -> sh.isThicknessable()).map(sh -> sh.getThickness()).findFirst().orElse(Double.NaN);
	}

	@Override
	default double getFullThickness() {
		return getShapes().stream().filter(sh -> sh.isThicknessable()).map(sh -> sh.getFullThickness()).findFirst().orElse(Double.NaN);
	}

	@Override
	default boolean isColourable() {
		return getShapes().stream().anyMatch(sh -> sh.isColourable());
	}

	@Override
	default boolean isThicknessable() {
		return getShapes().stream().anyMatch(sh -> sh.isThicknessable());
	}

	@Override
	default boolean isShowPtsable() {
		return getShapes().stream().anyMatch(sh -> sh.isShowPtsable());
	}

	@Override
	default boolean isShowPts() {
		return getShapes().stream().anyMatch(sh -> sh.isShowPtsable() && sh.isShowPts());
	}

	@Override
	default void setShowPts(final boolean show) {
		getShapes().stream().filter(sh -> sh.isShowPtsable()).forEach(sh -> sh.setShowPts(show));
	}

	@Override
	default IColor getLineColour() {
		return isEmpty() ? Constants.DEFAULT_LINE_COLOR : getShapes().get(0).getLineColour();
	}

	@Override
	default boolean isLineStylable() {
		return getShapes().stream().anyMatch(sh -> sh.isLineStylable());
	}

	@Override
	default ELineStyle getLineStyle() {
		return getShapes().stream().filter(sh -> sh.isLineStylable()).map(sh -> sh.getLineStyle()).findFirst().orElse(ELineStyle.SOLID);
	}

	@Override
	default void setLineStyle(final ELineStyle style) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setLineStyle(style));
	}

	@Override
	default boolean isBordersMovable() {
		return getShapes().stream().anyMatch(sh -> sh.isBordersMovable());
	}

	@Override
	default EBorderPos getBordersPosition() {
		return getShapes().stream().filter(sh -> sh.isBordersMovable()).map(sh -> sh.getBordersPosition()).findFirst().orElse(EBorderPos.INTO);
	}

	@Override
	default void setBordersPosition(final EBorderPos position) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setBordersPosition(position));
	}

	@Override
	default void setLineColour(final IColor lineColour) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setLineColour(lineColour));
	}

	@Override
	default void setDbleBordCol(final IColor colour) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setDbleBordCol(colour));
	}

	@Override
	default IColor getDbleBordCol() {
		return getShapes().stream().filter(sh -> sh.hasDbleBord()).map(sh -> sh.getDbleBordCol()).findFirst().orElse(Constants.DEFAULT_DOUBLE_COLOR);
	}

	@Override
	default boolean hasDbleBord() {
		return getShapes().stream().anyMatch(sh -> sh.isDbleBorderable() && sh.hasDbleBord());
	}

	@Override
	default void setHasDbleBord(final boolean dbleBorders) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setHasDbleBord(dbleBorders));
	}

	@Override
	default boolean isDbleBorderable() {
		return getShapes().stream().anyMatch(sh -> sh.isDbleBorderable());
	}

	@Override
	default void setDbleBordSep(final double dbleBorderSep) {
		getShapes().stream().filter(sh -> sh.isLineStylable()).forEach(sh -> sh.setDbleBordSep(dbleBorderSep));
	}

	@Override
	default double getDbleBordSep() {
		return getShapes().stream().filter(sh -> sh.isDbleBorderable() && sh.hasDbleBord()).map(sh -> sh.getDbleBordSep()).findFirst().orElse(Double.NaN);
	}

	@Override
	default boolean isShadowable() {
		return getShapes().stream().anyMatch(sh -> sh.isShadowable());
	}

	@Override
	default boolean hasShadow() {
		return getShapes().stream().anyMatch(sh -> sh.isShadowable() && sh.hasShadow());
	}

	@Override
	default void setHasShadow(final boolean shadow) {
		getShapes().stream().filter(sh -> sh.isShadowable()).forEach(sh -> sh.setHasShadow(shadow));
	}

	@Override
	default void setShadowSize(final double shadSize) {
		getShapes().stream().filter(sh -> sh.isShadowable()).forEach(sh -> sh.setShadowSize(shadSize));
	}

	@Override
	default double getShadowSize() {
		return getShapes().stream().filter(sh -> sh.isShadowable() && sh.hasShadow()).map(sh -> sh.getShadowSize()).findFirst().orElse(Double.NaN);
	}

	@Override
	default void setShadowAngle(final double shadAngle) {
		getShapes().stream().filter(sh -> sh.isShadowable()).forEach(sh -> sh.setShadowAngle(shadAngle));
	}

	@Override
	default double getShadowAngle() {
		return getShapes().stream().filter(sh -> sh.isShadowable() && sh.hasShadow()).map(sh -> sh.getShadowAngle()).findFirst().orElse(Double.NaN);
	}

	@Override
	default void setShadowCol(final IColor colour) {
		getShapes().stream().filter(sh -> sh.isShadowable()).forEach(sh -> sh.setShadowCol(colour));
	}

	@Override
	default IColor getShadowCol() {
		return getShapes().stream().filter(sh -> sh.isShadowable() && sh.hasShadow()).
			map(sh -> sh.getShadowCol()).findFirst().orElse(Constants.DEFAULT_SHADOW_COLOR);
	}

	@Override
	default EFillingStyle getFillingStyle() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable()).map(sh -> sh.getFillingStyle()).findFirst().orElse(EFillingStyle.NONE);
	}

	@Override
	default void setFillingStyle(final EFillingStyle style) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setFillingStyle(style));
	}

	@Override
	default void setFilled(final boolean filled) {
		getShapes().stream().filter(sh -> sh.isFillable()).forEach(sh -> sh.setFilled(filled));
	}

	@Override
	default boolean isFilled() {
		return getShapes().stream().anyMatch(sh -> sh.isFillable() && sh.isFilled());
	}

	@Override
	default boolean isFillable() {
		return getShapes().stream().anyMatch(sh -> sh.isFillable());
	}

	@Override
	default boolean isInteriorStylable() {
		return getShapes().stream().anyMatch(sh -> sh.isInteriorStylable());
	}

	@Override
	default void setFillingCol(final IColor colour) {
		getShapes().stream().filter(sh -> sh.isFillable()).forEach(sh -> sh.setFillingCol(colour));
	}

	@Override
	default IColor getFillingCol() {
		return getShapes().stream().filter(sh -> sh.isFillable() && sh.isFilled()).
			map(sh -> sh.getFillingCol()).findFirst().orElse(Constants.DEFAULT_FILL_COLOR);
	}

	@Override
	default void setHatchingsCol(final IColor colour) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setHatchingsCol(colour));
	}

	@Override
	default IColor getHatchingsCol() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable() && sh.getFillingStyle().isHatchings()).
			map(sh -> sh.getHatchingsCol()).findFirst().orElse(Constants.DEFAULT_HATCHING_COLOR);
	}

	@Override
	default void setGradColStart(final IColor colour) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setGradColStart(colour));
	}

	@Override
	default IColor getGradColStart() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable() && sh.getFillingStyle().isGradient()).
			map(sh -> sh.getGradColStart()).findFirst().orElse(Constants.DEFAULT_GRADIENT_START_COLOR);
	}

	@Override
	default void setGradColEnd(final IColor colour) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setGradColEnd(colour));
	}

	@Override
	default IColor getGradColEnd() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable() && sh.getFillingStyle().isGradient()).
			map(sh -> sh.getGradColEnd()).findFirst().orElse(Constants.DEFAULT_GRADIENT_END_COLOR);
	}

	@Override
	default void setGradAngle(final double gradAngle) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setGradAngle(gradAngle));
	}

	@Override
	default double getGradAngle() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable() && sh.getFillingStyle().isGradient()).
			map(sh -> sh.getGradAngle()).findFirst().orElse(Double.NaN);
	}

	@Override
	default void setGradMidPt(final double gradMidPoint) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setGradMidPt(gradMidPoint));
	}

	@Override
	default double getGradMidPt() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable() && sh.getFillingStyle().isGradient()).
			map(sh -> sh.getGradMidPt()).findFirst().orElse(Double.NaN);
	}

	@Override
	default double getHatchingsAngle() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable()).map(sh -> sh.getHatchingsAngle()).findFirst().orElse(Double.NaN);
	}

	@Override
	default double getHatchingsSep() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable()).map(sh -> sh.getHatchingsSep()).findFirst().orElse(Double.NaN);
	}

	@Override
	default double getHatchingsWidth() {
		return getShapes().stream().filter(sh -> sh.isInteriorStylable()).map(sh -> sh.getHatchingsWidth()).findFirst().orElse(Double.NaN);
	}

	@Override
	default void setHatchingsAngle(final double hatchingsAngle) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setHatchingsAngle(hatchingsAngle));
	}

	@Override
	default void setHatchingsSep(final double hatchingsSep) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setHatchingsSep(hatchingsSep));
	}

	@Override
	default void setHatchingsWidth(final double hatchingsWidth) {
		getShapes().stream().filter(sh -> sh.isInteriorStylable()).forEach(sh -> sh.setHatchingsWidth(hatchingsWidth));
	}

	@Override
	default void translate(final double tx, final double ty) {
		getShapes().forEach(sh -> sh.translate(tx, ty));
	}

	@Override
	default void addToRotationAngle(final IPoint gravCentre, final double angle) {
		final IPoint gc = getGravityCentre();
		getShapes().forEach(sh -> sh.addToRotationAngle(gc, angle));
	}

	@Override
	default void setRotationAngle(final double rotationAngle) {
		getShapes().forEach(sh -> sh.setRotationAngle(rotationAngle));
	}

	@Override
	default void rotate(final IPoint point, final double angle) {
		getShapes().forEach(sh -> sh.rotate(point, angle));
	}

	@Override
	default double getRotationAngle() {
		return isEmpty() ? 0d : getShapes().get(0).getRotationAngle();
	}

	@Override
	default IPoint getGravityCentre() {
		return isEmpty() ? ShapeFactory.INST.createPoint() : getTopLeftPoint().getMiddlePoint(getBottomRightPoint());
	}

	@Override
	default IPoint getBottomRightPoint() {
		return getShapes().parallelStream().map(sh -> sh.getBottomRightPoint()).
			reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()))).
			orElse(ShapeFactory.INST.createPoint(Double.NaN, Double.NaN));
	}

	@Override
	default IPoint getBottomLeftPoint() {
		return getShapes().parallelStream().map(sh -> sh.getBottomLeftPoint()).
			reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.min(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()))).
			orElse(ShapeFactory.INST.createPoint(Double.NaN, Double.NaN));
	}

	@Override
	default IPoint getTopLeftPoint() {
		return getShapes().parallelStream().map(sh -> sh.getTopLeftPoint()).
			reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()))).
			orElse(ShapeFactory.INST.createPoint(Double.NaN, Double.NaN));
	}

	@Override
	default IPoint getTopRightPoint() {
		return getShapes().parallelStream().map(sh -> sh.getTopRightPoint()).
			reduce((p1, p2) -> ShapeFactory.INST.createPoint(Math.max(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()))).
			orElse(ShapeFactory.INST.createPoint(Double.NaN, Double.NaN));
	}

	@Override
	default boolean hasHatchings() {
		return getShapes().stream().anyMatch(sh -> sh.hasHatchings());
	}

	@Override
	default boolean hasGradient() {
		return getShapes().stream().anyMatch(sh -> sh.hasGradient());
	}
}
