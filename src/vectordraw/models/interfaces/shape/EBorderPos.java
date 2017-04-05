
package vectordraw.models.interfaces.shape;

import java.util.Arrays;
import vectordraw.util.Constants;

/** The different positions of the border. */
public enum EBorderPos {
	INTO {
		@Override
		public String getLatexToken() {
			return Constants.BORDERS_INSIDE;
		}
	},
	MID {
		@Override
		public String getLatexToken() {
			return Constants.BORDERS_MIDDLE;
		}
	},
	OUT {
		@Override
		public String getLatexToken() {
			return Constants.BORDERS_OUTSIDE;
		}
	};

	/**
	 * @param style The style to get.
	 * @return The style which name is the given name style.
	 * *
	 */
	public static EBorderPos getStyle(final String style) {
		return Arrays.stream(values()).filter(it -> it.toString().equals(style) || it.getLatexToken().equals(style)).findFirst().orElse(INTO);
	}

	/**
	 * @return The latex token corresponding to the EBorderPos.
 *
	 */
	public abstract String getLatexToken();
}
