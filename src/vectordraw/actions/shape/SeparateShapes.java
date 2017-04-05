package vectordraw.actions.shape;

import java.util.ArrayList;
import java.util.List;
import vectordraw.actions.ADrawingActionImpl;
import vectordraw.actions.IModifying;
import vectordraw.actions.IShapesAction;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;

/**
 *
 * @author Volodymyr Korobko
 */
public class SeparateShapes extends ADrawingActionImpl implements IShapesAction, IModifying{
   	/** The shapes to handle. */
	final List<IShape> shapes;

	public SeparateShapes() {
		super();
		
		shapes = new ArrayList<>();
	}

	@Override
	protected void doActionBody() {
		separateShapes();
	}

	@Override
	public boolean canDo() {
		return super.canDo() && !shapes.isEmpty();
	}

	private void separateShapes() {
		drawing.ifPresent(dr -> {
//                    
			final List<IShape> drawingSh = dr.getShapes();
			shapes.stream().sorted((s1, s2) -> drawingSh.indexOf(s1) < drawingSh.indexOf(s2) ? -1 : 1)
                                .forEach(sh -> {
                                  if (sh instanceof IGroup) {
                                IGroup shape = (IGroup) sh;
                                dr.getShapes().addAll(shape.getShapes());
                                dr.removeShape(shape);
                            }
			 				  
			});
		
			dr.setModified(true);
		});
	}


	@Override
	public boolean isRegisterable() {
		return true;
	}

	@Override
	public List<IShape> getShapes() {
		return shapes;
	}
}
