package de.pixelgerecht.gameengine.shapes;

import de.pixelgerecht.gameengine.types.Vector2d;

/**
 * Defines the different interesection-behaviours for concrete shapes.
 * @author calculon102
 *
 */
public interface Intersectable {
	boolean intersects(Rect other, Vector2d otherPosition);

	boolean intersects(Circle other, Vector2d otherPosition);
}
