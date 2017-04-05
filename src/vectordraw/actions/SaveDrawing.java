package vectordraw.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javafx.stage.FileChooser;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.jfxconverter.JFXConverter;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import vectordraw.view.Canvas;

/**
 *
 * @author Volodymyr Korobko
 */
public class SaveDrawing extends ADrawingActionImpl{
    
    /** The file chooser that will be used to select the location to save. */
	protected FileChooser fileChooser;
        private File file;
        private Canvas canvas;
        private JFXConverter converter;
        private DOMImplementation domImpl;
        private final String SVGNS = "http://www.w3.org/2000/svg";
        private Document document;
        private SVGGraphics2D svgGenerator;

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
        
        
    public SaveDrawing() {
		super();
		
	}

    @Override
    protected void doActionBody() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG files", "*.svg"));
        fileChooser.setTitle("Save drawing");
        file = fileChooser.showSaveDialog(canvas.getScene().getWindow());
        if (file != null) {
            converter = new JFXConverter();
            // Get a DOMImplementation.
            domImpl = GenericDOMImplementation.getDOMImplementation();
            // Create an instance of org.w3c.dom.Document.
            document = domImpl.createDocument(SVGNS, "svg", null);
            // Create an instance of the SVG Generator.
            svgGenerator = new SVGGraphics2D(document);
            //Convert FX Node to Swing Graphics2D 
            converter.convert(svgGenerator,canvas.getViews());
            //Print SVG to file
            try (FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
                    Writer out = new OutputStreamWriter(fileOutputStream)) {
                       svgGenerator.stream(out);
                        		
                    } catch (IOException ex) {
                       ex.printStackTrace();
                    } 
        }
   
       done();
    }

    @Override
    public boolean isRegisterable() {
        return true;
    }
    
    @Override
    public boolean canDo() {
        return drawing.isPresent();
    }

    
}
