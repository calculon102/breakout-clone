package de.pixelgerecht.gameengine.subjects;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import de.pixelgerecht.gameengine.objects.hierarchy.Hierarchy;
import de.pixelgerecht.gameengine.physiscs.CollisionHandler;
import de.pixelgerecht.gameengine.physiscs.RigidBody;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.types.Angle;
import de.pixelgerecht.gameengine.types.Vector2d;

/**
 * TODO Implement parent- and children-mechanisms.
 * @author calculon102
 */
final class SimpleGameObject implements GameEntity {

	private final String id;
	private final Layer layer;
	private final Optional<Representation> representation;
	private final Hierarchy<GameEntity> hierarchy = Hierarchy.empty();

	private volatile Vector2d topLeft;
	private volatile Angle angle = Angle.zero();
	private volatile RigidBody rigidBody = RigidBody.none();
	private volatile CollisionHandler collisionHandler;


	public SimpleGameObject(String id, Layer layer, Vector2d position, Representation representation) {
		requireNonNull(id);
		requireNonNull(layer);
		requireNonNull(position);

		this.id = id;
		this.layer = layer;
		this.topLeft = position;
		this.representation = Optional.ofNullable(representation);
		this.collisionHandler = CollisionHandler.ignore();
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public Vector2d topLeft() {
		return topLeft;
	}

	@Override
	public Vector2d center() {
		return rigidBody.shape().relativeCenter(topLeft);
	}

	@Override
	public Angle angle() {
		return angle;
	}

	@Override
	public Hierarchy<GameEntity> hierarchy() {
		return hierarchy;
	}

	@Override
	public Optional<Representation> representation() {
		return representation;
	}

	@Override
	public RigidBody rigidBody() {
		return rigidBody;
	}

	@Override
	public void setRigidBody(Shape rigidShape) {
		requireNonNull(rigidShape);
		rigidBody = RigidBody.of(layer, this, rigidShape);
	}

	@Override
	public void onCollision(GameEntity other) {
		requireNonNull(other);
		collisionHandler.onCollision(other);
	}

	@Override
	public void setOnCollision(CollisionHandler handler) {
		requireNonNull(handler);
		collisionHandler = handler;
	}

	@Override
	public void moveTo(Vector2d xyCoords) {
		requireNonNull(xyCoords);

		final Vector2d xyDelta = topLeft.subtract(xyCoords);

		this.topLeft = xyCoords;
		hierarchy.children().forEach(c -> c.moveBy(xyDelta));
	}

	@Override
	public void moveBy(Vector2d xyDelta) {
		requireNonNull(xyDelta);

		this.topLeft = topLeft.add(xyDelta);
		hierarchy.children().forEach(c -> c.moveBy(xyDelta));
	}

	@Override
	public void remove() {
		layer.objects().remove(this);
	}

	@Override
	public String toString() {
		return "SimpleGameObject [id=" + id + ", layer=" + layer + ", representation=" + representation + ", position=" + topLeft + ", rigidBody=" + rigidBody + ", collisionHandler=" + collisionHandler + "]";
	}
}
