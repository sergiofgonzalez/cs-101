package org.joolzminer.examples.adt.lists;

import java.util.Arrays;
import java.util.function.IntConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntSequentialList implements IntList {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntSequentialList.class);
	
	
	private static final int DEFAULT_INITIAL_SIZE = 10;
	
	private static final double INFLATE_RATIO = .5;
	
	
	private int tailIndex;
	
	private int[] elements;
	
	public IntSequentialList() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public IntSequentialList(int initialSize) {
		if (initialSize < 1) {
			LOGGER.error("initialSize={}, must be greater than 0", initialSize);
			throw new IllegalArgumentException("invalid initialSize");
		}
		LOGGER.debug("Creating IntSequential instance with {} elements", initialSize);
		elements = new int[initialSize];
		tailIndex = 0;
	}

	@Override
	public boolean isEmpty() {
		return tailIndex == 0;
	}

	@Override
	public void add(int element, int position) {
		if (position < 0 || position > tailIndex) {
			LOGGER.error("Position={} is out of range; tailIndex={}", position, tailIndex); 
			throw new IllegalArgumentException("position is out of range");
		}
		if (isFull()) {
			inflate(INFLATE_RATIO);
		}
		
		for (int i = tailIndex; i > position; i--) {
			elements[i] = elements[i-1];
		}
		elements[position] = element;
		tailIndex++;
	}

	@Override
	public void remove(int position) {
		if (position < 0 || position > tailIndex - 1) {
			LOGGER.error("position={} is out of range; tailIndex={}", position, tailIndex);
			throw new IllegalArgumentException("position is out of range");
		}
		
		tailIndex--;
		for (int i = position; i < tailIndex; i++) {
			elements[i] = elements[i+1];
		}
	}

	@Override
	public int getLength() {
		return tailIndex;
	}

	@Override
	public void traverse(IntConsumer consumer) {
		for (int i = 0; i < tailIndex; i++) {
			consumer.accept(elements[i]);
		}		
	}	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < tailIndex; i++) {
			sb.append(elements[i]);
			sb.append(", ");
		}
		if (sb.length() > 2 && sb.charAt(sb.length() - 2) == ',') {
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append("]");
		return sb.toString();
	}
	
	private boolean isFull() {
		return tailIndex == elements.length;
	}
	
	private void inflate(double ratio) {
		int newSize = Math.max(elements.length + (int) (elements.length * ratio), DEFAULT_INITIAL_SIZE);
		elements = Arrays.copyOf(elements, newSize);
	}
}