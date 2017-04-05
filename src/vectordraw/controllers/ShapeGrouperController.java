package vectordraw.controllers;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import org.malai.action.ActionsRegistry;
import org.malai.javafx.instrument.JfxInteractor;
import org.malai.javafx.interaction.JfxInteraction;
import org.malai.javafx.interaction.library.ButtonPressed;
import vectordraw.actions.shape.JoinShapes;
import vectordraw.actions.shape.SeparateShapes;
import vectordraw.actions.shape.SelectShapes;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;

/**
 * This instrument groups and separates shapes.
 
 */
public class ShapeGrouperController extends ACanvasInstrument implements Initializable {
	/** The widget to group shapes. */
	@FXML protected Button groupB;

	/** The widget to separate shapes. */
	@FXML protected Button sepB;

	@FXML protected TitledPane mainPane;
        
        @Inject protected HandInstrument hand;
	/**
	 * Creates the instrument.
	 */
	public ShapeGrouperController() {
		super();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		mainPane.managedProperty().bind(mainPane.visibleProperty());
                setActivated(false);
                ((ObservableList<IShape>)canvas.getDrawing().getSelectedShapes().getShapes()).addListener(
			(ListChangeListener.Change<? extends IShape> evt) -> setActivated(hand.isActivated() && !evt.getList().isEmpty()));
	}

	
	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
            addInteractor(new Button2GroupShapes<>(this, ButtonPressed.class, groupB));
            addInteractor(new Button2SeparateShapes<>(this, ButtonPressed.class, sepB));
		
	}
        @Override
	public void setActivated(final boolean activ) {
		super.setActivated(activ);
                mainPane.setVisible(activ);
                 	
		}

    void update(IGroup shape) {
        if(shape.isEmpty() || !hand.isActivated()) {
			setActivated(false);
		}
		else {
			setActivated(true);
			
		}
    }
	
        
private static class Button2GroupShapes <I extends JfxInteraction> extends JfxInteractor<JoinShapes, I, ShapeGrouperController> {

        Button2GroupShapes(final ShapeGrouperController ins, final Class<I> clazzInteraction, Node widget) throws InstantiationException, IllegalAccessException {
            super(ins, false, JoinShapes.class, clazzInteraction, widget);
        }

        @Override
        public void initAction() {
            final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
            selection.getShapes().forEach(sh -> action.addShape(sh));
            action.setDrawing(selection.getDrawing().get());
        }
        @Override
		public boolean isConditionRespected() {
			final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
			return selection != null && !selection.getShapes().isEmpty();
		}
       
    }
private static class Button2SeparateShapes <I extends JfxInteraction> extends JfxInteractor<SeparateShapes, I, ShapeGrouperController> {

        Button2SeparateShapes(final ShapeGrouperController ins, final Class<I> clazzInteraction, Node widget) throws InstantiationException, IllegalAccessException {
            super(ins, false, SeparateShapes.class, clazzInteraction, widget);
        }

        @Override
        public void initAction() {
            final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
            selection.getShapes().forEach(sh -> action.addShape(sh));
            action.setDrawing(selection.getDrawing().get());
        }
        @Override
		public boolean isConditionRespected() {
			final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
			return selection != null && !selection.getShapes().isEmpty();
		}
       
    }	
}
