package de.pixelgerecht.gameengine.platform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import de.pixelgerecht.gameengine.input.InputEvent;
import de.pixelgerecht.gameengine.input.UserInput;
import de.pixelgerecht.gameengine.runtime.Logging;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

final class JavaFxUserInput implements UserInput {

	private final Set<KeyCode> keysPressed = new HashSet<>(7);
	private final Map<InputEvent, KeyCode> mapping = new HashMap<>(7);

	JavaFxUserInput() {
		mapping.put(InputEvent.ESCAPE, KeyCode.ESCAPE);
		mapping.put(InputEvent.LEFT, KeyCode.LEFT);
		mapping.put(InputEvent.RIGHT, KeyCode.RIGHT);
		mapping.put(InputEvent.UP, KeyCode.UP);
		mapping.put(InputEvent.DOWN, KeyCode.DOWN);
		mapping.put(InputEvent.FIRE1, KeyCode.CONTROL);
		mapping.put(InputEvent.FIRE2, KeyCode.SPACE);
		mapping.put(InputEvent.PAUSE, KeyCode.P);
		mapping.put(InputEvent.RESTART, KeyCode.R);
	}

	@Override
	public void poll() {
		// NOP
	}

	@Override
	public boolean escape() {
		return keysPressed.contains(mapping.get(InputEvent.ESCAPE));
	}

	@Override
	public boolean left() {
		return keysPressed.contains(mapping.get(InputEvent.LEFT));
	}

	@Override
	public boolean right() {
		return keysPressed.contains(mapping.get(InputEvent.RIGHT));
	}

	@Override
	public boolean up() {
		return keysPressed.contains(mapping.get(InputEvent.UP));
	}

	@Override
	public boolean down() {
		return keysPressed.contains(mapping.get(InputEvent.DOWN));
	}

	@Override
	public boolean fire1() {
		return keysPressed.contains(mapping.get(InputEvent.FIRE1));
	}

	@Override
	public boolean fire2() {
		return keysPressed.contains(mapping.get(InputEvent.FIRE2));
	}

	@Override
	public boolean pause() {
		return keysPressed.contains(mapping.get(InputEvent.PAUSE));
	}

	@Override
	public boolean restart() {
		return keysPressed.contains(mapping.get(InputEvent.RESTART));
	}

	@Override
	public boolean pressed(InputEvent event) {
		return keysPressed.contains(mapping.get(event));
	}

	@Override
	public boolean consume(InputEvent event) {
		return keysPressed.remove(mapping.get(event));
	}

	public EventHandler<KeyEvent> onKeyPressed() {
		return e -> {
			KeyCode code = e.getCode();
			keysPressed.add(code);
			Logging.LOG.log(Level.FINE, "{0} pressed.", code);
		};
	}

	public EventHandler<KeyEvent> onKeyReleased() {
		return e -> {
			KeyCode code = e.getCode();
			keysPressed.remove(code);
			Logging.LOG.log(Level.FINE, "{0} released.", code);
		};
	}
}
