package de.pixelgerecht.gameengine.platform;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import de.pixelgerecht.gameengine.representation.Color;
import de.pixelgerecht.gameengine.representation.Layer;
import de.pixelgerecht.gameengine.representation.Text;
import de.pixelgerecht.gameengine.subjects.GameEntity;
import de.pixelgerecht.gameengine.subjects.GameObjectPair;
import de.pixelgerecht.gameengine.types.Vector2d;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * TODO Buffer colors
 * TODO Externalize JavaFx-Drawing
 * @author calculon102
 *
 */
final class JavaFxLayer implements Layer {
	public final int z;
	public final Canvas canvas;

	private final ObservableList<GameEntity> _objects = FXCollections.observableArrayList();
	private final Collection<GameObjectPair> collisionMemory = new HashSet<>();

	public JavaFxLayer(int z, Canvas canvas) {
		assert z >= 0 : "z-axis must be gte 0!";

		requireNonNull(canvas);

		this.z = z;
		this.canvas = canvas;
	}

	@Override
	public Collection<GameEntity> objects() {
		return _objects;
	}

	/**
	 * Renders each object of this layer.
	 */
	public void renderFrame() {
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		// Apply physics
		collisionMemory.clear();

		final List<GameEntity> objects = new ArrayList<>(_objects);

		objects.forEach(o -> o.rigidBody().onFrame());

		// Render results
		objects.forEach(o -> o.representation().ifPresent(r -> {
			gc.save();
			if ( o.angle().degrees() != 0.0d) {
				final Vector2d center = o.center();
				gc.translate(center.x(), center.y());
				gc.rotate(o.angle().degrees());
				gc.translate(-center.x(), -center.y());
			}
			gc.translate(o.topLeft().x(), o.topLeft().y());
			r.render(this);
			gc.restore();
		}));
	}

	@Override
	public String toString() {
		return "JavaFxLayer [z=" + z + ", " + _objects.size() + " objects]";
	}

	@Override
	public void fillRect(double width, double height, Color color) {
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(javafx.scene.paint.Color.rgb(color.red(), color.green(), color.blue(), color.alpha()));
		gc.fillRect(0, 0, width, height);
	}

	@Override
	public void fillCircle(double radius, Color color) {
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(javafx.scene.paint.Color.rgb(color.red(), color.green(), color.blue(), color.alpha()));
		gc.fillOval(0.0d, 0.0d, radius*2, radius*2);
	}

	@Override
	public void text(Text text) {
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font(text.fontName(), text.size()));
		gc.setTextAlign(TextAlignment.valueOf(text.align().toString()));
		final Color color = text.color();
		gc.setFill(javafx.scene.paint.Color.rgb(color.red(), color.green(), color.blue(), color.alpha()));
		gc.fillText(text.content(), 0.0d, 0.0d);
	}

	@Override
	public void addToCollisionMemory(GameObjectPair pair) {
		collisionMemory.add(pair);
	}

	@Override
	public boolean isInCollisionMemory(GameObjectPair pair) {
		return collisionMemory.contains(pair);
	}
}
