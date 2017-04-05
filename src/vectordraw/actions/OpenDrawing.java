package vectordraw.actions;

import java.io.File;
import javafx.scene.Group;
import javafx.stage.FileChooser;
import vectordraw.view.Canvas;
import afester.javafx.svg.SvgLoader;
import java.util.List;
import java.util.stream.Collectors;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;
/**
 *
 * @author Volodymyr Korobko
 */
public class OpenDrawing extends ADrawingActionImpl{
    protected FileChooser fileChooser;
        private File file;
        private Canvas canvas;
     
    public OpenDrawing() {
		super();
		
	}
     public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    protected void doActionBody() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG files", "*.svg"));
        fileChooser.setTitle("Load drawing");
        file = fileChooser.showOpenDialog(canvas.getScene().getWindow());
        if (file != null) {
            SvgLoader loader = new SvgLoader();
//            Group svgImage = loader.loadSvg(file.getAbsolutePath());
//            canvas.getViews().getChildren().add(svgImage);
            IGroup svgShapes = loader.loadSvgToIGroup(file.getAbsolutePath());
            drawing.ifPresent(dr -> {
			dr.addShape(svgShapes);
			dr.setModified(true);
		});
        }  
        done();
    }

    @Override
    public boolean isRegisterable() {
        return true;
    }
    
}
