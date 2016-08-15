package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_FONT_NAME;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_FONT_SIZE;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_SCORE_COLOR;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_SCORE_ID;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_SCORE_POSITION;

import de.pixelgerecht.breakout.session.Session;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Text;
import de.pixelgerecht.gameengine.representation.TextAlign;
import de.pixelgerecht.gameengine.subjects.GameEntity;

public interface ScoreText extends BreakoutSubject {
	static ScoreText newInstance(Layer forLayer, Session session) {
		final Text scoreText = Text.of(HUD_FONT_NAME, HUD_FONT_SIZE, HUD_SCORE_COLOR, TextAlign.RIGHT, "Score: " + session.score());
		session.setOnScoreChange((prev, now) -> scoreText.setContent("Score: " + now));

		final GameEntity scoreEntity = GameEntity.simple(HUD_SCORE_ID, forLayer, HUD_SCORE_POSITION, scoreText);
		return new ScoreTextImpl(scoreEntity);
	}
}
