package org.joolzminer.examples.adt.lists;

import java.util.function.Consumer;

public interface SortedList<T extends Comparable<T>> {
	boolean isEmpty();
	void add(T element);
	void remove(T element);
	int getLength();
	boolean contains(T element);
	void traverse(Consumer<T> consumer);
}
