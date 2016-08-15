package de.pixelgerecht.gameengine.types;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

class ImmutableVector2d implements Vector2d {

	static final Vector2d ZERO = new ImmutableVector2d(0.0d, 0.0d);

	static final Vector2d ONE = new ImmutableVector2d(1.0d, 1.0d);

	static final Vector2d TWO = new ImmutableVector2d(2.0d, 2.0d);

	private final double x;
	private final double y;

	public ImmutableVector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double x() {
		return x;
	}

	@Override
	public double y() {
		return y;
	}

	@Override
	public Vector2d delta(double deltaX, double deltaY) {
		if (deltaX == 0.0d && deltaY == 0.0d) {
			return this;
		}

		double newX = x + deltaX;
		double newY = y + deltaY;

		final ImmutableVector2d newInstance = new ImmutableVector2d(newX, newY);

		return newInstance;
	}

	@Override
	public boolean isZero() {
		return ZERO.equals(this);
	}

	@Override
	public Vector2d negate() {
		return Vector2d.of(-x, -y);
	}

	@Override
	public Vector2d abs() {
		return Vector2d.of(Math.abs(x), Math.abs(y));
	}

	@Override
	public Vector2d add(Vector2d summand) {
		return Vector2d.of(x + summand.x(), y + summand.y());
	}

	@Override
	public Vector2d subtract(Vector2d subtrahend) {
		return Vector2d.of(x - subtrahend.x(), y - subtrahend.y());
	}

	@Override
	public Vector2d multiply(Vector2d factor) {
		return Vector2d.of(x * factor.x(), y * factor.y());
	}

	@Override
	public Vector2d divide(Vector2d divisor) {
		return Vector2d.of(x / divisor.x(), y / divisor.y());
	}

	@Override
	public Vector2d rotate(Angle angle, Vector2d center) {
		if (angle.equals(Angle.zero())) {
			return this;
		}

		final double tempX = x - center.x();
		final double tempY = y - center.y();

		final double theta = angle.degrees();
		final double rotatedX = tempX * cos(theta) - tempY * sin(theta);
		final double rotatedY = tempX * sin(theta) + tempY * cos(theta);

		return Vector2d.of(rotatedX + center.x(), rotatedY + center.y());
	}

	@Override
	public Vector2d toZero(Vector2d by) {
		if (by.isZero()) {
			return this;
		}

		double newX = x;
		double newY = y;

		if (x > 0.0d) {
			newX -= by.x();
			if (newX < 0.0d) {
				newX = 0.0d;
			}
		} else {
			newX += by.x();
			if (newX > 0.0d) {
				newX = 0.0d;
			}
		}

		if (y > 0.0d) {
			newY -= by.y();
			if (newY < 0.0d) {
				newY = 0.0d;
			}
		} else {
			newY += by.y();
			if (newY > 0.0d) {
				newY = 0.0d;
			}
		}

		return new ImmutableVector2d(newX, newY);
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}
}
