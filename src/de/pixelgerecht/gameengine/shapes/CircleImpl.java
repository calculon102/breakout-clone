package de.pixelgerecht.gameengine.shapes;

import static java.lang.Math.abs;

import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

final class CircleImpl implements Circle {

	private final double radius;
	private final GameEntity parent;

	public CircleImpl(GameEntity parent, double radius) {
		this.parent = parent;
		this.radius = radius;
	}

	@Override
	public boolean intersects(Intersectable other, Vector2d otherPosition) {
		return other.intersects(this, otherPosition);
	}

	@Override
	public Vector2d normalTo(GameEntity other) {
		// TOLOOK the position is normally the center of the other object.
		// More correct would be the position of the collision or neareast point.
		final Vector2d diff = parent.center().subtract(other.center());
		final double max = Math.max(diff.x(), diff.y());
		return Vector2d.of(diff.x() / max, diff.y() / max);
	}

	@Override
	public boolean intersects(Rect rect, Vector2d rectTopLeft) {
		return DifferentShapeInteractions.intersects(this, center(), rect, rect.relativeCenter(rectTopLeft));
	}

	@Override
	public boolean intersects(Circle circle, Vector2d circleCenter) {
		final double dx = abs(parent.center().x() - circleCenter.x());
		final double dy = abs(parent.center().y() - circleCenter.y());
		final double radii = radius + circle.radius();
		return (dx * dx) + (dy * dy) < radii * radii;
	}

	@Override
	public double radius() {
		return radius;
	}

	Vector2d center() {
		return relativeCenter(parent.topLeft());
	}

	@Override
	public Vector2d relativeCenter(Vector2d topLeft) {
		return parent.topLeft().add(Vector2d.of(radius, radius));
	}

}
