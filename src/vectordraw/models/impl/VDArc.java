
package vectordraw.models.impl;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import vectordraw.util.MathUtils;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.prop.IArcProp;
import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.IArc;
import vectordraw.models.interfaces.shape.IPoint;
import vectordraw.models.interfaces.shape.IShape;

/**
 * An implementation of a rounded arc.
 */
class VDArc extends ASquaredShape implements IArc {
	
	/** The style of the arc. */
	private ObjectProperty<EArcStyle> style;
           
	/** The start angle of the arc. In radian. */
	private double startAngle;
	/** The end angle of the arc. In radian. */
	private double endAngle;
        private double length;
          

    VDArc(final IPoint tl, final double width, final double startAngle, final double length) {
		super(tl, width);
		
		this.startAngle = startAngle;
                style = new SimpleObjectProperty<>(EArcStyle.ARC);
                endAngle = 3.0 * Math.PI / 2.0;
                this.length = length;
	}

	@Override
	public void copy(final IShape sh) {
		super.copy(sh);
		if(sh instanceof IArcProp) {
			IArcProp arc = (IArcProp) sh;
			startAngle = arc.getAngleStart();
			endAngle = arc.getAngleEnd();
                        style.set(arc.getArcStyle());
                        
		}
	}
            
	@Override
	public IPoint getCenter() {
		return getGravityCentre();
	}

	@Override
	public double getRadius() {
		return getWidth() / 2.0;
	}

	

	@Override
	public boolean isShowPtsable() {
		return true;
	}

	@Override
	public double getAngleEnd() {
		
            return endAngle;
             
    	}

	@Override
	public double getAngleStart() {
		return startAngle;
	}

	@Override
	public IPoint getEndPoint() {
		final IPoint grav = getGravityCentre();
		return ShapeFactory.INST.createPoint(grav.getX() + Math.cos(endAngle) * getHeight() / 2.0, grav.getY() - Math.sin(endAngle) * getHeight() / 2.0);
	}

	@Override
	public IPoint getStartPoint() {
		final IPoint grav = getGravityCentre();
		return ShapeFactory.INST.createPoint(grav.getX() + Math.cos(startAngle) * getWidth() / 2.0, grav.getY() - Math.sin(startAngle) * getWidth() / 2.0);
	}

	@Override
	public EArcStyle getArcStyle() {
		return style.get();
	}

	@Override
	public void setAngleEnd(final double angle) {
		if(MathUtils.INST.isValidCoord(angle)) {
			endAngle = angle;
		}
	}

	@Override
	public void setAngleStart(final double angle) {
		if(MathUtils.INST.isValidCoord(angle)) {
			startAngle = angle;
		}
	}

	@Override
	public void setArcStyle(EArcStyle arcStyle) {
		if(arcStyle != null) {
			style.setValue(arcStyle); 
		}
	}

	

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public ObjectProperty<EArcStyle> arcStyleProperty() {
        return style;
    }
}
