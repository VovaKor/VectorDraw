package vectordraw.models.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.prop.IArcProp;
import vectordraw.util.Colors;
import vectordraw.models.interfaces.prop.ILineArcProp;
import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.EBorderPos;
import vectordraw.models.interfaces.shape.IColor;
import vectordraw.models.interfaces.shape.EFillingStyle;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.ELineStyle;

/**
 * An implemenation of the IGroup interface.
 */
class VDGroup implements IShape, IGroupArc, IGroupLineArc,
        IGroupShape, ISetShapesImpl {

    /**
     * The set of shapes.
     */
    private final ObservableList<IShape> shapes;

    VDGroup() {
        super();
        shapes = FXCollections.observableArrayList();
    }

    @Override
    public IGroup duplicate() {
        return duplicateDeep(true);
    }

    @Override
    public void setModified(final boolean modified) {
        getShapes().forEach(sh -> sh.setModified(modified));
    }

    @Override
    public boolean isModified() {
        return getShapes().parallelStream().anyMatch(sh -> sh.isModified());
    }

    @Override
    public IGroup duplicateDeep(final boolean duplicateShapes) {
        final IGroup dup = ShapeFactory.INST.createGroup();

        if (duplicateShapes) {
            getShapes().forEach(sh -> dup.addShape(sh.duplicate()));
        } else {
            getShapes().forEach(sh -> dup.addShape(sh));
        }

        return dup;
    }

    @Override
    public boolean isTypeOf(final Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        if (clazz.equals(getClass()) || clazz.equals(IShape.class) || clazz.equals(AShapeImpl.class) || clazz.equals(IGroup.class)) {
            return true;
        }
        return shapes.parallelStream().anyMatch(sh -> sh.isTypeOf(clazz));
    }


    @Override
    public List<EBorderPos> getBordersPositionList() {
        return getShapes().stream().map(sh -> sh.isBordersMovable() ? sh.getBordersPosition() : EBorderPos.INTO).collect(Collectors.toList());
    }

    @Override
    public List<IColor> getLineColourList() {
        return getShapes().stream().map(sh -> sh.getLineColour()).collect(Collectors.toList());
    }

    @Override
    public void setBordersPositionList(final List<EBorderPos> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isBordersMovable()) {
                    sh.setBordersPosition(values.get(i));
                }
            });
        }
    }

    @Override
    public void setLineColourList(final List<IColor> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> shapes.get(i).setLineColour(values.get(i)));
        }
    }

    @Override
    public final List<Double> getAngleStartList() {
        return getShapes().stream().map(sh -> sh instanceof IArcProp ? ((IArcProp) sh).getAngleStart() : null).collect(Collectors.toList());
    }

    @Override
    public final List<Double> getAngleEndList() {
        return getShapes().stream().map(sh -> sh instanceof IArcProp ? ((IArcProp) sh).getAngleEnd() : null).collect(Collectors.toList());
    }

    @Override
    public List<EArcStyle> getArcStyleList() {
        return getShapes().stream().map(sh -> sh instanceof IArcProp ? ((IArcProp) sh).getArcStyle() : EArcStyle.ARC).collect(Collectors.toList());
    }

    @Override
    public final List<Double> getRotationAngleList() {
        return getShapes().stream().map(sh -> sh.getRotationAngle()).collect(Collectors.toList());
    }
