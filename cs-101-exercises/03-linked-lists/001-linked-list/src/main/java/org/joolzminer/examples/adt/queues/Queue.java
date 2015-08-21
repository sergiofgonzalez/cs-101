package org.joolzminer.examples.adt.queues;

public interface Queue<T> {
	boolean isEmpty();
	void add(T element);
	T remove();
	int getLength();
}
