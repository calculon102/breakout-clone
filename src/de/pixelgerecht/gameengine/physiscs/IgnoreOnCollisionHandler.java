package de.pixelgerecht.gameengine.physiscs;

import de.pixelgerecht.gameengine.subjects.GameEntity;

enum IgnoreOnCollisionHandler implements CollisionHandler {

	INSTANCE;

	@Override
	public void onCollision(GameEntity other) {
		// NOP
	}

}
