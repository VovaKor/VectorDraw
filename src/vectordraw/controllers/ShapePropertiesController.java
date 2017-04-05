
package vectordraw.controllers;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;

/**
 * This meta-instrument manages the instruments that customises shape properties.
 * 
 */
public class ShapePropertiesController extends AShapePropertyCustomiser implements Initializable {
	/** This instrument customises the line properties of shapes and the pencil. */
	@Inject protected ShapeBorderController borderCustomiser;

	/** This instrument customises the filling properties of shapes and the pencil. */
	@Inject protected ShapeFillingController fillingCustomiser;

		/** This instrument customises the arc parameters. */
	@Inject protected ShapeArcController arcCustomiser;

	/** This instrument customises the dimensions and the position. */
	@Inject protected ShapePositionOnPageController dimPosCustomiser;

	/** This instrument groups shapes. */
	@Inject protected ShapeGrouperController shapeGrouper;

	/** This instrument that places shapes. */
	@Inject protected ShapePositionZController shapeZPositioner;

	/**
	 * Creates the instrument.
	 */
	public ShapePropertiesController() {
		super();
	}

	@Override
	public void setActivated(final boolean act) {
		super.setActivated(act);

		if(act) {
			update();
		}
		else {
			borderCustomiser.setActivated(false);
			fillingCustomiser.setActivated(false);
			arcCustomiser.setActivated(false);
			dimPosCustomiser.setActivated(false);
			shapeGrouper.setActivated(false);
			shapeZPositioner.setActivated(false);
		}
	}

	@Override
	protected void update(final IGroup shape) {
		borderCustomiser.update(shape);
		fillingCustomiser.update(shape);
		arcCustomiser.update(shape);
		dimPosCustomiser.update(shape);
		shapeGrouper.update(shape);
		shapeZPositioner.update(shape);
	}

	@Override
	public void clearEvents() {
		borderCustomiser.clearEvents();
		fillingCustomiser.clearEvents();
		arcCustomiser.clearEvents();
		dimPosCustomiser.clearEvents();
		shapeGrouper.clearEvents();
		shapeZPositioner.clearEvents();
	}

	@Override
	protected void initialiseInteractors() {
		// This instrument does not have any link.
	}

	@Override
	protected void setWidgetsVisible(final boolean visible) {
		// This instrument does not have any widget.
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		drawing.getSelectedShapes().getShapes().addListener((ListChangeListener.Change<? extends IShape> change) -> {
			while(change.next()) {
				if(change.wasRemoved()) {
					if(hand.isActivated()) {
						setActivated(!drawing.getSelectedShapes().isEmpty());
					}
				}else {
					setActivated(true);
				}
			}
		});
	}
}
