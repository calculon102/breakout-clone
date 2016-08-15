package de.pixelgerecht.breakout.subject;

import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_FONT_NAME;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_FONT_SIZE;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_LIVES_COLOR;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_LIVES_ID;
import static de.pixelgerecht.breakout.config.BreakoutConfigs.HUD_LIVES_POSITION;

import de.pixelgerecht.breakout.session.Session;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Text;
import de.pixelgerecht.gameengine.representation.TextAlign;
import de.pixelgerecht.gameengine.subjects.GameEntity;

public interface LivesText extends BreakoutSubject {
	static LivesText newInstance(Layer forLayer, Session session) {
		final Text livesText = Text.of(HUD_FONT_NAME, HUD_FONT_SIZE, HUD_LIVES_COLOR, TextAlign.RIGHT, "Lives: " + session.lives());
		session.setOnLivesChange((prev, now) -> livesText.setContent("Lives: " + now));

		final GameEntity livesEntity = GameEntity.simple(HUD_LIVES_ID, forLayer, HUD_LIVES_POSITION, livesText);
		return new LivesTextImpl(livesEntity);
	}
}
