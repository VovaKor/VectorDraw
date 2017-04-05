package vectordraw.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.malai.javafx.instrument.JfxInteractor;
import org.malai.javafx.interaction.JfxInteraction;
import org.malai.javafx.interaction.library.ButtonPressed;
import vectordraw.actions.OpenDrawing;
import vectordraw.actions.SaveDrawing;

/**
 * This instrument saves and loads documents.
 * 
 */
public class OpenSaveController extends ACanvasInstrument implements Initializable {
	/** The button used to save documents. */
	@FXML protected Button saveButton;

	/** The button used to load documents. */
	@FXML protected Button loadButton;

	/**
	 * Creates the file loader/saver.
	 */
	public OpenSaveController() {
		super();
        }        
       
	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
            addInteractor(new Button2Save<>(this, ButtonPressed.class, saveButton));
		addInteractor(new Button2Open<>(this, ButtonPressed.class, loadButton));
	}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         setActivated(true);
    }
        
private static class Button2Open <I extends JfxInteraction> extends JfxInteractor<OpenDrawing, I, OpenSaveController> {

        Button2Open(final OpenSaveController ins, final Class<I> clazzInteraction, Node widget) throws InstantiationException, IllegalAccessException {
            super(ins, false, OpenDrawing.class, clazzInteraction, widget);
        }

        @Override
        public void initAction() {
//            final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
//            selection.getShapes().forEach(sh -> action.addShape(sh));
            action.setDrawing(instrument.canvas.getDrawing());
//            action.setFileChooser(instrument.getDialog(instrument.isSAVE()));
            action.setCanvas(instrument.canvas);
        }
        @Override
		public boolean isConditionRespected() {
//			final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
			return instrument.canvas.getDrawing() != null;
		}
       
    }
private static class Button2Save <I extends JfxInteraction> extends JfxInteractor<SaveDrawing, I, OpenSaveController> {

        Button2Save(final OpenSaveController ins, final Class<I> clazzInteraction, Node widget) throws InstantiationException, IllegalAccessException {
            super(ins, false, SaveDrawing.class, clazzInteraction, widget);
        }

        @Override
        public void initAction() {
//            final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
//            selection.getShapes().forEach(sh -> action.addShape(sh));
            action.setDrawing(instrument.canvas.getDrawing());
//            action.setFileChooser(instrument.getDialog(instrument.isSAVE()));
            action.setCanvas(instrument.canvas);
        }
        @Override
		public boolean isConditionRespected() {
//			final SelectShapes selection = ActionsRegistry.INSTANCE.getAction(SelectShapes.class);
			return instrument.canvas.getDrawing() != null;
		}
       
    }

}

