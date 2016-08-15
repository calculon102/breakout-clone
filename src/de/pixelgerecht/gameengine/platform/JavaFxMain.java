package de.pixelgerecht.gameengine.platform;

import static de.pixelgerecht.breakout.Main.LOG;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import de.pixelgerecht.gameengine.runtime.Controller;
import de.pixelgerecht.gameengine.runtime.EngineMetrics;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class JavaFxMain extends Application {
	/**
	 * Ugly hack because there is no way to instantiate an javafx-application
	 * with parameters or an exisitng instance.
	 */
	static String appTitle = null;
	static EngineMetrics appMetrics = null;
	static Controller appController = null;

	/** For the main-loop outside of JavaFx. */
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();
	/** Maps JavaFX-Input to the game-engine. */
	private final JavaFxUserInput input = new JavaFxUserInput();

	/** MAps all graphics to a JavaFx-Pane. Initialized in {@link #start(Stage)}. */
	private JavaFxView view;
	/** Main game-controller and action-dispatcher. Initialized in {@link #start(Stage)}. */
	private Controller controller;

	@Override
	public void init() {
		Objects.requireNonNull(appTitle, "title missing when starting fx-app!");
		Objects.requireNonNull(appMetrics, "metrics missing when starting fx-app!");
		Objects.requireNonNull(appController, "appController missing when starting fx-app!");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LOG.log(Level.FINE, "JavaFx-process starting...");

		view = new JavaFxView(primaryStage, appMetrics);
		controller = appController;

		Scene scene = new Scene(view.getRoot());
		scene.setOnKeyPressed(input.onKeyPressed());
		scene.setOnKeyReleased(input.onKeyReleased());
		LOG.log(Level.FINE, "View and scene created.");

		primaryStage.setTitle(appTitle);
		primaryStage.setScene(scene);
		primaryStage.show();
		LOG.log(Level.FINE, "Primary stage showing.");

		executorService.execute(() -> mainLoop());
	}

	@Override
	public void stop() throws Exception {
		executorService.shutdownNow();
	}

	private void mainLoop() {
		LOG.log(Level.FINE, "In main-loop.");

		Platform.runLater(() -> controller.onInit(view));
		final AnimationTimerImpl frameTimer = new AnimationTimerImpl();
		frameTimer.start();

		LOG.log(Level.FINE, "Frame-timer started.");
	}

	private class AnimationTimerImpl extends AnimationTimer {
		@Override
		public void handle(long now) {
			LOG.log(Level.FINEST, "New frame at {0} ms", now);

			input.poll();
			controller.onFrame(input, view);
			view.renderFrame();
		}
	}
}
