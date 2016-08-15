package de.pixelgerecht.breakout;

import static de.pixelgerecht.breakout.Main.LOG;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BRICK_COUNT;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BRICK_HEIGHT;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.BRICK_START_Y;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HEADLINE_FONT_NAME;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HEADLINE_FONT_SIZE;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HEADLINE_ID;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.MSG_FONT_NAME;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.MSG_FONT_SIZE;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.MSG_ID;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.ROW_COLORS;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.ROW_COUNT;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.ROW_POINTS;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.WALL_WIDTH;
import static de.pixelgerecht.gameengine.representation.TextAlign.CENTER;
import static java.util.Objects.requireNonNull;
import static java.util.logging.Level.FINE;

import de.pixelgerecht.breakout.audio.Sounds;
import de.pixelgerecht.breakout.config.BreakoutConfigs;
import de.pixelgerecht.breakout.session.GameState;
import de.pixelgerecht.breakout.session.Session;
import de.pixelgerecht.breakout.subject.Ball;
import de.pixelgerecht.breakout.subject.Brick;
import de.pixelgerecht.breakout.subject.LivesText;
import de.pixelgerecht.breakout.subject.Paddle;
import de.pixelgerecht.breakout.subject.ScoreText;
import de.pixelgerecht.breakout.subject.Wall;
import de.pixelgerecht.gameengine.input.InputEvent;
import de.pixelgerecht.gameengine.input.UserInput;
import de.pixelgerecht.gameengine.representation.Color;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Representation;
import de.pixelgerecht.gameengine.representation.Text;
import de.pixelgerecht.gameengine.representation.World;
import de.pixelgerecht.gameengine.runtime.Controller;
import de.pixelgerecht.gameengine.runtime.EngineMetrics;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.types.Vector2d;

public final class BreakOutController implements Controller {
	private final EngineMetrics metrics;

	private Paddle paddle;
	private Ball ball;

	private Wall wallWest;
	private Wall wallNorth;
	private Wall wallEast;
	private Wall wallSouth;

	private Session session = Session.reset();



	public BreakOutController(EngineMetrics engineMetrics) {
		requireNonNull(engineMetrics);
		this.metrics = engineMetrics;
	}


	@Override
	public void onInit(World view) {
		LOG.log(FINE, "Controller begins initialization-phase.");

		resetLevel(view);
	}


	private void resetLevel(World world) {
		session = Session.reset();

		final Layer background = world.layer(0);
		background.objects().clear();

		final GameEntity blackBox = GameEntity.simple("Background", background, Vector2d.of(0, 0), Representation.filledRect(metrics.width(), metrics.height(), Color.rgb(0, 0, 0)));
		background.objects().add(blackBox);

		final Layer foreground = world.layer(1);
		foreground.objects().clear();

		paddle = Paddle.newInstance(foreground);
		foreground.objects().add(paddle.gameEntity());

		ball = Ball.newInstance(foreground, paddle);
		foreground.objects().add(ball.gameEntity());

		// Walls
		wallWest = Wall.reflective("WestWall", foreground, Vector2d.of(0, 0), Vector2d.of(WALL_WIDTH, metrics.height()));
		wallNorth = Wall.reflective("NorthWall", foreground, Vector2d.of(0 + WALL_WIDTH, 0), Vector2d.of(metrics.width(), WALL_WIDTH));
		wallEast = Wall.reflective("EastWall", foreground, Vector2d.of(metrics.width() - WALL_WIDTH, 0 + WALL_WIDTH), Vector2d.of(WALL_WIDTH, metrics.height()));
		wallSouth = Wall.deadly("SouthWall", foreground, Vector2d.of(0 + WALL_WIDTH, metrics.height() - WALL_WIDTH), Vector2d.of(metrics.width(), WALL_WIDTH), paddle, session);

		foreground.objects().add(wallWest.gameEntity());
		foreground.objects().add(wallNorth.gameEntity());
		foreground.objects().add(wallEast.gameEntity());
		foreground.objects().add(wallSouth.gameEntity());

		final double brickWidth = (metrics.width() - (WALL_WIDTH) * 2) / BRICK_COUNT;

		for (int rowIndex = 0; rowIndex < ROW_COUNT; rowIndex++) {
			final double brickY = BRICK_START_Y - (rowIndex * BRICK_HEIGHT);

			for (int brickIndex = 0; brickIndex < BRICK_COUNT; brickIndex++) {
				final double brickX = WALL_WIDTH + (brickIndex * brickWidth);
				final String id = BreakoutConfigs.BRICK_ID_PREFIX + rowIndex + "" + brickIndex;
				final Vector2d position = Vector2d.of(brickX, brickY);
				final Vector2d bounds = Vector2d.of(brickWidth, BRICK_HEIGHT);

				final Brick brick = Brick.newInstance(id, foreground, position, bounds, ROW_COLORS[rowIndex], session, ROW_POINTS[rowIndex]);
				foreground.objects().add(brick.gameEntity());
			}
		}
		session.setBricksToDestroy(BRICK_COUNT);

		final Layer hud = world.layer(2);
		hud.objects().clear();

		final ScoreText scoreText = ScoreText.newInstance(hud, session);
		hud.objects().add(scoreText.gameEntity());

		final LivesText livesText = LivesText.newInstance(hud, session);
		hud.objects().add(livesText.gameEntity());


		final Layer messages = world.layer(3);
		messages.objects().clear();
	}


