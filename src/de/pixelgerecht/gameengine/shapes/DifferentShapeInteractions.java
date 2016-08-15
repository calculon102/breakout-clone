package de.pixelgerecht.gameengine.shapes;

import static java.lang.Math.abs;

import de.pixelgerecht.gameengine.types.Vector2d;

/**
 * Common algorithms, when different Shape-implementations interact with each other.
 * @author calculon102
 */
final class DifferentShapeInteractions {

	/**
	 * TODO Refactor for non-perpendicular rects...
	 * https://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
	 * @param circle
	 * @param cirlceCenter
	 * @param rect
	 * @param rectCenter
	 * @return
	 */
	public static boolean intersects(Circle circle, Vector2d cirlceCenter, Rect rect, Vector2d rectCenter) {
		final double dx = abs(cirlceCenter.x() - rectCenter.x());
		final double dy = abs(cirlceCenter.y() - rectCenter.y());

		if (dx > ((rect.width() / 2) + circle.radius())) {
			return false;
		}
		if (dy > ((rect.height() / 2) + circle.radius())) {
			return false;
		}

		if (dx <= (rect.width() / 2)) {
			return true;
		}
		if (dy <= (rect.height() / 2)) {
			return true;
		}

		final double dist = Math.pow((dx - (rect.width() / 2)), 2) + Math.pow((dy - (rect.height() / 2)), 2);
		return dist <= Math.pow(circle.radius(), 2);
	}

}
