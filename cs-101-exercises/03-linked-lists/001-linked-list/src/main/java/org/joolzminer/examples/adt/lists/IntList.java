package org.joolzminer.examples.adt.lists;

import java.util.function.IntConsumer;

public interface IntList {
	boolean isEmpty();
	void add(int element, int position);
	void remove(int position);
	int getLength();
	void traverse(IntConsumer consumer);
}
