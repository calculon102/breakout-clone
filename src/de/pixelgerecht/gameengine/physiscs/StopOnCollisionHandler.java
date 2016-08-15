package de.pixelgerecht.gameengine.physiscs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.pixelgerecht.gameengine.subjects.GameEntity;

final class StopOnCollisionHandler implements CollisionHandler {

	private final GameEntity parent;
	private Set<String> ignoreList;

	public StopOnCollisionHandler(GameEntity parent, String...ignoreList) {
		this.parent = parent;
		this.ignoreList = new HashSet<>(Arrays.asList(ignoreList));
	}

	@Override
	public void onCollision(GameEntity other) {
		if ( !ignoreList.contains(other.id())) {
			parent.rigidBody().velocity().zero();
		}
	}

}
