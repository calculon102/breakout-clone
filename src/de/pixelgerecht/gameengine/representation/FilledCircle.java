package de.pixelgerecht.gameengine.representation;

final class FilledCircle implements Representation {

	private final double radius;
	private final Color color;

	public FilledCircle(double color, Color radius) {
		this.radius = color;
		this.color = radius;
	}

	@Override
	public void render(Layer layer) {
		layer.fillCircle(radius, color);
	}

}
