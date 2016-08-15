package de.pixelgerecht.gameengine.runtime;

public interface EngineMetrics {
	/** @return Default-width of canvas. */
	double width();

	/** @return Default-height of canvas. */
	double height();

	/** @return Default-ratio of canvas which marks a scale-value of 1.0d */
	double ratio();

	/** @return Max frames per second. */
	double framerate();
}
