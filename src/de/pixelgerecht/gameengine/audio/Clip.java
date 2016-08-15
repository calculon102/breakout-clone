package de.pixelgerecht.gameengine.audio;

import java.net.URL;

import javafx.scene.media.AudioClip;

/**
 * Short audio-clip with low-latency playing.
 * @author calculon102
 *
 */
public interface Clip {

	static Clip of(URL path) {
		return new JavaFxClip(new AudioClip(path.toString()));
	}

	/** Play the clip. */
	void play();
}