//

    @Override
    public final List<Double> getHatchingsAngleList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getHatchingsAngle() : null).collect(Collectors.toList());
    }

    @Override
    public final List<Double> getHatchingsWidthList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getHatchingsWidth() : null).collect(Collectors.toList());
    }

    @Override
    public List<Double> getHatchingsSepList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getHatchingsSep() : null).collect(Collectors.toList());
    }

    @Override
    public List<Double> getGradAngleList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getGradAngle() : null).collect(Collectors.toList());
    }

    @Override
    public List<Double> getGradMidPtList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getGradMidPt() : null).collect(Collectors.toList());
    }

    @Override
    public List<Double> getLineArcList() {
        return getShapes().stream().map(sh -> sh instanceof ILineArcProp ? ((ILineArcProp) sh).getLineArc() : null).collect(Collectors.toList());
    }

    @Override
    public List<IColor> getFillingColList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getFillingCol() : Colors.BLACK).collect(Collectors.toList());
    }

    @Override
    public List<IColor> getHatchingsColList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getHatchingsCol() : Colors.BLACK).collect(Collectors.toList());
    }

    @Override
    public List<Boolean> hasDbleBordList() {
        return getShapes().stream().map(sh -> sh.isDbleBorderable() ? sh.hasDbleBord() : null).collect(Collectors.toList());
    }

    @Override
    public List<Double> getDbleBordSepList() {
        return getShapes().stream().map(sh -> sh.isDbleBorderable() ? sh.getDbleBordSep() : null).collect(Collectors.toList());
    }

    @Override
    public List<IColor> getDbleBordColList() {
        return getShapes().stream().map(sh -> sh.isDbleBorderable() ? sh.getDbleBordCol() : Colors.BLACK).collect(Collectors.toList());
    }

    @Override
    public final List<Boolean> hasShadowList() {
        return getShapes().stream().map(sh -> sh.isShadowable() ? sh.hasShadow() : null).collect(Collectors.toList());
    }

    @Override
    public List<Double> getShadowSizeList() {
        return getShapes().stream().map(sh -> sh.isShadowable() ? sh.getShadowSize() : null).collect(Collectors.toList());
    }

    @Override
    public List<Double> getShadowAngleList() {
        return getShapes().stream().map(sh -> sh.isShadowable() ? sh.getShadowAngle() : null).collect(Collectors.toList());
    }

    @Override
    public List<IColor> getShadowColList() {
        return getShapes().stream().map(sh -> sh.isShadowable() ? sh.getShadowCol() : Colors.BLACK).collect(Collectors.toList());
    }

    @Override
    public List<IColor> getGradColStartList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getGradColStart() : Colors.BLACK).collect(Collectors.toList());
    }

    @Override
    public List<IColor> getGradColEndList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getGradColEnd() : Colors.BLACK).collect(Collectors.toList());
    }

    @Override
    public List<Double> getThicknessList() {
        return getShapes().stream().map(sh -> sh.isThicknessable() ? sh.getThickness() : null).collect(Collectors.toList());
    }

    @Override
    public List<EFillingStyle> getFillingStyleList() {
        return getShapes().stream().map(sh -> sh.isInteriorStylable() ? sh.getFillingStyle() : EFillingStyle.NONE).collect(Collectors.toList());
    }

    @Override
    public List<ELineStyle> getLineStyleList() {
        return getShapes().stream().map(sh -> sh.isLineStylable() ? sh.getLineStyle() : ELineStyle.SOLID).collect(Collectors.toList());
    }

    @Override
    public void setAngleStartList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh instanceof IArcProp) {
                    ((IArcProp) sh).setAngleStart(values.get(i));
                }
            });
        }
    }

    @Override
    public void setAngleEndList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh instanceof IArcProp) {
                    ((IArcProp) sh).setAngleEnd(values.get(i));
                }
            });
        }
    }

    @Override
    public void setArcStyleList(final List<EArcStyle> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh instanceof IArcProp) {
                    ((IArcProp) sh).setArcStyle(values.get(i));
                }
            });
        }
    }

    @Override
    public void setRotationAngleList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> shapes.get(i).setRotationAngle(values.get(i)));
        }
    }

    @Override
    public void setHatchingsAngleList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setHatchingsAngle(values.get(i));
                }
            });
        }
    }

    @Override
    public void setHatchingsWidthList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setHatchingsWidth(values.get(i));
                }
            });
        }
    }

    @Override
    public void setHatchingsSepList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setHatchingsSep(values.get(i));
                }
            });
        }
    }

    @Override
    public void setGradAngleList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setGradAngle(values.get(i));
                }
            });
        }
    }

    @Override
    public void setGradMidPtList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setGradMidPt(values.get(i));
                }
            });
        }
    }

    @Override
    public void setLineArcList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh instanceof ILineArcProp) {
                    ((ILineArcProp) sh).setLineArc(values.get(i));
                }
            });
        }
    }

    @Override
    public void setFillingColList(final List<IColor> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setFillingCol(values.get(i));
                }
            });
        }
    }

    @Override
    public void setHatchingsColList(final List<IColor> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setHatchingsCol(values.get(i));
                }
            });
        }
    }

    @Override
    public void setHasDbleBordList(final List<Boolean> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isDbleBorderable()) {
                    sh.setHasDbleBord(values.get(i));
                }
            });
        }
    }

    @Override
    public void setDbleBordSepList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isDbleBorderable()) {
                    sh.setDbleBordSep(values.get(i));
                }
            });
        }
    }

    @Override
    public void setDbleBordColList(final List<IColor> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isDbleBorderable()) {
                    sh.setDbleBordCol(values.get(i));
                }
            });
        }
    }

    @Override
    public void setHasShadowList(final List<Boolean> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isShadowable()) {
                    sh.setHasShadow(values.get(i));
                }
            });
        }
    }

    @Override
    public void setShadowSizeList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isShadowable()) {
                    sh.setShadowSize(values.get(i));
                }
            });
        }
    }

    @Override
    public void setShadowAngleList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isShadowable()) {
                    sh.setShadowAngle(values.get(i));
                }
            });
        }
    }

    @Override
    public void setShadowColList(final List<IColor> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isShadowable()) {
                    sh.setShadowCol(values.get(i));
                }
            });
        }
    }

    @Override
    public void setGradColStartList(final List<IColor> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setGradColStart(values.get(i));
                }
            });
        }
    }

    @Override
    public void setGradColEndList(final List<IColor> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setGradColEnd(values.get(i));
                }
            });
        }
    }

    @Override
    public void setThicknessList(final List<Double> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isThicknessable()) {
                    sh.setThickness(values.get(i));
                }
            });
        }
    }

    @Override
    public void setFillingStyleList(final List<EFillingStyle> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isInteriorStylable()) {
                    sh.setFillingStyle(values.get(i));
                }
            });
        }
    }

    @Override
    public void setLineStyleList(final List<ELineStyle> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isLineStylable()) {
                    sh.setLineStyle(values.get(i));
                }
            });
        }
    }

    @Override
    public void setShowPointsList(final List<Boolean> values) {
        if (values != null && values.size() == shapes.size()) {
            IntStream.range(0, values.size()).forEach(i -> {
                final IShape sh = shapes.get(i);
                if (sh.isShowPtsable()) {
                    sh.setShowPts(values.get(i));
                }
            });
        }
    }

    @Override
    public List<Boolean> getShowPointsList() {
        return getShapes().stream().map(sh -> sh.isShowPtsable() ? sh.isShowPts() : null).collect(Collectors.toList());
    }

    @Override
    public ObservableList<IShape> getShapes() {
        return shapes;
    }

}
