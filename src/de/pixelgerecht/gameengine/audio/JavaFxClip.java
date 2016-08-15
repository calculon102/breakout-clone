package de.pixelgerecht.gameengine.audio;

import java.util.Objects;

import javafx.scene.media.AudioClip;

final class JavaFxClip implements Clip {

	private final AudioClip clip;

	public JavaFxClip(AudioClip clip) {
		Objects.requireNonNull(clip);
		this.clip = clip;
	}

	@Override
	public void play() {
		clip.play();
	}
}