	private void addMessageLayer(String headline, String message, World world) {
		final Layer layer = world.layer(3);
		layer.objects().clear();

		final GameEntity blackBox = GameEntity.simple("Layer", layer, Vector2d.of(0, 0), Representation.filledRect(metrics.width(), metrics.height(), Color.rgba(0, 0, 0, 0.5)));
		layer.objects().add(blackBox);

		final Text headlineText = Text.of(HEADLINE_FONT_NAME, HEADLINE_FONT_SIZE, Color.rgb(255, 255, 255), CENTER, headline);
		final GameEntity headlineEntity = GameEntity.simple(HEADLINE_ID, layer, Vector2d.of(metrics.width() / 2, metrics.height() / 2), headlineText);
		layer.objects().add(headlineEntity);

		final Text msgText = Text.of(MSG_FONT_NAME, MSG_FONT_SIZE, Color.rgb(255, 255, 255), CENTER, message);
		final GameEntity msgEntity = GameEntity.simple(MSG_ID, layer, Vector2d.of(metrics.width() / 2, (metrics.height() / 2) + HEADLINE_FONT_SIZE + 10.0d), msgText);
		layer.objects().add(msgEntity);
	}


	@Override
	public void onFrame(UserInput input, World world) {

		if (input.restart()) {
			resetLevel(world);
			input.consume(InputEvent.RESTART);
			LOG.info("Game restarted.");
			return;
		}

		if (input.pause()) {
			world.pause();
			input.consume(InputEvent.PAUSE);

			if (session.state() == GameState.PAUSED) {
				session.setState(GameState.RUNNING);
			}
			else if (session.state() == GameState.RUNNING) {
				session.setState(GameState.PAUSED);
			}
			LOG.info("Game paused.");
			return;
		}

		if (input.escape()) {
			LOG.info("Use exits game.");
			System.exit(0);
		}


		if (session.state() == GameState.RUNNING) {

			if (session.bricksToDestroy() <= 0) {
				addMessageLayer("YOU WIN!", "Press 'r' to restart", world);
				session.setState(GameState.ENDED);
				Sounds.WON.play();
				LOG.info("Game won!");
				return;
			}

			if (session.lives() <= 0) {
				addMessageLayer("YOU LOSE!", "Press 'r' to restart", world);
				session.setState(GameState.ENDED);
				Sounds.LOST.play();
				LOG.info("Game lost!");
				return;
			}

			paddle.handleInput(input, ball);
		}
	}

}
