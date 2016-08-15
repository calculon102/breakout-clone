package de.pixelgerecht.breakout;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import de.pixelgerecht.gameengine.platform.App;
import de.pixelgerecht.gameengine.runtime.Controller;
import de.pixelgerecht.gameengine.runtime.EngineMetrics;

/**
 * TODO Externalize JavaFX-Methods to specific implementations of the objects.
 * TODO Tests of engine
 * TODO Maven build
 * TODO OSGi for gameengine?
 * @author calculon102
 *
 */
public final class Main {

	public static final Logger LOG = Logger.getLogger("de.pixelgerecht.breakout");

	public static void main(String[] args) {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream(new File("conf/logging.properties")));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		final EngineMetrics metrics = new BreakoutMetrics();
		LOG.log(Level.FINE, "Engine-metrics instantiated.");

		final Controller controller = new BreakOutController(metrics);
		LOG.log(Level.FINE, "Game-controller instantiated.");

		final App main = App.javafx("Breakout-clone", metrics, controller);
		LOG.log(Level.FINE, "JavaFxApp instantiated.");

		LOG.info("Starting app.");
		main.start(args);
	}

}
