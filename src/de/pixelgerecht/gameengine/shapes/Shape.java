package de.pixelgerecht.gameengine.shapes;

import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

public interface Shape extends Intersectable {

	public static Shape none() {
		return NullShape.INSTANCE;
	}

	public static Circle circle(GameEntity parent, double radius) {
		return new CircleImpl(parent, radius);
	}

	public static Rect rect(GameEntity parent, double width, double height) {
		return new RectImpl(parent, width, height);
	}

	boolean intersects(Intersectable other, Vector2d otherPosition);

	/**
	 * @param other
	 * @return Normal to other gameobject assuming unrotated, normalized space!
	 */
	Vector2d normalTo(GameEntity other);

	Vector2d relativeCenter(Vector2d topLeft);
}
