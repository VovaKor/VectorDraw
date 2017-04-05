package vectordraw.view;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

import vectordraw.models.interfaces.shape.IGroup;

/**
 *
 * @author Volodymyr Korobko
 */
public class ViewGroup extends AViewShape<IGroup> {

    /**
     * The view that contains the drawing.
     */

    private final Rectangle border;

    /**
     * Creates an initialises a drawing view.
     *
     * @param sh The model to view.
     */
    public ViewGroup(IGroup sh) {
        super(sh);
        border = new Rectangle();
//        border.setStrokeLineJoin(StrokeLineJoin.MITER);

        border.setFill(Color.TRANSPARENT);
        getChildren().add(border);
//      
        border.setStroke(Color.RED);
        border.setStrokeLineCap(StrokeLineCap.BUTT);
        border.getStrokeDashArray().addAll(7d, 7d);

        this.addEventHandler(MouseEvent.ANY, evt -> {
            updateBorder();
        });
        sh.getShapes().forEach(shape -> ViewFactory.INSTANCE.createView(shape)
                .ifPresent(v -> {
                    getChildren().add(v);
                }));

        updateBorder();

    }

    private void updateBorder() {
        border.setX(model.getTopLeftPoint().getX());
        border.setY(model.getTopLeftPoint().getY());
        border.setWidth(model.getWidth());
        border.setHeight(model.getHeight());
    }

    @Override
    public void flush() {
        super.flush();

    }

}
