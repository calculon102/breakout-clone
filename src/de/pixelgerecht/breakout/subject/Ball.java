package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_COLOR;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_ID;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_POSITION;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_RADIUS;

import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.subjects.GameEntity;

public interface Ball extends BreakoutSubject {
	static Ball newInstance(Layer forLayer, Paddle toStartOn) {
		final GameEntity ball = GameEntity.simple(BALL_ID, forLayer, BALL_POSITION, Representation.fillecCircle(BALL_RADIUS, BALL_COLOR));
		ball.setRigidBody(Shape.circle(ball, BALL_RADIUS));
		ball.rigidBody().drag().zero();
		ball.rigidBody().isKinematic().set(false);
		ball.setOnCollision(new BallCollisionHandler(ball));
		toStartOn.gameEntity().hierarchy().addChild(ball);
		return new BallImpl(ball);
	}
}
