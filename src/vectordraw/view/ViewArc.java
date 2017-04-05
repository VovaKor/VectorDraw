package vectordraw.view;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.IArc;

/**
 *
 * @author Volodymyr Korobko
 */
public class ViewArc extends AViewSingleShape<IArc, Arc>{
    
private final  ChangeListener<?> arcTypeUpdateCall = (obj, oldVal, newVal) -> updateArcType();
    public ViewArc(IArc arc) {
        super(arc);
        model.arcStyleProperty().addListener((ChangeListener<? super EArcStyle>) arcTypeUpdateCall);
        border.centerXProperty().bind(Bindings.createDoubleBinding(() -> model.getCenter().getX(), model.getPtAt(2).xProperty(), model.getPtAt(3).xProperty()));
	border.centerYProperty().bind(Bindings.createDoubleBinding(() -> model.getCenter().getY(), model.getPtAt(0).yProperty(), model.getPtAt(3).yProperty()));
	border.radiusXProperty().bind(Bindings.createDoubleBinding(() -> model.getWidth() / 2d, model.getPtAt(0).xProperty(), model.getPtAt(1).xProperty()));
	border.radiusYProperty().bind(Bindings.createDoubleBinding(() -> model.getHeight() / 2d, model.getPtAt(1).yProperty(), model.getPtAt(2).yProperty()));
        border.startAngleProperty().bind(Bindings.createDoubleBinding(()-> model.getAngleStart()));
        border.lengthProperty().bind(Bindings.createDoubleBinding(()-> model.getLength()));
        updateArcType();
        
    }

    @Override
    protected Arc createJFXShape() {
        return new Arc();
    }
    private static void unbindArc(Arc sh) {
		if(sh != null) {
			sh.centerXProperty().unbind();
			sh.centerYProperty().unbind();
			sh.radiusXProperty().unbind();
			sh.radiusYProperty().unbind();
                        sh.lengthProperty().unbind();
		}
	}
	@Override
	public void flush() {
		unbindArc(border);
		unbindArc(dblBorder);
		unbindArc(shadow);
	}

    private void updateArcType() {
        switch(model.getArcStyle()){
                        case WEDGE:
                            border.setType(ArcType.ROUND);
                            break;
                        case ARC: 
                            border.setType(ArcType.OPEN);
                            break;
                        case CHORD:
                            border.setType(ArcType.CHORD);
                            break;
                        default:
                            throw new AssertionError(model.getArcStyle());
                        }
    }
   
}
