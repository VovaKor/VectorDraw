
package vectordraw.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathElement;
import vectordraw.models.interfaces.shape.IBezierCurve;
import vectordraw.models.interfaces.shape.IPoint;


/**
 * The JFX shape view for Bézier curves.
 */
public class ViewBezierCurve extends AViewPathShape<IBezierCurve> {
	final MoveTo moveTo;
	final List<CubicCurveTo> curvesTo;
	final Group showPoint;

	/**
	 * Creates the view.
	 * @param sh The model.
	 */
	ViewBezierCurve(final  IBezierCurve sh) {
		super(sh);
		ObservableList<PathElement> elts = border.getElements();
		showPoint = new Group();
		moveTo = new MoveTo();
		moveTo.xProperty().bind(sh.getPtAt(0).xProperty());
		moveTo.yProperty().bind(sh.getPtAt(0).yProperty());
		elts.add(moveTo);

		curvesTo = new ArrayList<>();
		addCurveTo(sh.getPtAt(1), model.getFirstCtrlPtAt(1), model.getFirstCtrlPtAt(1));

		IntStream.range(2, sh.getNbPoints()).forEach(index ->
			addCurveTo(sh.getPtAt(index), model.getSecondCtrlPtAt(index-1), model.getFirstCtrlPtAt(index)));

		model.getPoints().addListener((ListChangeListener.Change<? extends IPoint> c) -> {
			while(c.next()) {
				if(c.wasAdded()) {
					c.getAddedSubList().forEach(pt -> {
						addCurveTo(pt, model.getSecondCtrlPtAt(model.getNbPoints()-2), model.getFirstCtrlPtAt(model.getNbPoints()-1));
					});
				}
			}
		});
	}

	private void addCurveTo(final IPoint pt, final IPoint ctrl1, final IPoint ctrl2) {
		CubicCurveTo curveto = new CubicCurveTo();
		curveto.xProperty().bind(pt.xProperty());
		curveto.yProperty().bind(pt.yProperty());
		curveto.controlX1Property().bind(ctrl1.xProperty());
		curveto.controlY1Property().bind(ctrl1.yProperty());
		curveto.controlX2Property().bind(ctrl2.xProperty());
		curveto.controlY2Property().bind(ctrl2.yProperty());
		curvesTo.add(curveto);
		border.getElements().add(curveto);
	}


	@Override
	public void flush() {
		moveTo.xProperty().unbind();
		moveTo.yProperty().unbind();
		curvesTo.forEach(to -> {
			to.xProperty().unbind();
			to.yProperty().unbind();
			to.controlX1Property().unbind();
			to.controlX2Property().unbind();
			to.controlY1Property().unbind();
			to.controlY2Property().unbind();
		});
		curvesTo.clear();
		super.flush();
	}

	
}
