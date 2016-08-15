package de.pixelgerecht.gameengine.representation;

final class FilledRect implements Representation {

	private final double width;
	private final double height;
	private final Color color;

	public FilledRect(double width, double height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void render(Layer layer) {
		layer.fillRect(width, height, color);
	}

	@Override
	public String toString() {
		return "FilledRect [width=" + width + ", height=" + height + ", color=" + color + "]";
	}
}
