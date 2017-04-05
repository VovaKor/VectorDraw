
package vectordraw.controllers;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import vectordraw.view.Canvas;
import org.malai.action.library.Zoom;
import org.malai.javafx.instrument.library.BasicZoomer;
import org.malai.javafx.instrument.library.SpinnerInteractor;

/**
 * The instrument for zooming on the canvas.
 */
public class ZoomController extends BasicZoomer<Canvas> implements Initializable {
	@FXML private Spinner<Double> zoom;
	@Inject private Canvas canvas;

	public ZoomController() {
		super();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		AShapePropertyCustomiser.scrollOnSpinner(zoom);
		setZoomable(canvas);
		setWithKeys(true);
		setActivated(true);

		// Conflict between the standard scroll interaction of the scrollpane
		// and the scroll-zoom interaction. Must consume the event
		canvas.setOnScroll(evt -> {
			if(evt.isControlDown()) {
				evt.consume();
			}
		});
	}

	@Override
	protected void initialiseInteractors() throws InstantiationException, IllegalAccessException {
		super.initialiseInteractors();
		addInteractor(new Spinner2Zoom(this));
	}

	@Override
	public void reinit() {
		interimFeedback();
	}

	@Override
	public void interimFeedback() {
		zoom.getValueFactory().setValue(zoomable.getZoom() * 100d);
	}

	@Override
	public void setActivated(final boolean activated) {
		super.setActivated(activated);
		zoom.setVisible(activated);
	}

	private static class Spinner2Zoom extends SpinnerInteractor<Zoom, ZoomController> {
		Spinner2Zoom(final ZoomController ins) throws InstantiationException, IllegalAccessException {
			super(ins, Zoom.class, ins.zoom);
		}

		@Override
		public void updateAction() {
			action.setZoomLevel(Double.valueOf(interaction.getWidget().getValue().toString()) / 100d);
		}

		@Override
		public void initAction() {
			action.setZoomable(instrument.zoomable);

		}
	}
}
