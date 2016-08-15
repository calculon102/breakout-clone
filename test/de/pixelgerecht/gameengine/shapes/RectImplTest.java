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

public class RectImplTest {
	private CircleImpl circle1;
	private RectImpl rect1;
	private RectImpl rect2;
	private RectImpl rect3;
	private RectImpl rect4;

	@Before
	public void before() {
		final GameEntity circleObject1 = GameEntity.simple("cirlce1", mock(Layer.class), Vector2d.zero(), mock(Representation.class));
		circle1 = new CircleImpl(circleObject1, 1.0d);

		final GameEntity rectObject1 = GameEntity.simple("rect1", mock(Layer.class), Vector2d.zero(), mock(Representation.class));
		rect1 = new RectImpl(rectObject1, 10, 20);
		final GameEntity rectObject2 = GameEntity.simple("rect2", mock(Layer.class), Vector2d.of(2.01d, 0.0d), mock(Representation.class));
		rect2 = new RectImpl(rectObject2, 5, 5);

		final GameEntity rectObject3 = GameEntity.simple("rect1", mock(Layer.class), Vector2d.of(9.99d, 19.99d), mock(Representation.class));
		rect3 = new RectImpl(rectObject3, 5, 5);
		final GameEntity rectObject4 = GameEntity.simple("rect2", mock(Layer.class), Vector2d.of(10.0d, 20.0d), mock(Representation.class));
		rect4 = new RectImpl(rectObject4, 5, 5);
	}

	@Test
	public void testIntersectsRectPosition() {
		assertTrue(rect1.intersects(rect1, rect1.topleft()));
		assertTrue(rect1.intersects(rect3, rect3.topleft()));
		assertTrue(rect3.intersects(rect1, rect1.topleft()));
		assertFalse(rect1.intersects(rect4, rect4.topleft()));
		assertFalse(rect4.intersects(rect1, rect1.topleft()));
	}

	@Test
	public void testIntersectsCirclePosition() {
		assertTrue(circle1.intersects(rect1, rect1.topleft()));
		assertTrue(rect1.intersects(circle1, circle1.center()));

		assertFalse(circle1.intersects(rect2, rect2.topleft()));
		assertFalse(rect2.intersects(circle1, circle1.center()));
	}

	@Test
	public void testWidth() {
		assertThat(rect1.width(), is(10.0d));
	}

	@Test
	public void testHeight() {
		assertThat(rect1.height(), is(20.0d));
	}

}
