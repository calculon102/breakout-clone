package de.pixelgerecht.breakout.session;

import java.util.Optional;

import de.pixelgerecht.gameengine.types.ValueChangeListener;

final class SessionImpl implements Session {

	private long score = 0;
	private long lives = 5; // TODO Constant
	private long bricksToDestroy = 0;
	private GameState state = GameState.RUNNING;

	private Optional<ValueChangeListener<Long>> scoreChangeListener = Optional.empty();
	private Optional<ValueChangeListener<Long>> livesChangeListener = Optional.empty();

	@Override
	public long score() {
		return score;
	}

	@Override
	public void addScore(long points) {
		scoreChangeListener.ifPresent(l -> l.onValueChange(score, score + points));
		this.score += points;
	}

	@Override
	public long lives() {
		return lives;
	}

	@Override
	public void removeLive() {
		livesChangeListener.ifPresent(l -> l.onValueChange(lives, lives -1));
		lives -= 1;
	}

	@Override
	public void setOnScoreChange(ValueChangeListener<Long> newListener) {
		scoreChangeListener = Optional.of(newListener);
	}

	@Override
	public void setOnLivesChange(ValueChangeListener<Long> newListener) {
		livesChangeListener = Optional.of(newListener);
	}

	@Override
	public long bricksToDestroy() {
		return bricksToDestroy;
	}

	@Override
	public void setBricksToDestroy(long count) {
		bricksToDestroy = count;
	}

	@Override
	public void brickDestroyed() {
		bricksToDestroy -= 1;
	}

	@Override
	public GameState state() {
		return state;
	}

	@Override
	public void setState(GameState newState) {
		this.state = newState;
	}

}
