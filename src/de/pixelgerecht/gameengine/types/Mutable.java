package de.pixelgerecht.gameengine.types;

/**
 * Represents a mutable property of type T. The value is not nullable!
 * @author calculon102
 *
 * @param <T>
 */
public interface Mutable<T> {
	static Mutable<Boolean> of(boolean value) {
		return new GenericMutable<>(Boolean.valueOf(value));
	}
	static Mutable<Long> of(long value) {
		return new GenericMutable<>(Long.valueOf(value));
	}
	static Mutable<Double> of(double value) {
		return new GenericMutable<>(Double.valueOf(value));
	}
	static Mutable<String> of(String value) {
		return new GenericMutable<>(value);
	}

	/**
	 * @return Current value.
	 */
	T get();

	/**
	 * Replaces the value with given value.
	 * @param newValue Value to set.
	 */
	void set(T newValue);

	/**
	 * Registers a specific value-change-listener.
	 * @param listener listener to call, when value is changed. Is called <b>before</b> the value is persisted!
	 */
	void setOnValueChange(ValueChangeListener<T> listener);
}
