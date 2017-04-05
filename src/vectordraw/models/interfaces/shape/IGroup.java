package vectordraw.models.interfaces.shape;

import java.util.List;
import vectordraw.models.interfaces.prop.IArcProp;
import vectordraw.models.interfaces.prop.ILineArcProp;
import vectordraw.models.interfaces.prop.ISetShapesProp;


/**
 * Defines an interface that classes defining a group of shapes should implement.
 *
 */
public interface IGroup extends IShape, ISetShapesProp, ILineArcProp,
	IArcProp {
	/**
	 * Duplicates the group of shapes.
	 * @param duplicateShapes True: the shapes will be duplicated as well.
	 * @return The duplicated group of shapes.
	 * *
	 */
	IGroup duplicateDeep(final boolean duplicateShapes);
	
	/**
	 * @return The list of the border positions of the shapes contained by the group.
	 * If a shape of the group does not support the border position property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<EBorderPos> getBordersPositionList();


	/**
	 * @return The list of line colours of the shapes contained by the group.
	 * If a shape of the group does not support the line colour property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<IColor> getLineColourList();


	/**
	 * @return The list of start angle of the arc shapes contained by the group.
	 * If a shape of the group does not support the start angle property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getAngleStartList();

	/**
	 * @return The list of end angle of the arc shapes contained by the group.
	 * If a shape of the group does not support the end angle property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getAngleEndList();

	/**
	 * @return The list of arc styles of the arc shapes contained by the group.
	 * If a shape of the group does not support the arc style property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<EArcStyle> getArcStyleList();

	

	/**
	 * @return The list of the rotation angles of the shapes contained by the group.
	 * If a shape of the group does not support the rotation angle property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getRotationAngleList();

	/**
	 * @return The list of the text positions the shapes contained by the group.
	 * If a shape of the group does not support the text position property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<TextPosition> getTextPositionList();

	/**
	 * @return The list of the text contents of the shapes contained by the group.
	 * If a shape of the group does not support the text property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<String> getTextList();

	/**
	 * @return The list of the hatchings angle of the shapes contained by the group.
	 * If a shape of the group does not support the hatchings angle property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getHatchingsAngleList();

	/**
	 * @return The list of the hatchings width of the shapes contained by the group.
	 * If a shape of the group does not support the hatchings width property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getHatchingsWidthList();

	/**
	 * @return The list of the hatchings size of the shapes contained by the group.
	 * If a shape of the group does not support the hatchings size property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getHatchingsSepList();

	/**
	 * @return The list of the gradient angle of the shapes contained by the group.
	 * If a shape of the group does not support the gradient angle property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getGradAngleList();

	/**
	 * @return The list of the gradient middle point of the shapes contained by the group.
	 * If a shape of the group does not support the gradient middle point property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getGradMidPtList();

	/**
	 * @return The list of the line arc values of the shapes contained by the group.
	 * If a shape of the group does not support the line arc property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getLineArcList();

	/**
	 * @return The list of filling colours of the shapes contained by the group.
	 * If a shape of the group does not support the filling colour property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<IColor> getFillingColList();

	/**
	 * @return The list of hatchings colours of the shapes contained by the group.
	 * If a shape of the group does not support the hatchings colour property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<IColor> getHatchingsColList();

	/**
	 * @return The list of boolean defining if the shapes contained by the group have double borders.
	 * If a shape of the group does not support the double border property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Boolean> hasDbleBordList();

	/**
	 * @return The list of double border width of the shapes contained by the group.
	 * If a shape of the group does not support the double border width property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getDbleBordSepList();

	/**
	 * @return The list of double border colours of the shapes contained by the group.
	 * If a shape of the group does not support the double border colour property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<IColor> getDbleBordColList();

	/**
	 * @return The list of boolean defining if the shapes contained by the group have shadow.
	 * If a shape of the group does not support the shadow property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Boolean> hasShadowList();

	/**
	 * @return The list of shadow size of the shapes contained by the group.
	 * If a shape of the group does not support the shadow size property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getShadowSizeList();

	/**
	 * @return The list of shadow angle of the shapes contained by the group.
	 * If a shape of the group does not support the shadow angle property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getShadowAngleList();

	/**
	 * @return The list of shadow colours of the shapes contained by the group.
	 * If a shape of the group does not support the shadow colour property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<IColor> getShadowColList();

	/**
	 * @return The list of ending gradient colours of the shapes contained by the group.
	 * If a shape of the group does not support the ending gradient colour property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<IColor> getGradColStartList();

	/**
	 * @return The list of starting gradient colours of the shapes contained by the group.
	 * If a shape of the group does not support the starting gradient colour property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<IColor> getGradColEndList();

	/**
	 * @return The list of the thicknesses of the shapes contained by the group.
	 * If a shape of the group does not support the thickness property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Double> getThicknessList();

	/**
	 * @return The list of the filling styles of the shapes contained by the group.
	 * If a shape of the group does not support the filling style property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<EFillingStyle> getFillingStyleList();

	/**
	 * @return The list of the line styles of the shapes contained by the group.
	 * If a shape of the group does not support the line style property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
	List<ELineStyle> getLineStyleList();

	/**
	 * Sets the starting angle of the arcable shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setAngleStartList(final List<Double> values);

	/**
	 * Sets the border position of the shapes of the group.
	 * @param list The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setBordersPositionList(final List<EBorderPos> list);

	/**
	 * Sets the line colour of the shapes of the group.
	 * @param list The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setLineColourList(final List<IColor> list);

	/**
	 * Sets the dot style of the dottable shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setDotStyleList(final List<DotStyle> values);

	/**
	 * Sets the ending angle of the arcable shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setAngleEndList(final List<Double> values);

	/**
	 * Sets the arc style of the arcable shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setArcStyleList(final List<EArcStyle> values);

	
	/**
	 * Sets the rotation angle of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setRotationAngleList(final List<Double> values);

	
	/**
	 * Sets the hatchings angle of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setHatchingsAngleList(final List<Double> values);

	/**
	 * Sets the hatchings width of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setHatchingsWidthList(final List<Double> values);

	/**
	 * Sets the hatchings gap of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setHatchingsSepList(final List<Double> values);

	/**
	 * Sets the starting angle of the gradient of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setGradAngleList(final List<Double> values);

	/**
	 * Sets the middle point reference of the gradient of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setGradMidPtList(final List<Double> values);

	/**
	 * Sets the line arc value of the line-arcable shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setLineArcList(final List<Double> values);

	/**
	 * Sets the filling colour of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setFillingColList(final List<IColor> values);

	/**
	 * Sets the hatchings colour of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setHatchingsColList(final List<IColor> values);

	/**
	 * Defines if the shapes of the group have double borders.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setHasDbleBordList(final List<Boolean> values);

	/**
	 * Sets the double border gap of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setDbleBordSepList(final List<Double> values);

	/**
	 * Sets the double borders colour of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setDbleBordColList(final List<IColor> values);

	/**
	 * Defines if the shapes of the group have a shadow.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setHasShadowList(final List<Boolean> values);

	/**
	 * Sets the shadow sizes of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setShadowSizeList(final List<Double> values);

	/**
	 * Sets the shadow angles of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setShadowAngleList(final List<Double> values);

	/**
	 * Sets the shadow colour of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setShadowColList(final List<IColor> values);

	/**
	 * Sets the first gradient colour of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setGradColStartList(final List<IColor> values);

	/**
	 * Sets the last gradient colour of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setGradColEndList(final List<IColor> values);

	/**
	 * Sets the thickness of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setThicknessList(final List<Double> values);

	/**
	 * Sets the style of the filling of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setFillingStyleList(final List<EFillingStyle> values);

	/**
	 * Sets the line style colour of the shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setLineStyleList(final List<ELineStyle> values);

	/**
	 * Sets the filling colour of the dot shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setDotFillingColList(final List<IColor> values);

	/**
	 * Sets the size of the dot shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setDotSizeList(final List<Double> values);

	/**
	 * Sets the starting points of the grid shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridStartList(final List<IPoint> values);

	/**
	 * @return The list of the starting points of the grid shapes contained by the group.
	 * If a shape of the group does not support the starting point property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<IPoint> getGridStartList();

	/**
	 * Sets the ending points of the grid shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridEndList(final List<IPoint> values);

	/**
	 * @return The list of the ending points of the grid shapes contained by the group.
	 * If a shape of the group does not support the starting point property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<IPoint> getGridEndList();

	/**
	 * Sets the origin points of the grid shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridOriginList(final List<IPoint> values);

	/**
	 * @return The list of the origin points of the grid shapes contained by the group.
	 * If a shape of the group does not support the starting point property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<IPoint> getGridOriginList();

	/**
	 * Sets the size of the labels of the grid shapes of the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridLabelSizeList(final List<Integer> values);

	/**
	 * @return The list of the sizes of the labels of the grid shapes contained by the group.
	 * If a shape of the group does not support the starting point property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Integer> getGridLabelSizeList();

	/**
	 * Sets the Y-coordinate of the labels of the grid contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridXLabelSouthList(final List<Boolean> values);

	/**
	 * @return The list of the Y-coordinate labels of the grid shapes contained by the group.
	 * If a shape of the group does not support the starting point property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Boolean> getGridXLabelSouthList();

	/**
	 * Sets the X-coordinate of the labels of the grid contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridYLabelWestList(final List<Boolean> values);

	/**
	 * @return The list of the Y-coordinate labels of the grid shapes contained by the group.
	 * If a shape of the group does not support the starting point property, null is added
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Boolean> getGridYLabelWestList();

	/**
	 * Sets the style of the axes contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setAxesStyleList(final List<AxesStyle> values);

	/**
	 * @return The list of the styles of the axes contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<AxesStyle> getAxesStyleList();

	/**
	 * Sets the style of the axes' ticks contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setAxesTicksStyleList(final List<TicksStyle> values);

	/**
	 * @return The list of the styles of the axes' ticks contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<TicksStyle> getAxesTicksStyleList();

	/**
	 * Sets the size of the axes' ticks contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setAxesTicksSizeList(final List<Double> values);

	/**
	 * @return The list of the sizes of the axes' ticks contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<Double> getAxesTicksSizeList();

	/**
	 * Sets how the ticks of the axes contained by the group are displayed.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setAxesTicksDisplayedList(final List<PlottingStyle> values);

	/**
	 * @return The list of the plotting styles of the axes' ticks contained in the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<PlottingStyle> getAxesTicksDisplayedList();

	/**
	 * Sets the labels' increments of the axes contained by the group are displayed.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setAxesIncrementsList(final List<IPoint> values);

	/**
	 * @return The list of the labels' increments of the axes' ticks contained in the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<IPoint> getAxesIncrementsList();

	/**
	 * Sets how the labels of the axes contained by the group are displayed.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setAxesLabelsDisplayedList(final List<PlottingStyle> values);

	/**
	 * @return The list of the plotting styles of the axes' labels contained in the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<PlottingStyle> getAxesLabelsDisplayedList();

	/**
	 * Defines if the origin of the axes contained by the group are displayed.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setAxesShowOriginList(final List<Boolean> values);

	/**
	 * @return The list of booleans defining if the origin of the axes contained in the group must be shown.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<Boolean> getAxesShowOriginList();

	/**
	 * Sets the distances between the labels of the axes contained by the group are displayed.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setAxesDistLabelsList(final List<IPoint> values);

	/**
	 * @return The list of the distances between the labels of the axes contained in the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<IPoint> getAxesDistLabelsList();

	/**
	 * Sets the labels' colours of the grids contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridLabelsColourList(final List<IColor> values);

	/**
	 * @return The list of labels' colours of the grids contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<IColor> getGridLabelsColourList();

	/**
	 * Sets the labels' colours of the grids contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setSubGridColourList(final List<IColor> values);

	/**
	 * @return The list of labels' colours of the grids contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<IColor> getSubGridColourList();

	/**
	 * Sets the width of the grids contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridWidthList(final List<Double> values);

	/**
	 * @return The width of the grids contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Double> getGridWidthList();

	/**
	 * Sets the width of the sub-grids contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setSubGridWidthList(final List<Double> values);

	/**
	 * @return The width of the sub-grids contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Double> getSubGridWidthList();

	/**
	 * Sets the number of dots composing the main lines of each grids contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setGridDotsList(final List<Integer> values);

	/**
	 * @return The number of dots composing the main lines of each grids contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Integer> getGridDotsList();

	/**
	 * Sets the number of dots composing the sub-lines of each grids contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setSubGridDotsList(final List<Integer> values);

	/**
	 * @return The number of dots composing the sub-lines of each grids contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Integer> getSubGridDotsList();

	/**
	 * Sets the division of the sub-lines of each grids contained by the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setSubGridDivList(final List<Integer> values);

	/**
	 * @return The division of the sub-lines of each grids contained by the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
//	List<Integer> getSubGridDivList();

	/**
	 * Sets the type of the freehand shapes contained in the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
//	void setFreeHandTypeList(final List<FreeHandStyle> values);

	/**
	 * @return The types of the freehand shapes contained in the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<FreeHandStyle> getFreeHandTypeList();

	/**
	 * Sets the interval of the freehand shapes contained in the group.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setFreeHandIntervalList(final List<Integer> values);

	/**
	 * @return The intervals of the freehand shapes contained in the group.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<Integer> getFreeHandIntervalList();

	/**
	 * Defines if the freehand shapes contained in the group are open.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	//void setFreeHandOpenList(final List<Boolean> values);

	/**
	 * @return The boolean value defining if the freehand shapes contained in the group are open.
	 * If a shape of the group is not an axe, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	//List<Boolean> getFreeHandOpenList();

	/**
	 * @return The boolean value defining if the shapes contained in the group must show their points.
	 * If a shape of the group cannot show their points, null is added.
	 * to the list. The list cannot be null.
	 * *
	 */
	List<Boolean> getShowPointsList();

	/**
	 * Defines if the shapes contained in the group must show their points.
	 * @param values The list of values to use. Its must must equals the number of
	 * shapes of the group. If an element of the list is null, its corresponding
	 * shape will not be set.
	 * *
	 */
	void setShowPointsList(final List<Boolean> values);

}
