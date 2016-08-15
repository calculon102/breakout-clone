package de.pixelgerecht.gameengine.subjects;

import java.util.Optional;

import de.pixelgerecht.gameengine.objects.hierarchy.HierarchyAble;
import de.pixelgerecht.gameengine.physiscs.CollisionHandler;
import de.pixelgerecht.gameengine.physiscs.RigidBody;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.types.Angle;
import de.pixelgerecht.gameengine.types.Movable;
import de.pixelgerecht.gameengine.types.Vector2d;

public interface GameEntity extends Movable, HierarchyAble<GameEntity> {
	/**
	 * TODO Refactor to Builder
	 * @param topLeft Position of this object.
	 * @param representation A representation or <code>null</code>.
	 * @return Creates a new gameobject without parent or children an optional representation, but with a simple rigid body.
	 */
	public static GameEntity simple(String id, Layer layer, Vector2d topLeft, Representation representation) {
		return new SimpleGameObject(id, layer, topLeft, representation);
	}

	String id();

	Vector2d topLeft();

	Vector2d center();

	RigidBody rigidBody();

	void setRigidBody(Shape replacement);

	Optional<Representation> representation();

	/**
	 * Tells this entity, that a collision has occurered with the given object. Does not verify the collision itself, but assumes it is true!
	 * @param other Object this entity has collided with.
	 */
	void onCollision(GameEntity other);

	/**
	 * Custom collision-handler of detected collisions by its rigidBody. Additional to the physical resolving of collisions.
	 * @param handler
	 */
	void setOnCollision(CollisionHandler handler);

	/**
	 * @return dregrees of rotation of this object.
	 */
	Angle angle();

	/**
	 * Removes this object from the world (effecevly its layer).
	 */
	void remove();
}
