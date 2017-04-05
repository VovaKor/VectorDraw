package vectordraw.models.interfaces.shape;

import java.util.Arrays;
import vectordraw.util.Constants;


/** The different styles of filling. */
public enum EFillingStyle {
	NONE {
		@Override
		public boolean isFilled() {
			return false;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_NONE;
		}
	},
	GRAD {
		@Override
		public boolean isFilled() {
			return false;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_GRADIENT;
		}
	},
	HLINES {
		@Override
		public boolean isFilled() {
			return false;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_HLINES;
		}
	},
	VLINES {
		@Override
		public boolean isFilled() {
			return false;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_VLINES;
		}
	},
	CLINES {
		@Override
		public boolean isFilled() {
			return false;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_CROSSHATCH;
		}
	},
	PLAIN {
		@Override
		public boolean isFilled() {
			return true;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_SOLID;
		}
	},
	HLINES_PLAIN {
		@Override
		public boolean isFilled() {
			return true;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_HLINES_F;
		}
	},
	VLINES_PLAIN {
		@Override
		public boolean isFilled() {
			return true;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_VLINES_F;
		}
	},
	CLINES_PLAIN {
		@Override
		public boolean isFilled() {
			return true;
		}

		@Override
		public String getLatexToken() {
			return Constants.TOKEN_FILL_CROSSHATCH_F;
		}
	};

	/**
	 * Allows to know if the style is filled.
	 * @return True if the shape is filled.
	 */
	public abstract boolean isFilled();

	/**
	 * @return The latex token corresponding to the filling style.
	 * *
	 */
	public abstract String getLatexToken();

	/**
	 * @param token The style to get.
	 * @return The style which name is the given name style (or null).
	 * *
	 */
	public static  EFillingStyle getStyleFromLatex(final String token) {
		return Arrays.stream(values()).filter(style -> style.getLatexToken().equals(token)).findFirst().orElse(NONE);
	}

	/**
	 * @param style The text version of the filling style.
	 * @return The filling style that corresponds to the given text (or null).
	 * *
	 */
	public static  EFillingStyle getStyle(final String style) {
		return Arrays.stream(values()).filter(item -> item.toString().equals(style)).findFirst().orElse(NONE);
	}

	/**
	 * @return True if the style is a kind of hatchings.
	 * *
	 */
	public boolean isHatchings() {
		return this == HLINES || this == HLINES_PLAIN || this == VLINES || this == VLINES_PLAIN || this == CLINES || this == CLINES_PLAIN;
	}

	/**
	 * @return True if the style is a gradient.
	 * *
	 */
	public boolean isGradient() {
		return this == GRAD;
	}
}
