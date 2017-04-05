
package vectordraw;

import com.google.inject.Injector;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import vectordraw.view.Canvas;


class VDBuilderFactory implements BuilderFactory {
	private final Injector injector;
	private final BuilderFactory defaultFactory;

	VDBuilderFactory(final Injector inj) {
		super();
		injector = inj;
		defaultFactory = new JavaFXBuilderFactory();
	}

	@Override
	public Builder<?> getBuilder(final Class<?> type) {
		if(type == Canvas.class) return () -> injector.getInstance(type);
		return defaultFactory.getBuilder(type);
	}
}
