package org.joolzminer.examples.adt.queues;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntArrayBackedQueue implements IntQueue {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntArrayBackedQueue.class);
	
	private static final int DEFAULT_INITIAL_SIZE = 11;
	
	private static final double INFLATE_RATIO = .5;
	
	private int head;	/* points to first element to extract */
	private int tail;	/* points to last element added       */
	
	private int[] elements;
	
	public IntArrayBackedQueue() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public IntArrayBackedQueue(int initialSize) {
		if (initialSize < 1) {
			LOGGER.error("initialSize={}, must be greater than zero", initialSize);
			throw new IllegalArgumentException("Invalid initialSize");
		}		
					
		elements = new int[initialSize + 1]; 
		this.head = 0;
		this.tail = 0;
		
		LOGGER.debug("Created IntArrayBackedInstance with {} element(s); head={}, tail={}, elements={}", initialSize, head, tail, Arrays.toString(elements));		
	}
		
	@Override
	public boolean isEmpty() {
		LOGGER.debug("Checking if queue is empty: head={}, tail={}, elements={}", head, tail, Arrays.toString(elements));
		return head == tail;
	}

	@Override
	public void add(int element) {
		if (isFull()) {
			inflate(INFLATE_RATIO);
		}
		tail = (tail + 1) % elements.length;
		elements[tail] = element;
		
		LOGGER.debug("Element added to the queue: element={}, head={}, tail={}, elements={}", element, head, tail, Arrays.toString(elements));
	}

	@Override
	public int remove() {
		if (isEmpty()) {
			LOGGER.error("the queue is empty: head={}, tail={}", head, tail);
			throw new IllegalStateException("Cannot remove from empty queue");
		}
	
		head = (head + 1) % elements.length;
		int elem = elements[head];
			
		LOGGER.debug("Removed element from the queue: elem={}, head={}, tail={}, elements={}", elem, head, tail, Arrays.toString(elements));
		return elem;
	}

	@Override
	public int getLength() {
		
		int len;
		if (head <= tail) {
			len = tail - head;
		} else {
			len = tail + elements.length - head;
		}
		
		LOGGER.debug("Returning length={} for head={}, tail={}, elems={}", len, head, tail, Arrays.toString(elements));
		
		return len;
	}

	@Override
	public String toString() {
		if (head == tail) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = (head + 1) % elements.length;
		while (i != tail) {
			sb.append(elements[i]);
			sb.append(", ");
			i = (i + 1) % elements.length;
		}
		
		sb.append(elements[tail]);
		sb.append("]");
		
		return sb.toString();
	}
	
	private boolean isFull() {
		return getLength() == elements.length - 1;
	}
	
	private void inflate(double ratio) {		
		int newSize = Math.max(elements.length + (int) (elements.length * ratio), DEFAULT_INITIAL_SIZE);
		int oldLen = elements.length;
		int[] tempElements = new int[newSize];
		for (int i = 1, j = (head + 1) % elements.length; i < oldLen; i++, j = (j + 1) % elements.length) {
			tempElements[i] = elements[j];
		}
		elements = tempElements;
		head = 0;
		tail = oldLen - 1;
		LOGGER.debug("Array backing the queue has been inflated: head={}, tail={}, elements={}", head, tail, Arrays.toString(elements));
	}
}
