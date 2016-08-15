package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.Main.LOG;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_RADIUS;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_VERTICAL_VELOCITY;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_FRAME_MOVEMENT;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_HEIGHT;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_WIDTH;

import java.util.logging.Level;

import de.pixelgerecht.gameengine.input.InputEvent;
import de.pixelgerecht.gameengine.input.UserInput;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.MutableVector2d;
import de.pixelgerecht.gameengine.types.Vector2d;

final class PaddleImpl extends AbstractBreakoutSubject implements Paddle {

	public PaddleImpl(GameEntity gameEntity) {
		super(gameEntity);
	}

	/**
	 * TODO Divde and test.
	 */
	@Override
	public void handleInput(UserInput input, Ball ball) {

		if (input.left()) {
			gameEntity().rigidBody().velocity().add(Vector2d.of(-PADDLE_FRAME_MOVEMENT, 0.0d));
		} else if (input.right()) {
			gameEntity().rigidBody().velocity().add(Vector2d.of(+PADDLE_FRAME_MOVEMENT, 0.0d));
		}

		if (input.fire1()) {
			final MutableVector2d ballVelocity = ball.gameEntity().rigidBody().velocity();
			if (ballVelocity.get().isZero()) { // TODO Check if child
				// Shoot
				gameEntity().hierarchy().removeChild(ball.gameEntity());
				ballVelocity.add(Vector2d.of(gameEntity().rigidBody().velocity().get().x(), -BALL_VERTICAL_VELOCITY));
				input.consume(InputEvent.FIRE1); // Additional key-press needed for next action.
				LOG.log(Level.INFO, "Player shoots the ball.");
			} else {
				// Catch possible?
				final Vector2d delta = ball.gameEntity().center().subtract(gameEntity().center());
				final boolean ballIsInReach = Math.abs(delta.y()) < (PADDLE_HEIGHT / 2) + BALL_RADIUS + 1.0d
										   && Math.abs(delta.x()) < (PADDLE_WIDTH / 2) + BALL_RADIUS;
				if (ballIsInReach) {
					ballVelocity.zero();
					gameEntity().hierarchy().addChild(ball.gameEntity());
					input.consume(InputEvent.FIRE1); // Additional key-press needed for next action.
					LOG.log(Level.INFO, "Player catches the ball.");
				}
			}
		}
	}

	@Override
	public void ownBall(GameEntity ball) {
		ball.rigidBody().velocity().zero();
		final Vector2d newPosition = gameEntity().center().add(Vector2d.of(-BALL_RADIUS, -((PADDLE_HEIGHT / 2) + BALL_RADIUS*2)-1.0d));
		ball.moveTo(newPosition);
		gameEntity().hierarchy().addChild(ball);
	}
}
