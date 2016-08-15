package de.pixelgerecht.gameengine.types;

public interface Movable {
	void moveTo(Vector2d xyCoords);

	void moveBy(Vector2d xyDelta);
}
