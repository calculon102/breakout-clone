package de.pixelgerecht.gameengine.representation;

public interface World {
	/**
	 * Renders its current state to the platform as a single frame.
	 */
	void renderFrame();

	/**
	 * @param z Must be a value greater or equal zero.
	 * @return Gets or creates layer of given z-axis, depending if already existant.
	 */
	Layer layer(int z);

	/**
	 * Pauses/unpauses the world visually and physically.
	 */
	void pause();

}
