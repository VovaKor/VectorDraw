
package vectordraw.models.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import vectordraw.models.interfaces.prop.IArcProp;
import vectordraw.models.interfaces.shape.EArcStyle;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;

/**
 * This trait encapsulates the code of the group related to the support of IArcProp shapes.
 */
interface IGroupArc extends IGroup {
	/** May return the first IArcProp shape of the group. */
	default <T extends IShape & IArcProp> Optional<T> firstIArcProp() {
		return (Optional<T>) arcShapes().stream().filter(sh -> sh.isTypeOf(IArcProp.class)).findFirst();
	}

	default <T extends IShape & IArcProp> List<T> arcShapes() {
		return getShapes().stream().filter(sh -> sh instanceof IArcProp).map(sh -> (T) sh).collect(Collectors.toList());
	}

	@Override
	default EArcStyle getArcStyle() {
		return firstIArcProp().map(arc -> arc.getArcStyle()).orElse(EArcStyle.ARC);
	}

	@Override
	default void setArcStyle(final EArcStyle typeArc) {
		arcShapes().forEach(sh -> sh.setArcStyle(typeArc));
	}

	@Override
	default double getAngleStart() {
		return firstIArcProp().map(arc -> arc.getAngleStart()).orElse(Double.NaN);
	}

	@Override
	default void setAngleStart(final double angleStart) {
		arcShapes().forEach(sh -> sh.setAngleStart(angleStart));
	}

	@Override
	default double getAngleEnd() {
		return firstIArcProp().map(arc -> arc.getAngleEnd()).orElse(Double.NaN);
	}


	@Override
	default void setAngleEnd(final double angleEnd) {
		arcShapes().forEach(sh -> sh.setAngleEnd(angleEnd));
	}
}
