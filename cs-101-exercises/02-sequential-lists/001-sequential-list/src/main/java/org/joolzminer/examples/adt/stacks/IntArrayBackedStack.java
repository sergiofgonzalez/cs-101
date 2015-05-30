package org.joolzminer.examples.adt.stacks;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntArrayBackedStack implements IntStack {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntArrayBackedStack.class);
		
	private static final int DEFAULT_INITIAL_SIZE = 10;
	
	private static final double INFLATE_RATIO = .5;
	
	private int top;
	
	private int[] elements;
	
	 public IntArrayBackedStack() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public IntArrayBackedStack(int initialSize) {
		if (initialSize < 1) {
			LOGGER.error("initialSize={}, must be greater than 0", initialSize);
			throw new IllegalArgumentException("invalid initialSize");
		}
		
		LOGGER.debug("Creating IntArrayBackedStackl instance with {} elements", initialSize);
		elements = new int[initialSize];
		top = -1;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public void push(int element) {
		if (isFull()) {
			inflate(INFLATE_RATIO);
		}		
		
		elements[++top] = element;
	}

	@Override
	public int pop() {
		if (top == -1) {
			throw new IllegalStateException("Cannot pop on empty stack");
		}
		return elements[top--];
	}

	@Override
	public String toString() {
		if (top == -1) {
			return "0: ";
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= top; i++) {
			sb.append(top - i);
			sb.append(": ");
			sb.append(elements[i]);
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private boolean isFull() {
		return top == elements.length - 1;
	}
	
	private void inflate(double ratio) {
		int newSize = Math.max(elements.length + (int) (elements.length * ratio), DEFAULT_INITIAL_SIZE);
		elements = Arrays.copyOf(elements, newSize);
	}
}
