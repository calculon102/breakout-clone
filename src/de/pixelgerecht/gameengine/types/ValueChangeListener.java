package de.pixelgerecht.gameengine.types;

/**
 * @author calculon102
 *
 * @param <T>
 */
@FunctionalInterface
public interface ValueChangeListener<T> {
	void onValueChange(T prev, T now);
}
