
package vectordraw.models.interfaces.shape;

import java.awt.geom.Arc2D;
import vectordraw.util.LangTool;

/** The different styles of arc. */
public enum EArcStyle {
	WEDGE {
		@Override
		public boolean supportArrow() {
			return false;
		}

		@Override
		public String getLabel() {
			return LangTool.INSTANCE.getBundle().getString("Arc.arc"); 
		}

		@Override
		public int getJava2DArcStyle() {
			return Arc2D.PIE;
		}
	},
	ARC {
		@Override
		public boolean supportArrow() {
			return true;
		}

		@Override
		public String getLabel() {
			return LangTool.INSTANCE.getBundle().getString("Arc.wedge"); 
		}

		@Override
		public int getJava2DArcStyle() {
			return Arc2D.OPEN;
		}

	},
	CHORD {
		@Override
		public boolean supportArrow() {
			return false;
		}

		@Override
		public String getLabel() {
			return LangTool.INSTANCE.getBundle().getString("Arc.chord"); 
		}

		@Override
		public int getJava2DArcStyle() {
			return Arc2D.CHORD;
		}

	};

	/**
	 * @return True if the arc type can have arrows.
	 * *.0
	 */
	public abstract boolean supportArrow();

	/**
	 * @return The translated label of the arc type.
	 * *
	 */
	public abstract String getLabel();

	/**
	 * @return The Java value corresponding to the arc style.
	 *
	 */
	public abstract int getJava2DArcStyle();
}
