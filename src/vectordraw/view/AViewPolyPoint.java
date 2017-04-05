
package vectordraw.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathElement;
import vectordraw.models.interfaces.shape.IModifiablePointsShape;
import vectordraw.models.interfaces.shape.IPoint;


/**
 * The JFX shape view for multipoints shapes.
 */
abstract class AViewPolyPoint<T extends IModifiablePointsShape> extends AViewPathShape<T> {
	final MoveTo moveTo;
	final List<LineTo> lineTos;

	/**
	 * Creates the view.
	 * @param sh The model.
	 */
	AViewPolyPoint(final  T sh) {
		super(sh);

		final ObservableList<PathElement> elts = border.getElements();
		moveTo = new MoveTo();
		moveTo.xProperty().bind(sh.getPtAt(0).xProperty());
		moveTo.yProperty().bind(sh.getPtAt(0).yProperty());
		elts.add(moveTo);

		lineTos = new ArrayList<>();

		IntStream.range(1, sh.getNbPoints()).forEach(i -> {
			LineTo lineto = new LineTo();
			lineto.xProperty().bind(sh.getPtAt(i).xProperty());
			lineto.yProperty().bind(sh.getPtAt(i).yProperty());
			lineTos.add(lineto);
			elts.add(lineto);
		});

		model.getPoints().addListener((ListChangeListener.Change<? extends IPoint> c) -> {
			while(c.next()) {
				if(c.wasAdded()) {
					c.getAddedSubList().forEach(pt -> {
						LineTo lineto = new LineTo();
						lineto.xProperty().bind(pt.xProperty());
						lineto.yProperty().bind(pt.yProperty());
						lineTos.add(lineto);
						elts.add(lineto);
						if(elts.get(elts.size()-2) instanceof ClosePath) {
							elts.remove(elts.size()-2);
							elts.add(new ClosePath());
						}
					});
				}
			}
		});
	}

	@Override
	public void flush() {
		moveTo.xProperty().unbind();
		moveTo.yProperty().unbind();

		lineTos.forEach(lineTo -> {
			lineTo.xProperty().unbind();
			lineTo.yProperty().unbind();
		});
		lineTos.clear();
		super.flush();
	}
}
