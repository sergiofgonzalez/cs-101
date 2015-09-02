package org.joolzminer.examples.adt.recursion.structures;

import java.util.function.Consumer;

public interface List<T> {
	boolean isEmpty();
	void add(T element, int position);
	void remove(int position);
	int getLength();
	void traverse(Consumer<T> consumer);
}
