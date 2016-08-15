package de.pixelgerecht.gameengine.physiscs;

import static de.pixelgerecht.gameengine.runtime.Logging.LOG;

import java.util.Optional;
import java.util.logging.Level;

import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Mutable;
import de.pixelgerecht.gameengine.types.MutableVector2d;
import de.pixelgerecht.gameengine.types.Vector2d;

/**
 * RigidBody which collision-borders are same as its represented game-object.
 * @author calculon102
 */
final class SimpleRigidBody implements RigidBody {

	private static final Vector2d COLLISION_BACKTRACK_DIVISIOR = Vector2d.of(20.0d, 20.0d);
	private static final Vector2d DEFAULT_DRAG = Vector2d.of(0.05d, 0.05d);

	private final Layer world;
	private final GameEntity parent;
	private final Shape body;

	private final MutableVector2d drag = MutableVector2d.of(DEFAULT_DRAG);
	private final MutableVector2d velocity = MutableVector2d.of(Vector2d.zero());
	private final Mutable<Boolean> kinematic = Mutable.of(true);

	public SimpleRigidBody(Layer world, GameEntity parent, Shape body) {
		this.world = world;
		this.parent = parent;
		this.body = body;
	}

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
		if (velocity.get().isZero()) {
			return;
		}

		parent.moveBy(velocity.get());
		final Vector2d oldVelocity = velocity.get();
		velocity.set(velocity.get().toZero(drag.get()));

		final Optional<GameEntity> collision = world.objects().stream()
				.filter(o -> o != parent)
				.filter(o -> o.rigidBody().shape().intersects(body, parent.topLeft()))
				.findFirst();

		collision.ifPresent(o -> {
			if (LOG.isLoggable(Level.FINE)) {
				LOG.log(Level.FINE, "Collision detected: {0} vs. {1}", new Object[] {parent.id(), o.id()});
			}

			final Vector2d backtrackVelocity = oldVelocity.negate().divide(COLLISION_BACKTRACK_DIVISIOR);
			do {
				parent.moveBy(backtrackVelocity);
			} while (o.rigidBody().shape().intersects(body, parent.topLeft()));

			if ( !parent.rigidBody().isKinematic().get()) {
				resolveCollision(parent, o);
			}
			if ( !o.rigidBody().isKinematic().get()) {
				resolveCollision(o, parent);
			}

			parent.onCollision(o);
			o.onCollision(parent);
		});
	}

	@Override
	public Mutable<Boolean> isKinematic() {
		return kinematic;
	}

	@Override
	public Shape shape() {
		return body;
	}


	/**
	 * Resolves the physical forces of a collision for this object. Currently only simple reflection.
	 * Withour mass and forces...
	 * @param other
	 */
	private static void resolveCollision(GameEntity toResolve, GameEntity other) {
		if (LOG.isLoggable(Level.FINE)) {
			LOG.log(Level.FINE, "Resolve no-kinematic-collision of {0} with {1}", new Object[] {toResolve.id(), other.id()});
		}

 		final Vector2d normal = other.rigidBody().shape().normalTo(toResolve);
		final Vector2d currVelocity = toResolve.rigidBody().velocity().get();

		// New vector is −(2(n · v) n − v).
		final Vector2d reflectionVeloctiy = normal.multiply(currVelocity).multiply(Vector2d.two()).multiply(normal).subtract(currVelocity).negate();
		toResolve.rigidBody().velocity().set(reflectionVeloctiy);
	}

}
