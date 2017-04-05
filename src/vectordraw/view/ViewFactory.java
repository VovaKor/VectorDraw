
package vectordraw.view;

import java.util.Optional;
import vectordraw.models.interfaces.shape.IArc;
import vectordraw.models.interfaces.shape.IBezierCurve;
import vectordraw.models.interfaces.shape.ICircle;
import vectordraw.models.interfaces.shape.IEllipse;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IPolygon;
import vectordraw.models.interfaces.shape.IPolyline;
import vectordraw.models.interfaces.shape.IRectangle;
import vectordraw.models.interfaces.shape.IRhombus;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.ISquare;
import vectordraw.models.interfaces.shape.ITriangle;



/**
 * The factory that creates views from given models.
 */
public final class ViewFactory {
	/** The singleton. */
	public static final ViewFactory INSTANCE = new ViewFactory();

	private ViewFactory() {
		super();
	}

	/**
	 * Creates a view from a shape.
	 * @param shape The shape used to create the view.
	 * @return The created view or empty.
	 * *
	 */
	public <T extends IShape, S extends AViewShape<T>> Optional<S> createView(final  T shape) {
            
		if(shape instanceof IArc) return Optional.of((S)new ViewArc((IArc) shape));
		if(shape instanceof ISquare) return Optional.of((S)new ViewSquare((ISquare) shape));
		if(shape instanceof IRectangle) return Optional.of((S)new ViewRectangle((IRectangle) shape));
		if(shape instanceof IGroup) return Optional.of((S)new ViewGroup((IGroup) shape));
		if(shape instanceof ICircle) return Optional.of((S)new ViewCircle((ICircle) shape));
		if(shape instanceof IEllipse) return Optional.of((S)new ViewEllipse((IEllipse) shape));
		if(shape instanceof ITriangle) return Optional.of((S)new ViewTriangle((ITriangle) shape));
		if(shape instanceof IRhombus) return Optional.of((S)new ViewRhombus((IRhombus) shape));
		if(shape instanceof IPolyline) return Optional.of((S)new ViewPolyline((IPolyline) shape));
		if(shape instanceof IPolygon) return Optional.of((S)new ViewPolygon((IPolygon) shape));
		if(shape instanceof IBezierCurve) return Optional.of((S)new ViewBezierCurve((IBezierCurve) shape));
		
		return Optional.empty();
	}
}
