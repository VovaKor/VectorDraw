package vectordraw.models.interfaces.shape;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;


public interface ISingleShape extends IShape {
	/**
	 * @return The property of the thickness.
	 */
	 DoubleProperty thicknessProperty();
	
	/**
	 * @return The property of the line style.
	 */
	 ObjectProperty<ELineStyle> linestyleProperty();
	
	/**
	 * @return The property of the border position.
	 */
	 ObjectProperty<EBorderPos> borderPosProperty();
	
	/**
	 * @return The property of the line colour.
	 */
	 ObjectProperty<IColor> lineColourProperty();
	
	/**
	 * @return The property of the filling.
	 */
	 ObjectProperty<EFillingStyle> fillingProperty();

	 DoubleProperty dashSepWhiteProperty();

	 DoubleProperty dashSepBlackProperty();

	 DoubleProperty dotSepProperty();

	 BooleanProperty dbleBordProperty();

	 DoubleProperty dbleBordSepProperty();

	 ObjectProperty<IColor> dbleBordColProperty();

	 ObjectProperty<IColor> gradColStartProperty();

	 ObjectProperty<IColor> gradColEndProperty();

	 ObjectProperty<IColor> fillingColProperty();

	 DoubleProperty gradAngleProperty();

	 DoubleProperty gradMidPtProperty();

	 BooleanProperty shadowProperty();

	 ObjectProperty<IColor> shadowColProperty();

	 DoubleProperty shadowAngleProperty();

	 DoubleProperty shadowSizeProperty();

	 DoubleProperty hatchingsAngleProperty();

	 DoubleProperty hatchingsSepProperty();

	 DoubleProperty hatchingsWidthProperty();

	 ObjectProperty<IColor> hatchingsColProperty();
}
