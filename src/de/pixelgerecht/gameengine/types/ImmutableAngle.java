package de.pixelgerecht.gameengine.types;

final class ImmutableAngle implements Angle {

	public static final Angle ZERO = new ImmutableAngle(0.0d);

	private final double degrees;

	public ImmutableAngle(double degrees) {
		this.degrees = degrees;
	}

	@Override
	public double degrees() {
		return degrees;
	}

	@Override
	public double radiant() {
		return Math.toRadians(degrees);
	}

	@Override
	public Angle normalized() {
		if (degrees > -180.0d || degrees < 180.0d) {
			return this;
		}

		double result = degrees;

		if (result <= -180.0d) {
			do {
				result += 180.0d;
			} while (result <= -180.0d);
		} else if (result >= 180.0d) {
			do {
				result -= 180.0d;
			} while (result >= 180.0d);
		}

		return Angle.of(result);
	}

	@Override
	public Angle negate() {
		return Angle.of(-degrees);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(degrees);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ImmutableAngle other = (ImmutableAngle) obj;
		if (Double.doubleToLongBits(degrees) != Double.doubleToLongBits(other.degrees)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Angle [degrees=" + degrees + "]";
	}
}
