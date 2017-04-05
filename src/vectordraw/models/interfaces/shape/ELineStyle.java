package vectordraw.models.interfaces.shape;

import java.util.Arrays;
import vectordraw.util.Constants;


/** The different styles of the lines. */
public enum ELineStyle {
	// NONE{
	// @Override
	// public String getToken() { return Constants.LINE_NONE_STYLE; }
	// },
	SOLID {
		@Override
		public String getToken() {
			return Constants.LINE_SOLID_STYLE;
		}
	},
	DASHED {
		@Override
		public String getToken() {
			return Constants.LINE_DASHED_STYLE;
		}
	},
	DOTTED {
		@Override
		public String getToken() {
			return Constants.LINE_DOTTED_STYLE;
		}
	};

	/**
	 * @param style The style to get.
	 * @return The style which name is the given name style.
	 * *
	 */
	public static  ELineStyle getStyle(final String style) {
		return Arrays.stream(values()).filter(it -> it.toString().equals(style)).findFirst().orElse(SOLID);
	}

	/**
	 * @return The token corresponding to the line style.
	 * *
	 */
	public abstract String getToken();
}
