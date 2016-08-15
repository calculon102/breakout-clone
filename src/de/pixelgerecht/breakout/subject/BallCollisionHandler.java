package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.config.BreakoutConfigs.PADDLE_ZONE_REFLECTION_X;

import java.util.logging.Level;

import de.pixelgerecht.breakout.Main;
import de.pixelgerecht.breakout.audio.Sounds;
import de.pixelgerecht.breakout.config.BreakoutConfigs;
import de.pixelgerecht.gameengine.physiscs.CollisionHandler;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.MutableVector2d;
import de.pixelgerecht.gameengine.types.Vector2d;

final class BallCollisionHandler implements CollisionHandler {

	private final GameEntity parent;
	private long collisionCount = 0;

	public BallCollisionHandler(GameEntity parent) {
		this.parent = parent;
	}

	@Override
	public void onCollision(GameEntity other) {
		if (handleBrickCollision(other)) {
			return;
		}

		Sounds.BALL_COLLISION.play();

		handlePaddleCollision(other);
	}

	/**
	 * Handles collision if other entity is a brick.
	 * @param other Possible brick.
	 * @return <code>true</code> if other is actually a brick and collision was handled.
	 */
	private boolean handleBrickCollision(GameEntity other) {
		if ( !other.id().startsWith(BreakoutConfigs.BRICK_ID_PREFIX)) {
			return false;
		}

		Sounds.BRICK_HIT.play();
		collisionCount += 1;
		// TODO Extract constants
		if (collisionCount % 12 == 0) {
			double curr_y = parent.rigidBody().velocity().get().y();
			double new_y = curr_y < 0.0d ? -0.8d : 0.8d;
			parent.rigidBody().velocity().add(Vector2d.of(0.0d, new_y));
			Main.LOG.info("Hit the twelth brick. Increasing y-velocity!");
		}
		return true;
	}


	/**
	 * Handles collision if other entity is a paddle.
	 * @param other Possible paddle.
	 * @return <code>true</code> if other is actually a paddle and collision was handled.
	 */
	private boolean handlePaddleCollision(GameEntity other) {
		if ( !other.id().equals(BreakoutConfigs.PADDLE_ID)) {
			return false;
		}

		final Vector2d delta = parent.center().subtract(other.topLeft());
		if (delta.y() >= 0) { // Must be above.
			return true;
		}
		final double deltaX = delta.x();

		int zone = (int) (deltaX / BreakoutConfigs.PADDLE_ZONE_WIDTH);
		if (zone < 0) {
			zone = 0;
		} else if (zone >= PADDLE_ZONE_REFLECTION_X.length) {
			zone = PADDLE_ZONE_REFLECTION_X.length - 1;
		}

		Main.LOG.log(Level.INFO, "Ball hit paddle in zone {0}.", zone);

		final MutableVector2d ballVelo = parent.rigidBody().velocity();
		double ballY = ballVelo.get().y();
		double ballX = PADDLE_ZONE_REFLECTION_X[zone] * Math.abs(ballY);
		ballVelo.set(Vector2d.of(ballX, ballY));

		return true;
	}

}
