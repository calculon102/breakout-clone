package de.pixelgerecht.gameengine.platform;

import de.pixelgerecht.gameengine.runtime.Controller;
import de.pixelgerecht.gameengine.runtime.EngineMetrics;

public interface App {
	static App javafx(String title, EngineMetrics metrics, Controller controller) {
		return new JavaFxApp(title, metrics, controller);
	}

	void start(String[] args);
}