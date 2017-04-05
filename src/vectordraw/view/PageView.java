package vectordraw.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;
import vectordraw.util.EPage;


/**
 * The different page sizes that can be used.
 * 
 */
public class PageView extends Group {
	/** The gap between the page and its shadow. */
	private static final int GAP_SHADOW = 3;

	/** The size of the shadow of the page. */
	private static final int SIZE_SHADOW = 4;

	/** The main rectangle of the page. */
	private final  Rectangle recPage;

	/** The shadow rectangle of the page. */
	private final  Rectangle recShadowBottom;
	/** The shadow rectangle of the page. */
	private final  Rectangle recShadowRight;

	/** The origin point where the page has to ben placed. */
	private final  IPoint origin;

	/** The current page format. */
	private EPage format;

	/**
	 * Creates a view of a page.
	 * @param page The page format. Cannot be null.
	 * @param orig The origin point where the page has to be placed. Cannot be null.
	 */
	public PageView(final EPage page, final IPoint orig) {
		super();

		format = page;
		origin = orig;
		recPage = new Rectangle();
		recShadowBottom = new Rectangle();
		recShadowRight = new Rectangle();

		recPage.setStrokeWidth(1.0);
		recPage.setStroke(Color.BLACK);
		recPage.setFill(Color.WHITE);
		recShadowRight.setStroke(null);
		recShadowBottom.setStroke(null);
		recShadowRight.setFill(Color.GRAY);
		recShadowBottom.setFill(Color.GRAY);

		getChildren().add(recShadowBottom);
		getChildren().add(recShadowRight);
		getChildren().add(recPage);

		recPage.toFront();
		setMouseTransparent(true);
		setPage(page);
	}

	/**
	 * @return The current page format.
	 */
	public EPage getPage() {
		return format;
	}

	/**
	 * Sets the current page format.
	 * @param page The new page format to use. Cannot be null.
	 */
	public void setPage(final EPage page) {
		recPage.setX(origin.getX());
		recPage.setY(origin.getY());
		recPage.setWidth(page.getWidth() * IShape.PPC);
		recPage.setHeight(page.getHeight() * IShape.PPC);

		recShadowRight.setX(recPage.getX() + recPage.getWidth());
		recShadowRight.setY(recPage.getY() + GAP_SHADOW);
		recShadowRight.setWidth(SIZE_SHADOW);
		recShadowRight.setHeight(recPage.getHeight());

		recShadowBottom.setX(recPage.getX() + GAP_SHADOW);
		recShadowBottom.setY(recPage.getY() + recPage.getHeight());
		recShadowBottom.setWidth(recPage.getWidth());
		recShadowBottom.setHeight(SIZE_SHADOW);
	}
}
