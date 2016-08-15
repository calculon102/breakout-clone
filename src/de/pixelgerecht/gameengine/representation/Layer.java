package de.pixelgerecht.gameengine.representation;

import java.util.Collection;

import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.subjects.GameObjectPair;

public interface Layer {
	/**
	 * @return Access to objects within this layer.
	 */
	Collection<GameEntity> objects();

	/**
	 * Draws a filled rect at current translation point with given width, height and color.
	 * @param width
	 * @param height
	 * @param color
	 */
	void fillRect(double width, double height, Color color);

	/**
	 * Draws a filled circle at current translation point with given radius and color.
	 * @param radius
	 * @param color
	 */
	void fillCircle(double radius, Color color);

	/**
	 * Draws given text at current position.
	 * @param text textConfig.
	 * @param content Content of text.
	 */
	void text(Text text);

	void addToCollisionMemory(GameObjectPair pair);

	boolean isInCollisionMemory(GameObjectPair pair);

}
