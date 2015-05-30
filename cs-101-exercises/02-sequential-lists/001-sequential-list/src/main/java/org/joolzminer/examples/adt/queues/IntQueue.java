package org.joolzminer.examples.adt.queues;

public interface IntQueue {
	boolean isEmpty();
	void add(int element);
	int remove();
	int getLength();
}
