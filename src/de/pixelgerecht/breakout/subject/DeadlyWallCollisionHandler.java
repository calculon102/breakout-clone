package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.Main.LOG;

import java.util.Objects;
import java.util.logging.Level;

import de.pixelgerecht.breakout.audio.Sounds;
import de.pixelgerecht.breakout.config.BreakoutConfigs;
import de.pixelgerecht.breakout.session.Session;
import de.pixelgerecht.gameengine.physiscs.CollisionHandler;
import de.pixelgerecht.gameengine.subjects.GameEntity;

final class DeadlyWallCollisionHandler implements CollisionHandler {

	private final Paddle paddle;
	private final Session session;

	public DeadlyWallCollisionHandler(Paddle paddle, Session session) {
		Objects.requireNonNull(paddle);
		Objects.requireNonNull(session);

		this.paddle = paddle;
		this.session = session;
	}

	@Override
	public void onCollision(GameEntity other) {
		if ( !other.id().equals(BreakoutConfigs.BALL_ID)) {
			return;
		}

		session.removeLive();
		LOG.log(Level.INFO, "Ball hit deadly wall! Lives left: " + session.lives());

		Sounds.DEADLY_WALL_HIT.play();

		paddle.ownBall(other);
	}

}
