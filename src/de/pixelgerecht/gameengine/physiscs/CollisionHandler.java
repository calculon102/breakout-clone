package de.pixelgerecht.gameengine.physiscs;

import de.pixelgerecht.gameengine.subjects.GameEntity;

/**
 * To define custom collision-handlers additional to the non-kinematic collision handling.
 * @author calculon102
 */
@FunctionalInterface
public interface CollisionHandler {

	/**
	 * Default handler that zeros its parent velocity on collision.
	 * @param parent Reference to object which has to react on collision.
	 * @param ignoreList List of String-IDs where collisions which are to be ignored.
	 * @return New collision-handler.
	 */
	public static CollisionHandler stop(GameEntity parent, String... ignoreList) {
		return new StopOnCollisionHandler(parent, ignoreList);
	}

	/**
	 * Default-handler, does nothing.
	 * @return Null-instance, does nothing.
	 */
	public static CollisionHandler ignore() {
		return IgnoreOnCollisionHandler.INSTANCE;
	}

	void onCollision(GameEntity other);
}
