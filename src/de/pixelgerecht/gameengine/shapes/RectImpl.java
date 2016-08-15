package de.pixelgerecht.gameengine.shapes;

import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

final class RectImpl implements Rect {
	private final double width;
	private final double height;
	private final GameEntity parent;

	public RectImpl(GameEntity parent, double width, double height) {
		this.width = width;
		this.height = height;
		this.parent = parent;
	}

	@Override
	public boolean intersects(Intersectable other, Vector2d otherPosition) {
		return other.intersects(this, parent.topLeft());
	}

	@Override
	public Vector2d normalTo(GameEntity object) {
		// Determine the relevant side of the rectangle

//		final Angle theta = parent.angle().normalized().negate();
		final Vector2d center = center();
		final Vector2d otherCenter = object.center();

		double xw = topleft().x() + width;
		double yh = topleft().y() + height;

		// East?
		if (otherCenter.x() > xw) {
			if (otherCenter.y() > yh && Math.abs(otherCenter.x() - xw) <= Math.abs(otherCenter.y() - center.y())) {
				return Vector2d.of(0.0d,  1.0d);
			} else if (otherCenter.y() < topleft().y() && Math.abs(otherCenter.x() - xw) <= Math.abs(otherCenter.y() - center.y())){
				return Vector2d.of(0.0d,  -1.0d);
			}
			return Vector2d.of( 1.0d, 0.0d);
		}

		// West?
		if (otherCenter.x() < topleft().x()) {
			if (otherCenter.y() > yh && Math.abs(otherCenter.x() - topleft().x()) <= Math.abs(otherCenter.y() - center.y())) {
				return Vector2d.of(0.0d,  1.0d);
			} else if (otherCenter.y() <= topleft().y() && Math.abs(otherCenter.x() - topleft().x()) <= Math.abs(otherCenter.y() - center.y())){
				return Vector2d.of(0.0d,  -1.0d);
			}
			return Vector2d.of( -1.0d, 0.0d);
		}

		// North / South
		if (otherCenter.y() <= topleft().y()) {
			return Vector2d.of(0.0d,  -1.0d);
		} else {
			return Vector2d.of(0.0d,  1.0d);
		}
	}

	@Override
	public boolean intersects(Rect other, Vector2d otherPosition) {
		return parent.topLeft().x() < otherPosition.x() + other.width()
			&& otherPosition.x() < parent.topLeft().x() + this.width
			&& parent.topLeft().y() < otherPosition.y() + other.height()
			&& otherPosition.y() < parent.topLeft().y() + this.height;
	}

	@Override
	public boolean intersects(Circle other, Vector2d otherPosition) {
		return DifferentShapeInteractions.intersects(other, other.relativeCenter(otherPosition), this, center());
	}

	@Override
	public double width() {
		return width;
	}

	@Override
	public double height() {
		return height;
	}

	@Override
	public Vector2d relativeCenter(Vector2d topLeft) {
		return topLeft.add(Vector2d.of((width / 2), (height / 2)));
	}

	Vector2d topleft() {
		return parent.topLeft();
	}

	Vector2d center() {
		return relativeCenter(parent.topLeft());
	}
}
