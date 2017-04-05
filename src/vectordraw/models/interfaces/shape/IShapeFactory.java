
package vectordraw.models.interfaces.shape;

import java.awt.geom.Point2D;
import java.util.Optional;
import javafx.geometry.Point3D;

public interface IShapeFactory {
	/**
	 * @param shapeClass The class of the shape to instantiated.
	 * @return A new instance of the class given as argument or null.
	 *
	 */
	<T extends IShape> Optional<T> newShape(java.lang.Class<T> shapeClass);

	/**
	 * Creates a color from an JavaFX color.
	 * @param col The colour to convert.
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColorFX(javafx.scene.paint.Color col);

	/**
	 * Creates a color from an AWT color.
	 * @param col The colour to convert.
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColorAWT(java.awt.Color col);

	/**
	 * Creates a color following the HSB format.
	 * @param h The H
	 * @param s The S
	 * @param b The B
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColorHSB(double h, double s, double b);

	/**
	 * Creates a colour following the RGBA format.
	 * @param r The R.
	 * @param g The G.
	 * @param b The B.
	 * @param a The A.
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColorInt(int r, int g, int b, int a);

	/**
	 * Creates a colour following the RGB format.
	 * @param r The R.
	 * @param g The G.
	 * @param b The B.
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColorInt(int r, int g, int b);

	/**
	 * Creates a colour.
	 * @param r Red
	 * @param g Green
	 * @param b Blue
	 * @param o Opacity
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColor(double r, double g, double b, double o);

	/**
	 * Creates a colour.
	 * @param r Red
	 * @param g Green
	 * @param b Blue
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColor(double r, double g, double b);

	/**
	 * Creates a colour (1,1,1,1)
	 * @return The converted colour. Cannot be null.
	 */
	IColor createColor();

	/**
	 * @return The created drawing.
	 * 
	 */
	IDrawing createDrawing();

	/**
	 * Creates a group that will contains initially the given sh.
	 * @param sh The shape to add to the group to create.
	 * @return Created groupe. Cannot be null.
	 * 
	 */
	IGroup createGroup(IShape sh);
	
	/**
	 * Creates a model with no point.
	 * @return The created bezier curve.
	 */
	IBezierCurve createBezierCurve();

	/**
	 * Creates a bezier curve with two points.
	 * @param point The first point of the curve.
	 * @param point2 The second point of the curve.
	 * @return The created bezier curve.
	 */
	IBezierCurve createBezierCurve(IPoint point, IPoint point2);

	/**
	 * Creates an ellipse.
	 * @param tl The top-left point of the ellipse.
	 * @param br The bottom-right point of the ellipse.
	 * @throws IllegalArgumentException If a or b is not valid.
	 * @return The created ellipse.
	 */
	IEllipse createEllipse(IPoint tl, IPoint br);

	/**
	 * @return The created ellipse.
	 */
	IEllipse createEllipse();

	/**
	 * Creates a triangle.
	 * @param pos The north-west point of the triangle.
	 * @param width The width of the triangle.
	 * @param height The height of the triangle.
	 * @throws IllegalArgumentException If the width or the height is not valid.
	 * @return The created triangle.
	 */
	ITriangle createTriangle(IPoint pos, double width, double height);

	/**
	 * @return The created triangle.
	 */
	ITriangle createTriangle();

	/**
	 * Creates a rhombus.
	 * @param centre The centre of the rhombus.
	 * @param width The width of the rhombus.
	 * @param height The height of the rhombus.
	 * @throws IllegalArgumentException If the width, the height or the centre is not valid.
	 * @return The created rhombus.
	 */
	IRhombus createRhombus(IPoint centre, double width, double height);

	/**
	 * Creates a rhombus at the position (0,0) with width=height=1.
	 * @return The created rhombus.
	 */
	IRhombus createRhombus();

	
	/**
	 * Creates a circle.
	 * @param pt The position of the top-left point of the picture.
	 * @param radius The radius.
	 * @throws IllegalArgumentException If the radius is not valid.
	 * @throws NullPointerException If the given point pt is null.
	 * @return The created circle.
	 */
	ICircle createCircle(IPoint pt, double radius);

	/**
	 * @return The created circle.
	 */
	ICircle createCircle();

	/**
	 * @return The created group of shapes.
	 * 
	 */
	IGroup createGroup();

	/**
	 * Constructs a line from the specified coordinates.
	 * @param x1 the X coordinate of the start point.
	 * @param y1 the Y coordinate of the start point.
	 * @param x2 the X coordinate of the end point.
	 * @param y2 the Y coordinate of the end point.
	 * @throws IllegalArgumentException If one of the given coordinate is not valid.
	 * @return The created line.
	 */
	ILine createLine(double x1, double y1, double x2, double y2);

