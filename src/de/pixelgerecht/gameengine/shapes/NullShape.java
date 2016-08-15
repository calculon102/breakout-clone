package de.pixelgerecht.gameengine.shapes;

import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

final class NullShape implements Shape {

	static Shape INSTANCE = new NullShape();

	@Override
	public boolean intersects(Rect other, Vector2d otherPosition) {
		return false;
	}

	@Override
	public boolean intersects(Circle other, Vector2d otherPosition) {
		return false;
	}

	@Override
	public boolean intersects(Intersectable other, Vector2d otherPosition) {
		return false;
	}

	@Override
	public Vector2d normalTo(GameEntity other) {
		return Vector2d.zero();
	}

	@Override
	public Vector2d relativeCenter(Vector2d topLeft) {
		return topLeft;
	}
}
