
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
import javafx.scene.input.KeyCode;
import vectordraw.actions.shape.DeleteShapes;
import vectordraw.actions.shape.SelectShapes;
import vectordraw.models.interfaces.shape.IShape;
import org.malai.action.ActionsRegistry;
import org.malai.javafx.instrument.JfxInteractor;
import org.malai.javafx.interaction.JfxInteraction;
import org.malai.javafx.interaction.library.ButtonPressed;
import org.malai.javafx.interaction.library.KeyPressure;


/**
 * This instrument deletes the selected shapes.
*
 */
public class ShapeDeleteController extends ACanvasInstrument implements Initializable {
	/** The button used to remove the selected shapes. */
	@FXML protected Button deleteB;

	@Inject protected HandInstrument hand;


	/**
	 * Creates the instrument.
	 */
	public ShapeDeleteController() {
		super();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		setActivated(false);

		((ObservableList<IShape>)canvas.getDrawing().getSelectedShapes().getShapes()).addListener(
			(ListChangeListener.Change<? extends IShape> evt) -> setActivated(hand.isActivated() && !evt.getList().isEmpty()));
	}


	@Override
	public void setActivated(final boolean activ) {
		super.setActivated(activ);
		updateWidgets(false);
	}

	/**
	 * Updates the widgets of this instrument.
	 * @param hideWidgets True: the widgets are hidden on deactivation.
	 * *
	 */
	protected void updateWidgets(final boolean hideWidgets) {
		deleteB.setVisible(activated || !hideWidgets);
		deleteB.setDisable(!activated);
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		addInteractor(new DeleteShapesInteractor<>(this, ButtonPressed.class, deleteB));
		addInteractor(new DeleteShapesInteractor<KeyPressure>(this, KeyPressure.class, canvas) {
			@Override
			public boolean isConditionRespected() {
				return interaction.getKeyCode().isPresent() && interaction.getKeyCode().get() == KeyCode.DELETE && super.isConditionRespected();
			}
		});
	}


	/** This abstract link maps an interaction to an action that delete shapes. */
	private static class DeleteShapesInteractor<I extends JfxInteraction> extends JfxInteractor<DeleteShapes, I, ShapeDeleteController> {
		DeleteShapesInteractor(final ShapeDeleteController ins, final Class<I> clazzInteraction, Node widget) throws InstantiationException, IllegalAccessException {
			super(ins, false, DeleteShapes.class, clazzInteraction, widget);
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

