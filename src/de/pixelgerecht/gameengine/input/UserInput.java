package de.pixelgerecht.gameengine.input;

public interface UserInput {

	/**
	 * Uppdates state of regocnized input. May be called within each frame.
	 */
	void poll();

	boolean escape();

	boolean left();

	boolean right();

	boolean up();

	boolean down();

	boolean fire1();

	boolean fire2();

	boolean pause();

	boolean restart();

	boolean pressed(InputEvent event);

	boolean consume(InputEvent event);

}
