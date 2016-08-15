package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_ID;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_COLOR;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_HEIGHT;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_ID;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_POSITION;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_WIDTH;

import de.pixelgerecht.gameengine.input.UserInput;
import de.pixelgerecht.gameengine.physiscs.CollisionHandler;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.shapes.Shape;
import de.pixelgerecht.gameengine.subjects.GameEntity;

public interface Paddle extends BreakoutSubject {

	/**
	 * Factory-method for a completely new paddle-instance for the breakout-game.
	 * @param forLayer World-Layer the new paddle is needed for.
	 * @return New paddle.
	 */
	public static Paddle newInstance(Layer forLayer) {
		final GameEntity paddleSubject = GameEntity.simple(PADDLE_ID, forLayer, PADDLE_POSITION, Representation.filledRect(PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR));
		paddleSubject.setRigidBody(Shape.rect(paddleSubject, PADDLE_WIDTH, PADDLE_HEIGHT));
		paddleSubject.setOnCollision(CollisionHandler.stop(paddleSubject, BALL_ID));

		return new PaddleImpl(paddleSubject);
	}

	/**
	 * Handles the input of a frame.
	 * @param input Current input-map. Will be manipulated!
	 * @param ball Ball to interact with.
	 */
	void handleInput(UserInput input, Ball ball);

	/**
	 * Returns the ball to the middle of the panel with zero velocity.
	 * @param ball Entity of ball to reset.
	 */
	void ownBall(GameEntity ball);
}