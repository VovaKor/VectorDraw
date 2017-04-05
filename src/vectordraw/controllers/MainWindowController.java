
package vectordraw.controllers;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

import org.malai.javafx.action.library.MoveCamera;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.util.EPage;
import vectordraw.view.Canvas;

/**
 * The class that controls the main frame.
*
 */
public class MainWindowController implements Initializable {

	@FXML private ScrollPane scrollPane;
	@FXML private Canvas canvas;
	
	@Inject private ShapePropertiesController ShapeProperties;

	/**
	 * Creates the controller.
	 */
	public MainWindowController() {
		super();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		scrollPane.widthProperty().addListener(obs -> canvas.update());
		scrollPane.heightProperty().addListener(obs -> canvas.update());
		// Because several instruments are not bound to an FXML widget, have to call the initialization here.
		
		ShapeProperties.initialize(null, null);
	}

	public void centreViewport() {
		Platform.runLater(() -> {
			final MoveCamera action = new MoveCamera();
			final EPage page = canvas.getPage().getPage();
			final IPoint origin = canvas.getOrigin();

			action.setScrollPane(scrollPane);
			action.setPx((origin.getX() + page.getWidth() * IShape.PPC / 2d) / canvas.getWidth());
			action.setPy((origin.getY() + page.getHeight() * IShape.PPC / 5d) / canvas.getHeight());

			if(action.canDo()) {
				action.doIt();
			}

			action.flush();
		});
	}
}
