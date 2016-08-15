package de.pixelgerecht.gameengine.types;

public interface Vector2d {

	public static Vector2d zero() {
		return ImmutableVector2d.ZERO;
	}
	public static Vector2d one() {
		return ImmutableVector2d.ONE;
	}
	public static Vector2d two() {
		return ImmutableVector2d.TWO;
	}

	public static Vector2d of(double x, double y) {
		return new ImmutableVector2d(x, y);
	}

	double x();

	double y();

	Vector2d delta(double deltaX, double deltaY);

	boolean isZero();

	Vector2d toZero(Vector2d by);

	/**
	 * @return The negation of both coordniates.
	 */
	Vector2d negate();

	/**
	 * @return Absolute, non-negative value of this vector.
	 */
	Vector2d abs();

	/**
	 * @param summand Sum given value to this one.
	 * @return The sum of this and the given summand.
	 */
	Vector2d add(Vector2d summand);

	/**
	 * Subtracts the given vector from this one..
	 * @param subtrahend Vector to subtract
	 * @return The result.
	 */
	Vector2d subtract(Vector2d subtrahend);

	/**
	 * Multiplies this vector with the govem one.
	 * @param factor Vector to multiply
	 * @return The result.
	 */
	Vector2d multiply(Vector2d factor);

	/**
	 * Divides this vector as dividend by the given divisor
	 * @param divisor Dividy by this dividor.
	 * @return Result.
	 */
	Vector2d divide(Vector2d divisor);

	/**
	 * Computes a rotated vector around the given center.
	 * @param theta Angle to rotate this vector
	 * @param center Center of the rotation
	 * @return Rotated vector.
	 */
	Vector2d rotate(Angle theta, Vector2d center);

}
