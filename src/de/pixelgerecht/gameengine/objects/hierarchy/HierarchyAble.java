package de.pixelgerecht.gameengine.objects.hierarchy;

public interface HierarchyAble<T extends HierarchyAble<T>> {
	Hierarchy<T> hierarchy();
}
