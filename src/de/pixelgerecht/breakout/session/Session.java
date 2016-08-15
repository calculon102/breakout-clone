package de.pixelgerecht.breakout.session;

import de.pixelgerecht.gameengine.types.ValueChangeListener;

public interface Session {

	static Session reset() {
		return new SessionImpl();
	}

	long score();

	void addScore(long points);

	void setOnScoreChange(ValueChangeListener<Long> l);

	long lives();

	void removeLive();

	void setOnLivesChange(ValueChangeListener<Long> l);

	long bricksToDestroy();

	void setBricksToDestroy(long count);

	void brickDestroyed();

	GameState state();

	void setState(GameState newState);
}
