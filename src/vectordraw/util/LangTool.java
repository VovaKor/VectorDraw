package vectordraw.util;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Defines the different localizations.
 * 
 */
public final class LangTool {
	public static final LangTool INSTANCE = new LangTool();
	
	private final ResourceBundle bundle;
	
	private LangTool() {
		super();
		bundle = getDefaultLanguage();
	}

	
	public ResourceBundle getBundle() {
		return bundle;
	}

	
	/** @return The language used by default. */
	private ResourceBundle getDefaultLanguage() {
		return loadResourceBundle(Locale.getDefault());
	}
	
	
	private ResourceBundle loadResourceBundle(Locale locale) {
		try {
			return ResourceBundle.getBundle("lang.VectorDraw", Locale.US);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
