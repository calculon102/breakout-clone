package de.pixelgerecht.gameengine.types;

public interface Angle {
	static Angle of(double degrees) {
		if (degrees == 0.0d) {
			return ImmutableAngle.ZERO;
		}

		return new ImmutableAngle(degrees);
	}

	static Angle zero() {
		return ImmutableAngle.ZERO;
	}

	double degrees();

	double radiant();

	/**
	 * @return Reduced value with identical meaning in a range between -180* and +180*
	 */
	Angle normalized();

	/**
	 * @return Negation of this angle.
	 */
	Angle negate();
}
