package org.joolzminer.examples.adt.lists;

import java.util.Arrays;
import java.util.function.IntConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntArrayBackedLinkedList implements IntList {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IntArrayBackedLinkedList.class);
	
	private int list;
	private int free;
	
	private IntArrayBackedNode[] storage;
	
	private static final int DEFAULT_INITIAL_SIZE = 10;
	
	private static final double INFLATE_RATIO = .5;
	
	
	private static final int NULL = -1;
	
	private static final int EMPTY = 0;
	
	
	public IntArrayBackedLinkedList() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public IntArrayBackedLinkedList(int initialSize) {
		if (initialSize <= 0) {
			LOGGER.error("Cannot create IntArrayBackedLinkedList: initialSize={} must be greater than 1", initialSize);
			throw new IllegalArgumentException("Cannot create IntArrayBackedLinkedList with negative or zero size");
		}
		list = NULL;
		storage = new IntArrayBackedNode[initialSize];
		for (int i = 0; i < initialSize - 1; i++) {
			storage[i] = new IntArrayBackedNode(EMPTY, i + 1);
			storage[i].setNext(i + 1);
		}
		storage[initialSize - 1] = new IntArrayBackedNode(EMPTY, NULL);
	}

	@Override
	public boolean isEmpty() {
		return list == NULL;
	}



	@Override
	public void add(int element, int position) {
		int listLength = getLength();
		if (position < 0 || position > listLength) {
			LOGGER.error("Cannot add: position={} is out of bounds, list length={}", position, listLength);
			throw new IllegalArgumentException("Position is out of bounds");
		}				
		
		if (position == 0) {
			int p = getFreeNode();
			storage[p].setInfo(element);
			storage[p].setNext(list);
			list = p;
		} else {
			int p = getFreeNode();
			storage[p].setInfo(element);
			int q = list;
			for (int i = 0; i < position - 1; i++) {
				q = storage[q].getNext();
			}
			storage[p].setNext(storage[q].getNext());
			storage[q].setNext(p);
		}
	}


	@Override
	public void remove(int position) {
		int listLength = getLength();
		if (position < 0 || position >= listLength) {
			LOGGER.error("Cannot remove: position={} is out of bounds, list length={}", position, listLength);
			throw new IllegalArgumentException("Position is out of bounds");			
		}
		
		if (position == 0) {
			int p = list;
			list = storage[list].getNext();
			storage[p].setNext(storage[p].getNext());
			freeNode(p);
		} else {
			int p = list;
			for (int i = 0; i < position - 1; i++) {
				p = storage[p].getNext();
			}
			int q = storage[p].getNext();
			storage[p].setNext(storage[q].getNext());
			freeNode(q);
		}
	}



	@Override
	public int getLength() {
		int len = 0;
		int p = list;
		
		while (p != NULL) {
			len++;
			p = storage[p].getNext();
		}
		
		return len;
	}



	@Override
	public void traverse(IntConsumer consumer) {
		int p = list;
		while (p != NULL) {
			consumer.accept(storage[p].getInfo());
			p = storage[p].getNext();
		}
	}

	
	private int getFreeNode() {
		if (free == NULL) {
			inflate(INFLATE_RATIO);
		}
		
		int p = free;
		free = storage[free].getNext();
		return p;
	}
	
	
	private void freeNode(int p) {
		storage[p].setNext(free);
		free = p;
	}
	
	
	private void inflate(double ratio) {
		int oldSize = storage.length;
		int newSize = Math.max(oldSize + (int) (oldSize * ratio), DEFAULT_INITIAL_SIZE);

		storage = Arrays.copyOf(storage, newSize);

		for (int i = oldSize; i < newSize - 1; i++) {
			storage[i] = new IntArrayBackedNode(EMPTY, i + 1);
		}
		storage[newSize - 1] = new IntArrayBackedNode(EMPTY, NULL);
		
		free = oldSize;
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		traverse(elem -> {
			sb.append(elem);
			sb.append(", ");
		});
		
		if (sb.length() > 2) {
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String toString(boolean showInternals) {
		if (!showInternals) {
			return toString();
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("list: ");
		sb.append((list == NULL)? "x" : list);
		sb.append("\nfree: ");
		sb.append((free == NULL)? "x" : free);
		sb.append('\n');
		
		for (int i = 0; i < storage.length; i++) {
			sb.append(i);
			sb.append(": ");
			sb.append(storage[i]);
			sb.append('\n');
		}
		
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();		
	}
	
	private static class IntArrayBackedNode {
		private int info;
		private int next;
		
		public IntArrayBackedNode(final int info, final int next) {
			this.info = info;
			this.next = next;
		}

		public int getNext() {
			return next;
		}

		public void setNext(int next) {
			this.next = next;
		}

		public int getInfo() {
			return info;
		}

		public void setInfo(int info) {
			this.info = info;
		}

		@Override
		public String toString() {
			return "[info=" + info + ", next=" + next + "]";
		}	
	}
}
