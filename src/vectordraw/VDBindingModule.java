package vectordraw;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import vectordraw.controllers.HandInstrument;
import vectordraw.controllers.ShapePropertiesController;
import vectordraw.controllers.MainWindowController;
import vectordraw.controllers.OpenSaveController;
import vectordraw.controllers.PencilInstrument;
import vectordraw.controllers.ShapeArcController;
import vectordraw.controllers.ShapeBorderController;
import vectordraw.controllers.ShapeDeleteController;
import vectordraw.controllers.ShapeFillingController;
import vectordraw.controllers.ShapeGrouperController;
import vectordraw.controllers.ShapePositionOnPageController;
import vectordraw.controllers.ShapePositionZController;
import vectordraw.controllers.ShapeTypesBarController;
import vectordraw.controllers.ZoomController;
import vectordraw.models.interfaces.shape.IDrawing;
import vectordraw.view.Canvas;

/**
 *
 * @author Volodymyr Korobko
 */
class VDBindingModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Canvas.class).asEagerSingleton();
		bind(ShapeTypesBarController.class).asEagerSingleton();
		bind(OpenSaveController.class).asEagerSingleton();
		bind(HandInstrument.class).asEagerSingleton();
		bind(ShapePropertiesController.class).asEagerSingleton();
		bind(PencilInstrument.class).asEagerSingleton();
		bind(ShapeArcController.class).asEagerSingleton();		
		bind(ShapeBorderController.class).asEagerSingleton();
		bind(ShapePositionOnPageController.class).asEagerSingleton();
		bind(ShapeDeleteController.class).asEagerSingleton();		
		bind(ShapeFillingController.class).asEagerSingleton();				
		bind(ShapeGrouperController.class).asEagerSingleton();		
		bind(ShapePositionZController.class).asEagerSingleton();
		bind(MainWindowController.class).asEagerSingleton();
		bind(ZoomController.class).asEagerSingleton();
	}

	@Provides
	IDrawing provideDrawing(final Canvas canvas) {
		return canvas.getDrawing();
	}


}

