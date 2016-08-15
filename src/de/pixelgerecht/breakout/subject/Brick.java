package de.pixelgerecht.breakout.subject;

import de.pixelgerecht.breakout.session.Session;
import de.pixelgerecht.gameengine.representation.Color;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

public interface Brick extends BreakoutSubject {

	static Brick newInstance(String id, Layer forLayer, Vector2d position, Vector2d bounds, Color color, Session session, long points) {
		final GameEntity brickEntity = GameEntity.simple(id, forLayer, position, Representation.filledRect(bounds.x(), bounds.y(), color));
		brickEntity.setRigidBody(Shape.rect(brickEntity, bounds.x(), bounds.y()));
		brickEntity.setOnCollision(new BrickCollisionHandler(brickEntity, points, session));

		return new BrickImpl(brickEntity);
	}

}
