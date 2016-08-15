package de.pixelgerecht.gameengine.subjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

final class GameObjectPairImpl implements GameObjectPair {
	private final GameEntity a;
	private final GameEntity b;

	public GameObjectPairImpl(GameEntity a, GameEntity b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public Set<GameEntity> pair() {
		return new HashSet<>(Arrays.asList(a, b));
	}

	@Override
	public int hashCode() {
		return a.id().hashCode() + b.id().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GameObjectPairImpl other = (GameObjectPairImpl) obj;
		if (a == other.a && b == other.b) {
			return true;
		}
		if (a == other.b && b == other.a) {
			return true;
		}
		return false;
	}
}
