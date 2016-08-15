package de.pixelgerecht.breakout;

import de.pixelgerecht.gameengine.runtime.EngineMetrics;

public final class BreakoutMetrics implements EngineMetrics {
	@Override
	public double width() {
		return 800.0d;
	}

	@Override
	public double height() {
		return 600.0d;
	}

	@Override
	public double ratio() {
		return width() / height();
	}

	@Override
	public double framerate() {
		return 60;
	}
}
