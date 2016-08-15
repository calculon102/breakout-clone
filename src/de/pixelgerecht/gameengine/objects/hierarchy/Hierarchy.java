package de.pixelgerecht.gameengine.objects.hierarchy;

import java.util.Collection;
import java.util.Optional;

public interface Hierarchy<T extends HierarchyAble<T>> {

	static <I extends HierarchyAble<I>> Hierarchy<I> empty() {
		return new HierarchyImpl<>();
	}

	Optional<T> parent();

	Collection<T> children();

	void setParent(T parent);

	void unsetParent();

	void addChild(T child);

	void removeChild(T child);

}
