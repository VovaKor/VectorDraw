
package vectordraw.models;

import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.IArc;
import vectordraw.models.interfaces.shape.IBezierCurve;
import vectordraw.models.interfaces.shape.ICircle;
import vectordraw.models.interfaces.shape.IEllipse;
import vectordraw.models.interfaces.shape.IPolygon;
import vectordraw.models.interfaces.shape.IPolyline;
import vectordraw.models.interfaces.shape.IRectangle;
import vectordraw.models.interfaces.shape.IRhombus;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.models.interfaces.shape.ISquare;
import vectordraw.models.interfaces.shape.ITriangle;

/**
 * Defines the choices of edition of the canvas.
*
 */
public enum EShapeTypes {
	RECT {
		@Override
		public IRectangle createShapeInstance() {
			return ShapeFactory.INST.createRectangle();
		}
	}, SQUARE {
		@Override
		public ISquare createShapeInstance() {
			return ShapeFactory.INST.createSquare();
		}
	}, RHOMBUS {
		@Override
		public IRhombus createShapeInstance() {
			return ShapeFactory.INST.createRhombus();
		}
	},  TRIANGLE {
		@Override
		public ITriangle createShapeInstance() {
			return ShapeFactory.INST.createTriangle();
		}
	}, LINES {
		@Override
		public IPolyline createShapeInstance() {
			return ShapeFactory.INST.createPolyline();
		}
	}, CIRCLE {
		@Override
		public ICircle createShapeInstance() {
			return ShapeFactory.INST.createCircle();
		}
	}, ELLIPSE {
		@Override
		public IEllipse createShapeInstance() {
			return ShapeFactory.INST.createEllipse();
		}
	}, POLYGON {
		@Override
		public IPolygon createShapeInstance() {
			return ShapeFactory.INST.createPolygon();
		}
	}, WEDGE {
		@Override
		public IArc createShapeInstance() {
			final IArc shape = ShapeFactory.INST.createArc();
			shape.setArcStyle(EArcStyle.WEDGE);                        
			return shape;
		}
	}, OPEN_ARC {
		@Override
		public IArc createShapeInstance() {
			final IArc shape = ShapeFactory.INST.createArc();
			shape.setArcStyle(EArcStyle.ARC);
			return shape;
		}
	}, CHORD {
		@Override
		public IArc createShapeInstance() {
			final IArc shape = ShapeFactory.INST.createArc();
			shape.setArcStyle(EArcStyle.CHORD);
			return shape;
		}
	}, BEZIER_CURVE {
		@Override
		public IBezierCurve createShapeInstance() {
			final IBezierCurve shape = ShapeFactory.INST.createBezierCurve();
			shape.setIsClosed(false);
			return shape;
		}
	};

	/**
	 * @return A new shape instance corresponding to the editing choice.
	 * *
	 */
	public abstract IShape createShapeInstance();
}
