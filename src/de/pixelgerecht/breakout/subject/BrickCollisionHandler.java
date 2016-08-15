package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.Main.LOG;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BALL_ID;

import java.util.logging.Level;

import de.pixelgerecht.breakout.session.Session;
import de.pixelgerecht.gameengine.physiscs.CollisionHandler;
import de.pixelgerecht.gameengine.subjects.GameEntity;

final class BrickCollisionHandler implements CollisionHandler {
	/** Brick this handler belongs to. */
	private final GameEntity parent;
	/** Points the playe gets for hitting this brick. */
	private final long points;
	/** Session of the game to add the points to on collision. */
	private final Session session;

	/**
	 * @param brick Brick this handler belongs to.
	 * @param points Points the playe gets for hitting this brick.
	 * @param session Session of the game to add the points to on collision.
	 */
	public BrickCollisionHandler(GameEntity brick, long points, Session session) {
		this.parent = brick;
		this.points = points;
		this.session = session;
	}

	@Override
	public void onCollision(GameEntity other) {
		if ( !other.id().equals(BALL_ID)) {
			return;
		}

		session.addScore(points);
		parent.remove();

		LOG.log(Level.INFO, "Brick hit! New score: {0}", session.score());
	}

}
