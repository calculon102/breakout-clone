package de.pixelgerecht.gameengine.representation;

public interface Representation {

	public static Representation filledRect(double width, double height, Color color) {
		return new FilledRect(width, height, color);
	}

	public static Representation fillecCircle(double radius, Color color) {
		return new FilledCircle(radius, color);
	}

	/**
	 * Renders this representation to the given view. Without translation.
	 * @param layer View this representation will be rendered to.
	 */
	void render(Layer layer);

}
