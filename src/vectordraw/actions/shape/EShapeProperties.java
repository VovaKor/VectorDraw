
package vectordraw.actions.shape;

import java.util.Collections;
import java.util.List;
import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.EBorderPos;
import vectordraw.models.interfaces.shape.EFillingStyle;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.ELineStyle;
import vectordraw.util.LangTool;
import vectordraw.models.interfaces.shape.IColor;

/**
 * Defines shape properties.
 */
public enum EShapeProperties {
	
	/** Show/Hide the origin of the axes. */
	SHOW_POINTS {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.12"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Boolean;
		}

		@Override
		public List<Boolean> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Boolean>emptyList() : group.getShowPointsList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setShowPts((Boolean)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setShowPointsList((List<Boolean>)values);
		}
	},
		
	/** Modification of the start angle of arcs. */
	ARC_START_ANGLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.17"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getAngleStartList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setAngleStart((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setAngleStartList((List<Double>)values);
		}
	},
	/** Modification of the end angle of arcs. */
	ARC_END_ANGLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.17"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getAngleEndList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setAngleEnd((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setAngleEndList((List<Double>)values);
		}
	},
	/** Modification of the style of arcs. */
	ARC_STYLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.17"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof EArcStyle;
		}

		@Override
		public List<EArcStyle> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<EArcStyle>emptyList() : group.getArcStyleList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setArcStyle((EArcStyle)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setArcStyleList((List<EArcStyle>)values);
		}
	},
	
	
	/** Modification of the hatchings angle of shapes. */
	HATCHINGS_ANGLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.19"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getHatchingsAngleList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setHatchingsAngle((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setHatchingsAngleList((List<Double>)values);
		}
	},
	/** Modification of the hatchings width a shape. */
	HATCHINGS_WIDTH {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.19"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getHatchingsWidthList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setHatchingsWidth((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setHatchingsWidthList((List<Double>)values);
		}
	},
	/** Modification of the hatching spacing a shape. */
	HATCHINGS_SEP {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.19"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getHatchingsSepList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setHatchingsSep((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setHatchingsSepList((List<Double>)values);
		}
	},
	/** Modification of the gradient angle a shape. */
	GRAD_ANGLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.20"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getGradAngleList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setGradAngle((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setGradAngleList((List<Double>)values);
		}
	},
	/** Modification of the middle point of the gradient a shape. */
	GRAD_MID_POINT {
			@Override
			public String getMessage() {
				return LangTool.INSTANCE.getBundle().getString("Actions.20"); 
			}

			@Override
			public boolean isValueValid(final Object obj) {
				return obj instanceof Double;
			}

			@Override
			public List<Double> getPropertyValues(final IGroup group) {
				return group==null ? Collections.<Double>emptyList() : group.getGradMidPtList();
			}

			@Override
			public void setPropertyValue(final IGroup group, final Object value) {
				if(group!=null && isValueValid(value))
					group.setGradMidPt((Double)value);
			}

			@SuppressWarnings("unchecked")
			@Override
			public void setPropertyValueList(final IGroup group, final List<?> values) {
				if(group!=null)
					group.setGradMidPtList((List<Double>)values);
			}
	},
	/** Modification of the round corner value of a shape. */
	ROUND_CORNER_VALUE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.21"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getLineArcList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setLineArc((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setLineArcList((List<Double>)values);
		}
	},
	
	/** Modification of the colour of the filling of a shape. */
	COLOUR_FILLING {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.21"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof IColor;
		}

		@Override
		public List<IColor> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<IColor>emptyList() : group.getFillingColList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setFillingCol((IColor)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setFillingColList((List<IColor>)values);
		}
	},
	/** Modification of the colour of the borders of a shape. */
	COLOUR_LINE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.23"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof IColor;
		}

		@Override
		public List<IColor> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<IColor>emptyList() : group.getLineColourList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setLineColour((IColor)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setLineColourList((List<IColor>)values);
		}
	},
	/** Modification of the colour of the hatchings of a shape. */
	COLOUR_HATCHINGS {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.19"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof IColor;
		}

		@Override
		public List<IColor> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<IColor>emptyList() : group.getHatchingsColList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setHatchingsCol((IColor)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setHatchingsColList((List<IColor>)values);
		}
	},
	/** Defines if a shape must have double borders. */
	DBLE_BORDERS {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.24"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Boolean;
		}

		@Override
		public List<Boolean> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Boolean>emptyList() : group.hasDbleBordList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setHasDbleBord((Boolean)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setHasDbleBordList((List<Boolean>)values);
		}
	},
	/** Modification of the size of the double borders of a shape. */
	DBLE_BORDERS_SIZE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.24"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getDbleBordSepList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setDbleBordSep((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setDbleBordSepList((List<Double>)values);
		}
	},
	/** Modification of the colour of the double borders of a shape. */
	COLOUR_DBLE_BORD {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.24"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof IColor;
		}

		@Override
		public List<IColor> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<IColor>emptyList() : group.getDbleBordColList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setDbleBordCol((IColor)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setDbleBordColList((List<IColor>)values);
		}
	},
	/** Defines if a shape must have a shadow. */
	SHADOW {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.25"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Boolean;
		}

		@Override
		public List<Boolean> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Boolean>emptyList() : group.hasShadowList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setHasShadow((Boolean)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setHasShadowList((List<Boolean>)values);
		}
	},
	/** Modification of the size of the shadow of a shape. */
	SHADOW_SIZE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.25"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getShadowSizeList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setShadowSize((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setShadowSizeList((List<Double>)values);
		}
	},
	/** Modification of the angle of the shadow of a shape. */
	SHADOW_ANGLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.25"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Double;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getShadowAngleList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setShadowAngle((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setShadowAngleList((List<Double>)values);
		}
	},
	/** Modification of colour of the shadow of a shape. */
	SHADOW_COLOUR {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.25"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof IColor;
		}

		@Override
		public List<IColor> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<IColor>emptyList() : group.getShadowColList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setShadowCol((IColor)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setShadowColList((List<IColor>)values);
		}
	},
	/** Modification of the colour of the start gradient of a shape. */
	COLOUR_GRADIENT_START {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.20"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof IColor;
		}

		@Override
		public List<IColor> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<IColor>emptyList() : group.getGradColStartList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setGradColStart((IColor)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setGradColStartList((List<IColor>)values);
		}
	},
	/** Modification of the colour of the end gradient of a shape. */
	COLOUR_GRADIENT_END {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.20"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof IColor;
		}

		@Override
		public List<IColor> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<IColor>emptyList() : group.getGradColEndList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setGradColEnd((IColor)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setGradColEndList((List<IColor>)values);
		}
	},
	/** Modification of the thickness of the borders of a shape. */
	LINE_THICKNESS {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.26"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof Integer || obj instanceof Double || obj instanceof Float;
		}

		@Override
		public List<Double> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<Double>emptyList() : group.getThicknessList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setThickness((Double)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setThicknessList((List<Double>)values);
		}
	},
	/** Modification of the filling style of a shape. */
	FILLING_STYLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.22"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof EFillingStyle;
		}

		@Override
		public List<EFillingStyle> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<EFillingStyle>emptyList() : group.getFillingStyleList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setFillingStyle((EFillingStyle)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setFillingStyleList((List<EFillingStyle>)values);
		}
	},
	/** Modification of the border position of a shape. */
	BORDER_POS {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.27"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof EBorderPos;
		}

		@Override
		public List<EBorderPos> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<EBorderPos>emptyList() : group.getBordersPositionList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setBordersPosition((EBorderPos)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setBordersPositionList((List<EBorderPos>)values);
		}
	},
	/** Modification of the line style of a shape. */
	LINE_STYLE {
		@Override
		public String getMessage() {
			return LangTool.INSTANCE.getBundle().getString("Actions.28"); 
		}

		@Override
		public boolean isValueValid(final Object obj) {
			return obj instanceof ELineStyle;
		}

		@Override
		public List<ELineStyle> getPropertyValues(final IGroup group) {
			return group==null ? Collections.<ELineStyle>emptyList() : group.getLineStyleList();
		}

		@Override
		public void setPropertyValue(final IGroup group, final Object value) {
			if(group!=null && isValueValid(value))
				group.setLineStyle((ELineStyle)value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setPropertyValueList(final IGroup group, final List<?> values) {
			if(group!=null)
				group.setLineStyleList((List<ELineStyle>)values);
		}
	};
	

	/**
	 * @param group The group to test.
	 * @return True if the given group supports the calling property.
	 * *
	 */
	public boolean isPropertySupported(final IGroup group) {
		return group!=null;
	}

	/**
	 * Sets the given value of the property to the given group.
	 * @param group The group to modify.
	 * @param value The new value of the property to set.
	 * *
	 */
	public abstract void setPropertyValue(final IGroup group, final Object value);

	/**
	 * Sets the given values of the property to the given group. The size of the list
	 * must equals the number of shapes of the group. If a shape of the group must not be set,
	 * its corresponding value in the list must be null.
	 * @param group The group to modify.
	 * @param values The set of new values of the property to set.
	 * *
	 */
	public abstract void setPropertyValueList(final IGroup group, final List<?> values);

	/**
	 * @param group The group to explore.
	 * @return The list of property values of the shapes of the given group.
	 * *
	 */
	public abstract List<?> getPropertyValues(final IGroup group);


	/**
	 * @return The title of the properties.
	 * *
	 */
	public abstract String getMessage();


	/**
	 * @param obj The new value to test.
	 * @return True if the given value can be set to the shape property.
	 * *
	 */
	public abstract boolean isValueValid(final Object obj);
}
