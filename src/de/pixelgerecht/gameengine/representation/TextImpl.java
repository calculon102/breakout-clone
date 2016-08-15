package de.pixelgerecht.gameengine.representation;

import java.util.Objects;

final class TextImpl implements Text {

	private final String name;
	private final double size;
	private final Color color;
	private String content;
	private TextAlign textAlign;

	public TextImpl(String name, double size, Color color, TextAlign textAlign, String content) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(size);
		assert size > 0.0d : "Size must not be zero or lower!";
		Objects.requireNonNull(color);
		Objects.requireNonNull(content);

		this.name = name;
		this.size = size;
		this.color = color;
		this.textAlign = textAlign;
		this.content = content;
	}

	@Override
	public String fontName() {
		return name;
	}

	@Override
	public double size() {
		return size;
	}

	@Override
	public Color color() {
		return color;
	}

	@Override
	public String content() {
		return content;
	}

	@Override
	public TextAlign align() {
		return textAlign;
	}

	@Override
	public String toString() {
		return "Font [name=" + name + ", size=" + size + "]";
	}

	@Override
	public void setContent(String content) {
		Objects.requireNonNull(content);
		this.content = content;
	}

	@Override
	public void render(Layer layer) {
		layer.text(this);
	}

}
