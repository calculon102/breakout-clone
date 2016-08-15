package de.pixelgerecht.gameengine.runtime;

import de.pixelgerecht.gameengine.input.UserInput;
import de.pixelgerecht.gameengine.representation.World;

public interface Controller {

	/**
	 * Wukk ve cakk before first frame is rendered. Use to initialize the game.
	 * @param view Current user-view. Here game-objects may be added or removed.
	 */
	void onInit(World view);

	/**
	 * Will be called in game-loop in every frame, just before rendering.
	 *
	 * @param input Current state of user-input.
	 * @param view Current user-view. Here game-objects may be added or removed.
	 */
	void onFrame(UserInput input, World view);
}
