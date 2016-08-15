package de.pixelgerecht.gameengine.types;

final class MutableVector2dImpl extends GenericMutable<Vector2d> implements MutableVector2d {

	public MutableVector2dImpl(Vector2d value) {
		super(value);
	}

	@Override
	public void add(Vector2d summand) {
		set(get().add(summand));
	}

	@Override
	public void subtract(Vector2d subtrahend) {
		set(get().subtract(subtrahend));
	}

	@Override
	public void zero() {
		set(Vector2d.zero());
	}

}
