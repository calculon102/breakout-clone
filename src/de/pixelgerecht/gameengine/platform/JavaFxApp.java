package de.pixelgerecht.gameengine.platform;

import de.pixelgerecht.gameengine.runtime.Controller;
import de.pixelgerecht.gameengine.runtime.EngineMetrics;
import javafx.application.Application;

public final class JavaFxApp implements App {

	public JavaFxApp(String title, EngineMetrics metrics, Controller controller) {
		// TOFIX Better solution than writing to static fields possible?
		JavaFxMain.appTitle = title;
		JavaFxMain.appMetrics = metrics;
		JavaFxMain.appController = controller;
	}

	@Override
	public void start(String[] args) {
		Application.launch(JavaFxMain.class, args);
	}
}
