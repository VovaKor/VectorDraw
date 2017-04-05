
package vectordraw.models.impl;

import vectordraw.util.MathUtils;
import vectordraw.models.interfaces.shape.IColor;

/**
 * An implementation of a colour.
 */
class VDColorImpl implements IColor {
	private double r;
	private double g;
	private double b;
	private double o;

	VDColorImpl(final double red, final double green, final double blue, final double opacity) {
		super();
		setR(red);
		setG(green);
		setB(blue);
		setO(opacity);
	}

	@Override
	public javafx.scene.paint.Color toJFX() {
		return new javafx.scene.paint.Color(r, g, b, o);
	}

	@Override
	public java.awt.Color toAWT() {
		return new java.awt.Color((float) r, (float) g, (float) b, (float) o);
	}

	@Override
	public double getR() {
		return r;
	}

	@Override
	public double getG() {
		return g;
	}

	@Override
	public double getB() {
		return b;
	}

	@Override
	public double getO() {
		return o;
	}

	private void checkChannel(final double val) {
		if(val < 0.0 || val > 1.0 || !MathUtils.INST.isValidCoord(val)) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setR(final double red) {
		checkChannel(red);
		r = red;
	}

	@Override
	public void setG(final double green) {
		checkChannel(green);
		g = green;
	}

	@Override
	public void setB(final double blue) {
		checkChannel(blue);
		b = blue;
	}

	@Override
	public void setO(final double opacity) {
		checkChannel(opacity);
		o = opacity;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj instanceof IColor) return hashCode() == obj.hashCode();
		return false;
	}

	@Override
	public int hashCode() {
		int hash = (int) Math.round(r * 255.0);
		hash = ((hash << 8) | (int) Math.round(g * 255.0));
		hash = ((hash << 8) | (int) Math.round(b * 255.0));
		hash = ((hash << 8) | (int) Math.round(o * 255.0));
		return hash;
	}

	@Override
	public String toString() {
		return String.format("[%d,%d,%d,%d]", (int) Math.round(r * 255.0), (int) Math.round(g * 255.0),
			(int) Math.round(b * 255.0), (int) Math.round(o * 255.0));
	}
}
