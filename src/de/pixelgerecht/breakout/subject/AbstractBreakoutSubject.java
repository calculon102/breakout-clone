package de.pixelgerecht.breakout.subject;

import static java.util.Objects.requireNonNull;

import de.pixelgerecht.gameengine.subjects.GameEntity;

abstract class AbstractBreakoutSubject implements BreakoutSubject {
	/** Representation in the world. */
	private final GameEntity gameEntity;

	public AbstractBreakoutSubject(GameEntity gameEntity) {
		requireNonNull(gameEntity);
		this.gameEntity = gameEntity;
	}

	@Override
	public GameEntity gameEntity() {
		return gameEntity;
	}
}
