package de.pixelgerecht.breakout.config;

import de.pixelgerecht.gameengine.representation.Color;
import de.pixelgerecht.gameengine.types.Vector2d;

public final class BreakoutConfigs {

	public static final String PADDLE_ID = "paddle";
	public static final Double PADDLE_WIDTH = 110.0d;
	public static final Double PADDLE_HEIGHT = 8.0d;
	public static final Vector2d PADDLE_POSITION = Vector2d.of(345.0d, 550.0d);
	public static final Color PADDLE_COLOR = Color.rgb(31, 131, 216);
	public static final Double PADDLE_FRAME_MOVEMENT = 0.2d;
	public static final Long PADDLE_ZONES = 6l;
	public static final Double PADDLE_ZONE_WIDTH = PADDLE_WIDTH / PADDLE_ZONES;
	/** From left to right. */
	public static final Double[] PADDLE_ZONE_REFLECTION_X = {
			-1.50d,
			-1.00d,
			-0.50d,
			 0.50d,
			 1.00d,
			 1.50d,
	};

	public static final String BALL_ID = "ball";
	public static final Double BALL_RADIUS = 8.0d;
	public static final Vector2d BALL_POSITION = Vector2d.of(394.0d, 533.0d);
	public static final Color BALL_COLOR = Color.rgb(150, 150, 255);
	public static final Double BALL_VERTICAL_VELOCITY = 3.0d;

	public static final Double WALL_WIDTH = 5.0d;
	public static final Color WALL_COLOR = Color.rgb(240, 240, 240);
	public static final Color WALL_DEADLY_COLOR = Color.rgb(200, 60, 60);

	public static final String BRICK_ID_PREFIX = "brick";
	public static final Long BRICK_COUNT = 15l;
	public static final double BRICK_HEIGHT = 15.0d;
	public static final double BRICK_START_Y = 200.0d;

	public static final Long ROW_COUNT = 7l;
	public static final Color[] ROW_COLORS = {
		Color.rgb(66, 72, 200),
		Color.rgb(0, 86, 253),
		Color.rgb(62, 255, 2),
		Color.rgb(2, 159, 36),
		Color.rgb(92, 110, 0),
		Color.rgb(166, 73, 4),
		Color.rgb(104, 0, 0)
	};
	public static final Integer[] ROW_POINTS = {
			1,
			1,
			4,
			4,
			7,
			7,
			10
	};

	public static final String HUD_FONT_NAME = "Arial";
	public static final double HUD_FONT_SIZE = 20.0d;

	public static final String HUD_SCORE_ID = "Score";
	public static final Vector2d HUD_SCORE_POSITION = Vector2d.of(780.0d, 35.0d);
	public static final Color HUD_SCORE_COLOR = Color.rgb(255,255,255);

	public static final String HUD_LIVES_ID = "Lives";
	public static final Vector2d HUD_LIVES_POSITION = Vector2d.of(780.0d, 60.0d);
	public static final Color HUD_LIVES_COLOR = Color.rgb(255,255,255);

	public static final String HEADLINE_ID = "Headline";
	public static final String HEADLINE_FONT_NAME = "Arial";
	public static final double HEADLINE_FONT_SIZE = 30.0d;

	public static final String MSG_ID = "Headline";
	public static final String MSG_FONT_NAME = "Arial";
	public static final double MSG_FONT_SIZE = 20.0d;
}
