package de.pixelgerecht.gameengine.physiscs;

import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.types.Mutable;
import de.pixelgerecht.gameengine.types.MutableVector2d;
import de.pixelgerecht.gameengine.types.Vector2d;

final class NullRigidBody implements RigidBody {

	static RigidBody INSTANCE = new NullRigidBody();

	private final MutableVector2d drag = MutableVector2d.of(Vector2d.zero());
	private final MutableVector2d velocity = MutableVector2d.of(Vector2d.zero());
	private final Mutable<Boolean> kinematic = Mutable.of(false);

	@Override
	public MutableVector2d drag() {
		return drag;
	}

	@Override
	public MutableVector2d velocity() {
		return velocity;
	}

	@Override
	public void onFrame() {
		// NOP
	}

	@Override
	public Shape shape() {
		return Shape.none();
	}

	@Override
	public Mutable<Boolean> isKinematic() {
		return kinematic;
	}
}
