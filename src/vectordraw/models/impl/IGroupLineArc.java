
package vectordraw.models.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import vectordraw.models.interfaces.prop.ILineArcProp;
import vectordraw.models.interfaces.shape.IGroup;
import vectordraw.models.interfaces.shape.IShape;

/**
 * This trait encapsulates the code of the group related to the support of line arc shapes.
 */
interface IGroupLineArc extends IGroup {
	/** May return the first free hand shape of the group. */
	default <T extends IShape & ILineArcProp> Optional<T> firstLineArc() {
		return (Optional<T>) lineArcShapes().stream().filter(sh -> sh.isTypeOf(ILineArcProp.class)).findFirst();
	}

	default <T extends IShape & ILineArcProp> List<T> lineArcShapes() {
		return getShapes().stream().filter(sh -> sh instanceof ILineArcProp).map(sh -> (T) sh).collect(Collectors.toList());
	}

	@Override
	default double getLineArc() {
		return firstLineArc().map(sh -> sh.getLineArc()).orElse(Double.NaN);
	}

	@Override
	default void setLineArc(final double lineArc) {
		lineArcShapes().forEach(sh -> sh.setLineArc(lineArc));
	}

	@Override
	default boolean isRoundCorner() {
		return firstLineArc().map(sh -> sh.isRoundCorner()).orElse(false);
	}
}
