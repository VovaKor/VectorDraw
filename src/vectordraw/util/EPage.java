
package vectordraw.util;

/**
 * The different page formats.
 */
public enum EPage {
	/** The US letter format. */
	USLETTER {
		@Override
		public double getWidth() {
			return 21.6;
		}

		@Override
		public double getHeight() {
			return 27.9;
		}
	};

	/**
	 * @return The width of the page in CM.
	 *
	 */
	public abstract double getWidth();

	/**
	 * @return The height of the page in CM.
	 *
	 */
	public abstract double getHeight();
}
