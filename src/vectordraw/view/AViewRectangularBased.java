
package vectordraw.view;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.scene.shape.Rectangle;
import vectordraw.models.interfaces.shape.ISingleShape;


/**
 * A JFX abstract view to factorise code of views based on a JFX rectangle.
 */
abstract class AViewRectangularBased<T extends ISingleShape> extends AViewSingleShape<T, Rectangle> {
	final  ChangeListener<? super Number> lineArcCall = (observable, oldValue, newValue) -> {
		final double lineArc = newValue.doubleValue();
		final double width = model.getWidth();
		final double height = model.getHeight();

		border.setArcHeight(lineArc * height);
		border.setArcWidth(lineArc * width);

		if(shadow != null) {
			shadow.setArcHeight(lineArc * height);
			shadow.setArcWidth(lineArc * width);
		}
		if(dblBorder != null) {
			dblBorder.setArcHeight(lineArc * height);
			dblBorder.setArcWidth(lineArc * width);
		}
	};

	/**
	 * Creates the rectangle view.
	 * @param sh The model.
	 */
	AViewRectangularBased(final  T sh) {
		super(sh);

		if(dblBorder != null) {
			dblBorder.xProperty().bind(Bindings.add(getDbleBorderGap(), border.xProperty()));
			dblBorder.yProperty().bind(Bindings.add(getDbleBorderGap(), border.yProperty()));
			dblBorder.heightProperty().bind(Bindings.subtract(border.heightProperty(), getDbleBorderGap() * 2d));
			dblBorder.widthProperty().bind(Bindings.subtract(border.widthProperty(), getDbleBorderGap() * 2d));
		}

		if(shadow != null) {
			shadow.xProperty().bind(border.xProperty());
			shadow.yProperty().bind(border.yProperty());
			shadow.widthProperty().bind(border.widthProperty());
			shadow.heightProperty().bind(border.heightProperty());
		}
	}


	private static void unbindRect(Rectangle rec) {
		if(rec != null) {
			rec.xProperty().unbind();
			rec.yProperty().unbind();
			rec.widthProperty().unbind();
			rec.heightProperty().unbind();
		}
	}

	@Override
	protected Rectangle createJFXShape() {
		return new Rectangle();
	}

	@Override
	public void flush() {
		unbindRect(border);
		unbindRect(dblBorder);
		unbindRect(shadow);
	}
}
