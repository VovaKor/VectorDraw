
package vectordraw.models.impl;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import javafx.geometry.Point3D;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IArc;
import vectordraw.models.interfaces.shape.IBezierCurve;
import vectordraw.models.interfaces.shape.ICircle;
import vectordraw.models.interfaces.shape.IDrawing;
import vectordraw.models.interfaces.shape.IEllipse;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.ILine;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IPolygon;
import vectordraw.models.interfaces.shape.IPolyline;
import vectordraw.models.interfaces.shape.IRectangle;
import vectordraw.models.interfaces.shape.IRhombus;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.IShapeFactory;
import vectordraw.models.interfaces.shape.ISquare;
import vectordraw.models.interfaces.shape.ITriangle;
import vectordraw.models.interfaces.shape.IColor;


/**
 * An implementation of the abstract factory.
 */

public class VDShapeFactory implements IShapeFactory {
	/** The map that maps types to creation operations. */
	final Map<Class<?>, Supplier<IShape>> factoryMap;

	public VDShapeFactory() {
		super();
		factoryMap = new HashMap<>();
		
		factoryMap.put(IArc.class, () -> createArc());
		factoryMap.put(VDArc.class, () -> createArc());
		factoryMap.put(ICircle.class, () -> createCircle());
		factoryMap.put(VDCircle.class, () -> createCircle());
		factoryMap.put(IEllipse.class, () -> createEllipse());
		factoryMap.put(VDEllipse.class, () -> createEllipse());
		factoryMap.put(ISquare.class, () -> createSquare());
		factoryMap.put(VDSquare.class, () -> createSquare());
		factoryMap.put(IRectangle.class, () -> createRectangle());
		factoryMap.put(VDRectangle.class, () -> createRectangle());
		factoryMap.put(ITriangle.class, () -> createTriangle());
		factoryMap.put(VDTriangle.class, () -> createTriangle());
		factoryMap.put(IRhombus.class, () -> createRhombus());
		factoryMap.put(VDRhombus.class, () -> createRhombus());
		factoryMap.put(IPolyline.class, () -> createPolyline());
		factoryMap.put(VDPolyline.class, () -> createPolyline());
		factoryMap.put(IPolygon.class, () -> createPolygon());
		factoryMap.put(VDPolygon.class, () -> createPolygon());
		factoryMap.put(IBezierCurve.class, () -> createBezierCurve());
		factoryMap.put(VDBezierCurve.class, () -> createBezierCurve());
		factoryMap.put(IGroup.class, () -> createGroup());
		factoryMap.put(VDGroup.class, () -> createGroup());

	}


