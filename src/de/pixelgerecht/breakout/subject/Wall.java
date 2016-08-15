package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.config.BreakoutConfigs.WALL_COLOR;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.WALL_DEADLY_COLOR;

import java.util.Objects;

import de.pixelgerecht.breakout.session.Session;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

public interface Wall extends BreakoutSubject {


	static Wall reflective(String id, Layer forLayer, Vector2d position, Vector2d bounds) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(forLayer);
		Objects.requireNonNull(position);
		Objects.requireNonNull(bounds);

		GameEntity wall = GameEntity.simple(id, forLayer, position, Representation.filledRect(bounds.x(), bounds.y(), WALL_COLOR));
		wall.setRigidBody(Shape.rect(wall, bounds.x(), bounds.y()));

		return new WallImpl(wall);
	}

	static Wall deadly(String id, Layer forLayer, Vector2d position, Vector2d bounds, Paddle paddle, Session session) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(forLayer);
		Objects.requireNonNull(position);
		Objects.requireNonNull(bounds);
		Objects.requireNonNull(paddle);
		Objects.requireNonNull(session);

		GameEntity wall = GameEntity.simple(id, forLayer, position, Representation.filledRect(bounds.x(), bounds.y(), WALL_DEADLY_COLOR));
		wall.setRigidBody(Shape.rect(wall, bounds.x(), bounds.y()));
		wall.setOnCollision(new DeadlyWallCollisionHandler(paddle, session));

		return new WallImpl(wall);
	}
}
