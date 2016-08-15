package de.pixelgerecht.gameengine.platform;

import static de.pixelgerecht.gameengine.runtime.Logging.LOG;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.World;
import de.pixelgerecht.gameengine.runtime.EngineMetrics;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

final class JavaFxView implements World {

	private final Map<Integer, JavaFxLayer> layers = new HashMap<>();
	private final BorderPane root = new BorderPane();
	private Pane layerPane = new StackPane();
	private EngineMetrics metrics;
	private boolean paused = false;

	@Override
	public void renderFrame() {
		if (paused) {
			return;
		}

		layers.forEach((k, v) -> {
			v.renderFrame();
		});
	}

	@Override
	public Layer layer(int z) {
		return getOrCreateLayer(z);
	}

	@Override
	public void pause() {
		paused = !paused;
		LOG.log(Level.INFO, "World paused: {0}", paused);
	}

	JavaFxView(Stage window, EngineMetrics metrics) {
		Objects.requireNonNull(window);
		Objects.requireNonNull(metrics);

		this.metrics = metrics;

		window.setResizable(false);

		layerPane.setPrefWidth(metrics.width());
		layerPane.setPrefHeight(metrics.height());
		layerPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		root.setCenter(layerPane);

		window.heightProperty().addListener((elem, oldVal, newVal) -> onWindowResized(window, oldVal, newVal));
		window.widthProperty().addListener((elem, oldVal, newVal) -> onWindowResized(window, oldVal, newVal));
	}

	Parent getRoot() {
		return root;
	}

	private void onWindowResized(Stage window, Number oldVal, Number newVal) {
		final double newRatio = window.getWidth() / window.getHeight();
		final double scale;
		if (newRatio < metrics.ratio()) {
			scale = window.getWidth() / metrics.width();
		} else {
			scale = window.getHeight() / metrics.height();
		}

		layerPane.setScaleX(scale);
		layerPane.setScaleY(scale);

		return;
	}

	private final JavaFxLayer getOrCreateLayer(int z) {
		assert z >= 0 : "layer-z must be gte 0!";

		Integer Z = Integer.valueOf(z);
		return layers.computeIfAbsent(Z, l -> {
			final Canvas canvas = new Canvas(metrics.width(), metrics.height());

			final JavaFxLayer layer = new JavaFxLayer(z, canvas);

			layerPane.getChildren().add(layer.canvas);

			return layer;
		});
	}
}