	/**
	 * Creates a line by creating a second point with
	 * @param b y = ax+ b
	 * @param p1 The first point.
	 * @throws IllegalArgumentException If one of the given parameter is not valid.
	 * @return The created line.
	 */
	ILine createLine(double b, IPoint p1);

	/**
	 * Constructs a line from the specified <code>Point2D</code> objects.
	 * @param p1 the start <code>Point2D</code> of this line segment.
	 * @param p2 the end <code>Point2D</code> of this line segment.
	 * @throws IllegalArgumentException If one of the given points is not valid.
	 * @return The created line.
	 */
	ILine createLine(IPoint p1, IPoint p2);

	/**
	 * @return The created point with coordinates (0, 0).
	 * 
	 */
	IPoint createPoint();

	/**
	 * Duplicates a java 2D point into a IPoint. If the given point pt is null, a point (0,0) is
	 * created.
	 * @param pt The point to convert.
	 * @return The created point. Cannot be null.
	 */
	IPoint createPoint(Point2D pt);

	/**
	 * Duplicates a java 3D point into a IPoint. If the given point pt is null, a point (0,0) is
	 * created.
	 * @param pt The point to convert.
	 * @return The created point. Cannot be null.
	 */
	IPoint createPoint(Point3D pt);

	/**
	 * Creates a Point2D with the specified coordinates.
	 * @param x The X-coordinate to set.
	 * @param y The Y-coordinate to set.
	 * @return The created point.
	 * 
	 */
	IPoint createPoint(double x, double y);

	/**
	 * Creates a Point2D with the specified coordinates.
	 * @param pt The IPoint, if null the default value (0,0) will be used.
	 * @return The created point.
	 *
	 */
	IPoint createPoint(IPoint pt);

	/**
	 * @return The created polyline
	 * 
	 */
	IPolyline createPolyline();

	/**
	 * Creates a model with two points.
	 * @param point The first point of the shape.
	 * @param point2 The second point of the shape.
	 * @return The created polyline.
	 * 
	 */
	IPolyline createPolyline(IPoint point, IPoint point2);

	/**
	 * @return The created polygon
	 * 
	 */
	IPolygon createPolygon();

	/**
	 * Creates a polygon with two points.
	 * @param point The first point of the shape.
	 * @param point2 The second point of the shape.
	 * @return The created polygon.
	 * 
	 */
	IPolygon createPolygon(IPoint point, IPoint point2);

	/**
	 * @return The created rectangle with position (0,0) and width=10 and height=10.
	 * 
	 */
	IRectangle createRectangle();

	/**
	 * Creates a rectangle.
	 * @param pos The north-west point of the rectangle.
	 * @param width The width of the rectangle.
	 * @param height The height of the rectangle.
	 * @throws IllegalArgumentException If the width, the height or the point is not valid.
	 * @throws NullPointerException if the given point is null.
	 * @return The created rectangle.
	 * 
	 */
	IRectangle createRectangle(IPoint pos, double width, double height);

	/**
	 * Creates a rectangle.
	 * @param tl The top left point of the rectangle.
	 * @param br The bottom right point of the rectangle.
	 * @throws IllegalArgumentException if one of the given points is not valid.
	 * @return The created rectangle.
	 * 
	 */
	IRectangle createRectangle(IPoint tl, IPoint br);

		/**
	 * Creates a square at position (0,0) which width equals 10.
	 * @return The created square.
	 * 
	 */
	ISquare createSquare();

	/**
	 * Creates a square.
	 * @param pos The north-west point of the square.
	 * @param width The width of the square.
	 * @throws IllegalArgumentException If the width or the height is not valid.
	 * @return The created square.
	 * 
	 */
	ISquare createSquare(IPoint pos, double width);

	/**
	 * Creates a circled arc.
	 * @param pos The north-west point of the square.
	 * @param width The width of the square.
         * @param startAngle 
         * @param length
	 * @throws IllegalArgumentException If the width or the height is not valid.
	 * @return The created circled arc.
	 * 
	 */
	IArc createArc(IPoint pos, double width, double startAngle, double length);

	/**
	 * Creates a circled arc with a 1 radius.
	 * @return The created circled arc.
	 * 
	 */
	IArc createArc();

	
	/**
	 * Duplicates the given shape.
	 * @param shape The shape to duplicate
	 * @return The duplicated shape or null.
	 * 
	 */
	<T extends IShape> T duplicate(final T shape);
}
