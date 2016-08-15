package de.pixelgerecht.gameengine.representation;

@FunctionalInterface
public interface Vector2dChangedListener {
	void onPositionChanged(double oldX, double oldY, double newX, double newY, double deltaX, double deltaY);
}
