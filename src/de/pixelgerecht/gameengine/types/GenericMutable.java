package de.pixelgerecht.gameengine.types;

import java.util.Objects;
import java.util.Optional;

class GenericMutable<T> implements Mutable<T> {
	private volatile T value;
	private Optional<ValueChangeListener<T>> listener = Optional.empty();

	public GenericMutable(T value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public void set(T newValue) {
		Objects.requireNonNull(newValue);
		listener.ifPresent(l -> l.onValueChange(this.value, newValue));
		this.value = newValue;
	}

	@Override
	public void setOnValueChange(ValueChangeListener<T> listener) {
		this.listener = Optional.ofNullable(listener);
	}

}
