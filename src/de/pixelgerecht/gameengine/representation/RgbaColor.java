package de.pixelgerecht.gameengine.representation;

final class RgbaColor implements Color {
	private final int red;
	private final int green;
	private final int blue;
	private final double alpha;

	public RgbaColor(int red, int green, int blue, double alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	@Override
	public int red() {
		return red;
	}

	@Override
	public int green() {
		return green;
	}

	@Override
	public int blue() {
		return blue;
	}

	@Override
	public double alpha() {
		return alpha;
	}

	@Override
	public String toString() {
		return "RgbaColor [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + "]";
	}
}
