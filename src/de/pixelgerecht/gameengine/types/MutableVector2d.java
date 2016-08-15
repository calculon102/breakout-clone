package de.pixelgerecht.gameengine.types;

/**
 * Contains a mutable vector with some methods to manipulate it.
 * Returns always an incarnation of its concrete implemention.
 * @author calculon102
 */
public interface MutableVector2d extends Mutable<Vector2d> {
	static MutableVector2d of(Vector2d value) {
		return new MutableVector2dImpl(value);
	}

	void add(Vector2d summand);

	void subtract(Vector2d subtrahend);

	void zero();
}
