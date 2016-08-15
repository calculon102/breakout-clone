package de.pixelgerecht.gameengine.objects.hierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * TODO Testing, Documentation
 * @author calculon102
 *
 * @param <T>
 */
final class HierarchyImpl<T extends HierarchyAble<T>> implements Hierarchy<T> {
	private volatile Optional<T> parent = Optional.empty();
	private volatile List<T> children = null;

	@Override
	public Optional<T> parent() {
		return parent;
	}

	@Override
	public Collection<T> children() {
		if (children == null) {
			return Collections.emptyList();
		}

		return Collections.unmodifiableCollection(children);
	}

	@Override
	public void setParent(T newParent) {
		Objects.requireNonNull(newParent);
		this.parent = Optional.of(newParent);
	}

	@Override
	public void unsetParent() {
		this.parent = Optional.empty();
	}

	@Override
	public void addChild(T child) {
		Objects.requireNonNull(child);

		if (children == null) {
			children = new ArrayList<>(1);
		}

		child.hierarchy().setParent(child);
		children.add(child);
	}

	@Override
	public void removeChild(T child) {
		Objects.requireNonNull(child);

		if (children == null || !children.contains(child)) {
			return;
		}

		child.hierarchy().unsetParent();
		children.remove(child);
	}

}