	@Override
	public <T extends IShape> Optional<T> newShape(final Class<T> shapeClass) {
		if(shapeClass == null) return Optional.empty();
		try {
			return Optional.of(shapeClass.cast(factoryMap.get(shapeClass).get()));
		}catch(final Throwable ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public IGroup createGroup(final IShape sh) {
		final IGroup gp = createGroup();
		if(sh != null) {
			gp.addShape(sh);
		}
		return gp;
	}

	@Override
	public IColor createColorFX(final javafx.scene.paint.Color col) {
		if(col == null) throw new IllegalArgumentException();
		return createColor(col.getRed(), col.getGreen(), col.getBlue(), col.getOpacity());
	}

	@Override
	public IColor createColorAWT(java.awt.Color col) {
		if(col == null) throw new IllegalArgumentException();
		return createColorInt(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha());
	}

	@Override
	public IColor createColorInt(final int r, final int g, final int b, final int a) {
		return createColor(r / 255.0, g / 255.0, b / 255.0, a / 255.0);
	}

	@Override
	public IColor createColorInt(final int r, final int g, final int b) {
		return createColorInt(r, g, b, 255);
	}

	@Override
	public IColor createColorHSB(final double h, final double s, final double b) {
		final javafx.scene.paint.Color col = javafx.scene.paint.Color.hsb(h, s, b);
		return createColor(col.getRed(), col.getGreen(), col.getBlue(), col.getOpacity());
	}

	@Override
	public IColor createColor(final double r, final double g, final double b, final double o) {
		return new VDColorImpl(r, g, b, o);
	}

	@Override
	public IColor createColor(final double r, final double g, final double b) {
		return createColor(r, g, b, 1.0);
	}

	@Override
	public IColor createColor() {
		return createColor(1.0, 1.0, 1.0, 1.0);
	}


	@Override
	public IPoint createPoint(final Point2D pt) {
		return pt == null ? createPoint() : createPoint(pt.getX(), pt.getY());
	}

	@Override
	public IPoint createPoint(final Point3D pt) {
		return pt == null ? createPoint() : createPoint(pt.getX(), pt.getY());
	}

	@Override
	public IDrawing createDrawing() {
		return new VDDrawing();
	}

	@Override
	public IBezierCurve createBezierCurve() {
		return new VDBezierCurve(true);
	}

	@Override
	public IBezierCurve createBezierCurve(final IPoint point, final IPoint point2) {
		return new VDBezierCurve(point, point2, true);
	}

	@Override
	public IEllipse createEllipse(final IPoint tl, final IPoint br) {
		return new VDEllipse(tl, br);
	}

	@Override
	public IEllipse createEllipse() {
		return new VDEllipse();
	}

	@Override
	public ITriangle createTriangle(final IPoint pos, final double width, final double height) {
		return new VDTriangle(pos, width, height);
	}

	@Override
	public ITriangle createTriangle() {
		return new VDTriangle();
	}

	@Override
	public IRhombus createRhombus(final IPoint centre, final double width, final double height) {
		return new VDRhombus(centre, width, height);
	}

	@Override
	public IRhombus createRhombus() {
		return new VDRhombus();
	}

	@Override
	public ICircle createCircle(final IPoint pt, final double radius) {
		return new VDCircle(pt, radius);
	}

	@Override
	public ICircle createCircle() {
		return createCircle(ShapeFactory.INST.createPoint(), 10);
	}

	@Override
	public IGroup createGroup() {
		return new VDGroup();
	}

	@Override
	public ILine createLine(final double x1, final double y1, final double x2, final double y2) {
		return new VDLine(x1, y1, x2, y2);
	}

	@Override
	public ILine createLine(final double b, final IPoint p1) {
		return new VDLine(b, p1);
	}

	@Override
	public ILine createLine(final IPoint p1, final IPoint p2) {
		return new VDLine(p1, p2);
	}

	@Override
	public IPoint createPoint() { return new VDPoint();}

	@Override
	public IPoint createPoint(final double x, final double y) {
		return new VDPoint(x, y);
	}

	@Override
	public IPoint createPoint(final IPoint pt) {
		return new VDPoint(pt);
	}

	@Override
	public IPolyline createPolyline() {
		return new VDPolyline();
	}

	@Override
	public IPolyline createPolyline(final IPoint point, final IPoint point2) {
		return new VDPolyline(point, point2);
	}

	@Override
	public IPolygon createPolygon() {
		return new VDPolygon();
	}

	@Override
	public IPolygon createPolygon(final IPoint point, final IPoint point2) {
		return new VDPolygon(point, point2);
	}

	@Override
	public IRectangle createRectangle() {
		return createRectangle(ShapeFactory.INST.createPoint(), ShapeFactory.INST.createPoint(1, 1));
	}

	@Override
	public IRectangle createRectangle(final IPoint pos, final double width, final double height) {
		return createRectangle(pos, ShapeFactory.INST.createPoint(pos.getX() + width, pos.getY() + height));
	}

	@Override
	public IRectangle createRectangle(final IPoint tl, final IPoint br) {
		return new VDRectangle(tl, br);
	}


	@Override
	public ISquare createSquare() {
		return createSquare(ShapeFactory.INST.createPoint(), 1);
	}

	@Override
	public ISquare createSquare(final IPoint pos, final double width) {
		return new VDSquare(pos, width);
	}

	@Override
	public IArc createArc(final IPoint pos, final double width, final double startAngle, final double length) {
		return new VDArc(pos, width, startAngle, length);
	}

	@Override
	public IArc createArc() {
		return createArc(ShapeFactory.INST.createPoint(), 10.0, 0.0, 200.0);
	}

	@Override
	public <T extends IShape> T duplicate(final T shape) {
		return shape == null ? null : shape.duplicate();
	}
}
