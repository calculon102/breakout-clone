package de.pixelgerecht.gameengine.representation;

public interface Text extends Representation {
	static Text of(String fontName, double size, Color color, TextAlign textAlign, String content) {
		return new TextImpl(fontName, size, color, textAlign, content);
	}

	String fontName();
	String content();
	double size();
	Color color();
	TextAlign align();
	void setContent(String content);
}
