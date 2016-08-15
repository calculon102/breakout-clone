package de.pixelgerecht.gameengine.shapes;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

public class CircleImplTest {

	private CircleImpl circle1;
	private CircleImpl circle2;
	private CircleImpl circle3;
	private RectImpl rect1;
	private RectImpl rect2;

	@Before
	public void before() {
		final GameEntity circleObject1 = GameEntity.simple("cirlce1", mock(Layer.class), Vector2d.zero(), mock(Representation.class));
		circle1 = new CircleImpl(circleObject1, 1.0d);
		circleObject1.setRigidBody(circle1);
		final GameEntity circleObject2 = GameEntity.simple("cirlce2", mock(Layer.class), Vector2d.of(1.41d, 1.41d), mock(Representation.class));
		circle2 = new CircleImpl(circleObject2, 1.0d);
		circleObject2.setRigidBody(circle2);
		final GameEntity circleObject3 = GameEntity.simple("cirlce3", mock(Layer.class), Vector2d.of(3, 3), mock(Representation.class));
		circle3 = new CircleImpl(circleObject3, 1.0d);
		circleObject3.setRigidBody(circle3);

		final GameEntity rectObject1 = GameEntity.simple("rect1", mock(Layer.class), Vector2d.zero(), mock(Representation.class));
		rect1 = new RectImpl(rectObject1, 10, 20);
		final GameEntity rectObject2 = GameEntity.simple("rect2", mock(Layer.class), Vector2d.of(2.01d, 0.0d), mock(Representation.class));
		rect2 = new RectImpl(rectObject2, 5, 5);
	}

	@Test
	public void testIntersectsRectPosition() {
		assertTrue(circle1.intersects(rect1, rect1.topleft()));
		assertTrue(rect1.intersects(circle1, circle1.center()));

		assertFalse(circle1.intersects(rect2, rect2.topleft()));
		assertFalse(rect2.intersects(circle1, circle1.center()));
	}

	@Test
	public void testIntersectsCirclePosition() {
		assertTrue(circle1.intersects(circle1, circle1.center()));
		assertTrue(circle1.intersects(circle2, circle2.center()));
		assertTrue(circle2.intersects(circle1, circle1.center()));
		assertFalse(circle1.intersects(circle3, circle3.center()));
		assertFalse(circle3.intersects(circle1, circle1.center()));
	}

	@Test
	public void testRadius() {
		assertThat(circle1.radius(), is(1.0d));
	}

}
