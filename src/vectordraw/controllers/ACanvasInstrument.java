
package vectordraw.controllers;

import com.google.inject.Inject;
import javafx.geometry.Point3D;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IPoint;

import vectordraw.view.Canvas;
import org.malai.javafx.instrument.JfxInstrument;

/**
 * This abstract instrument encapsulates common operations dealing with a canvas.
 *
 */
abstract class ACanvasInstrument extends JfxInstrument {
	@Inject protected Canvas canvas;

	ACanvasInstrument() {
		super();
	}

	/**
	 * Computes the point depending on the the zoom level and the origin of the canvas.
	 * @param pt The point to adapted.
	 * @return The computed point.
	 * *
	 */
	public IPoint getAdaptedOriginPoint(final IPoint pt) {
		return canvas.convertToOrigin(pt);
	}

	/**
	 * Computes the point depending on the the zoom level and the origin of the canvas.
	 * @param pt The point to adapted.
	 * @return The computed point.
	 */
	public IPoint getAdaptedOriginPoint(final Point3D pt) {
		return pt == null ? null : getAdaptedOriginPoint(ShapeFactory.INST.createPoint(pt.getX(), pt.getY()));
	}

	/**
	 * Computes the point depending on the the zoom level and the magnetic grid.
	 * @param pt The point to adapted.
	 * @return The computed point.
	 * *
	 */
	public IPoint getAdaptedPoint(final Point3D pt) {
		return canvas.convertToOrigin(ShapeFactory.INST.createPoint(pt.getX(), pt.getY()));
          
	}
}
