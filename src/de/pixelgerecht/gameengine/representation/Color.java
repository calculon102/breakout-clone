package de.pixelgerecht.gameengine.representation;

public interface Color {
	/**
	 * @return value between 0 and 255
	 */
	int red();

	/**
	 * @return value between 0 and 255
	 */
	int green();

	/**
	 * @return value between 0 and 255
	 */
	int blue();

	/**
	 * @return Value between 0.0d and 1.0d;
	 */
	double alpha();

	public static Color rgb(int red, int green, int blue) {
		return new RgbaColor(red, green, blue, 1.0d);
	}

	public static Color rgba(int red, int green, int blue, double alpha) {
		return new RgbaColor(red, green, blue, alpha);
	}
}
