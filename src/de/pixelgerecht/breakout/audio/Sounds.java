package de.pixelgerecht.breakout.audio;

import de.pixelgerecht.gameengine.audio.Clip;

public enum Sounds implements Clip {
	BRICK_HIT("brick_hit.wav"),
	DEADLY_WALL_HIT("deadly_wall_hit.wav"),
	LOST("lost.wav"),
	BALL_COLLISION("paddle_hit.wav"),
	WON("won.wav");

	private final Clip delegate;

	private Sounds(String resourceName) {
		this.delegate = Clip.of(Sounds.class.getResource(resourceName));
	}

	@Override
	public void play() {
		delegate.play();
	}
}
