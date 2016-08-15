package de.pixelgerecht.gameengine.physiscs;

import static java.util.Objects.requireNonNull;

import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Mutable;
import de.pixelgerecht.gameengine.types.MutableVector2d;

/**
 * A physical body, which velocity affects the position of its object and all of its children.
 * It may also collide with other RigidBodies.
 *
 * @author calculon102
 */
public interface RigidBody {

	static RigidBody none() {
		return NullRigidBody.INSTANCE;
	}

	static RigidBody of(Layer layer, GameEntity parent, Shape shape) {
		requireNonNull(layer);
		requireNonNull(parent);
		requireNonNull(shape);

		if (shape.equals(Shape.none())) {
			return NullRigidBody.INSTANCE;
		}

		return new SimpleRigidBody(layer, parent, shape);
	}

	/** @return Modifiable drag of this body. */
	MutableVector2d drag();

	/** @return Modifiable velocity of this body. */
	MutableVector2d velocity();

	/**
	 * Called, when actual physics on this rigidbody should be resolved for on frame.
	 * Includes movement by current velocity, drag and collisions.
	 */
	void onFrame();

	/**
	 * @return Shape of this rigid-body.
	 */
	Shape shape();

	/**
	 * @return <code>true</code> means, velocity is only changed "manually" and not by phyical forces
	 * such as gravity or collisions. Non-moving objects remain static and colliding objects "bounce of".
	 *
	 */
	Mutable<Boolean> isKinematic();
}
