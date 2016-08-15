package de.pixelgerecht.gameengine.subjects;

import java.util.Set;

public interface GameObjectPair {
	static GameObjectPair of(GameEntity a, GameEntity b) {
		return new GameObjectPairImpl(a, b);
	}

	/**
	 * @return Both unnique objects. Must have two distinct elements.
	 */
	Set<GameEntity> pair();
}
