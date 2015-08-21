package org.joolzminer.examples.adt.stacks;

public interface Stack<T> {
	boolean isEmpty();
	void push(T element);
	T pop();
}
