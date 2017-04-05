
package vectordraw.models.interfaces.shape;

import java.awt.geom.Rectangle2D;
import vectordraw.models.ShapeFactory;

/** The different cardinal points. */
public enum EPosition {
	NORTH {
		@Override
		public EPosition getOpposite() {
			return SOUTH;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getCenterX(), bound.getMinY());
		}
	},
	SOUTH {
		@Override
		public EPosition getOpposite() {
			return NORTH;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getCenterX(), bound.getMaxY());
		}
	},
	EAST {
		@Override
		public EPosition getOpposite() {
			return WEST;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getMaxX(), bound.getCenterY());
		}
	},
	WEST {
		@Override
		public EPosition getOpposite() {
			return EAST;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getMinX(), bound.getCenterY());
		}
	},
	NE {
		@Override
		public EPosition getOpposite() {
			return SW;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getMaxX(), bound.getMinY());
		}
	},
	NW {
		@Override
		public EPosition getOpposite() {
			return SE;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getMinX(), bound.getMinY());
		}
	},
	SE {
		@Override
		public EPosition getOpposite() {
			return NW;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getMaxX(), bound.getMaxY());
		}
	},
	SW {
		@Override
		public EPosition getOpposite() {
			return NE;
		}

		@Override
		public IPoint getReferencePoint(final Rectangle2D bound) {
			return ShapeFactory.INST.createPoint(bound.getMinX(), bound.getMaxY());
		}
	};

	/**
	 * @return True if the given position is south oriented.
	 * *
	 */
	public boolean isSouth() {
		return this == SOUTH || this == SE || this == SW;
	}

	/**
	 * @return True if the given position is north oriented.
	 * *
	 */
	public boolean isNorth() {
		return this == NORTH || this == NE || this == NW;
	}

	/**
	 * @return True if the given position is east oriented.
	 * *
	 */
	public boolean isEast() {
		return this == EAST || this == NE || this == SE;
	}

	/**
	 * @return True if the given position is west oriented.
	 * *
	 */
	public boolean isWest() {
		return this == WEST || this == SW || this == NW;
	}

	/**
	 * @return The opposite position of the current position.
	 * *
	 */
	public abstract EPosition getOpposite();

	public abstract IPoint getReferencePoint(final Rectangle2D bound);
}
