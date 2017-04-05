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
import javafx.scene.layout.AnchorPane;
import org.malai.action.ActionsRegistry;
import org.malai.javafx.instrument.JfxInteractor;
import org.malai.javafx.interaction.JfxInteraction;
import org.malai.javafx.interaction.library.ButtonPressed;
import vectordraw.actions.shape.MoveBackgroundShapes;
import vectordraw.actions.shape.MoveForegroundShapes;
import vectordraw.actions.shape.SelectShapes;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;

/**
 * Puts shapes in background / foreground.
 
 */
public class ShapePositionZController extends ACanvasInstrument implements Initializable {
	/** The foreground button. */
	@FXML protected Button foregroundB;

	/** The background button. */
	@FXML protected Button backgroundB;

	@FXML protected TitledPane mainPane;
        
        @Inject protected HandInstrument hand;
	/**
	 * Creates the instrument.
	 */
	public ShapePositionZController() {
		super();
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
                 addInteractor(new Button2MoveForeground<>(this, ButtonPressed.class, foregroundB));
                 addInteractor(new Button2MoveBackground<>(this, ButtonPressed.class, backgroundB));
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		mainPane.managedProperty().bind(mainPane.visibleProperty());
               setActivated(false);

		((ObservableList<IShape>)canvas.getDrawing().getSelectedShapes().getShapes()).addListener(
			(ListChangeListener.Change<? extends IShape> evt) -> setActivated(hand.isActivated() && !evt.getList().isEmpty()));
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
        

    private static class Button2MoveForeground <I extends JfxInteraction> extends JfxInteractor<MoveForegroundShapes, I, ShapePositionZController> {

        Button2MoveForeground(final ShapePositionZController ins, final Class<I> clazzInteraction, Node widget) throws InstantiationException, IllegalAccessException {
            super(ins, false, MoveForegroundShapes.class, clazzInteraction, widget);
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

    private static class Button2MoveBackground <I extends JfxInteraction> extends JfxInteractor<MoveBackgroundShapes, I, ShapePositionZController>{

        Button2MoveBackground(final ShapePositionZController ins, final Class<I> clazzInteraction, Node widget) throws InstantiationException, IllegalAccessException {
            super(ins, false, MoveBackgroundShapes.class, clazzInteraction, widget);
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


 /** This link maps a button interaction to an action that puts shapes in
 foreground / background. */
// class Button2MoveBackForeground extends
// InteractorImpl<MoveBackForegroundShapes, ButtonPressed, ShapePositionZController> {
// protected Button2MoveBackForeground(final ShapePositionZController ins) throws
// InstantiationException, IllegalAccessException {
// super(ins, false, MoveForegroundShapes.class, ButtonPressed.class);
// }
//
// @Override
// public void initAction() {
// action.setIsForeground(interaction.getButton()==instrument.foregroundB);
// action.setDrawing(instrument.pencil.canvas().getDrawing());
// action.setShape(instrument.pencil.canvas().getDrawing().getSelection().duplicateDeep(false));
// }
//
// @Override
// public boolean isConditionRespected() {
// return interaction.getButton()==instrument.backgroundB ||
// interaction.getButton()==instrument.foregroundB;
// }
// }
