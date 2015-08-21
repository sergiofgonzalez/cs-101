package org.joolzminer.examples.adt.queues;

public class CircularDynamicLinkedQueue<T> implements Queue<T> {

	private Node<T> tail;
	
	@Override
	public boolean isEmpty() {
		return tail == null;
	}


	@Override
	public void add(T element) {
		if (tail == null) {
			tail = new Node<>();
			tail.info = element;
			tail.next = tail;
		} else {
			Node<T> p = tail;
			tail = new Node<>();
			tail.info = element;
			tail.next = p.next;
			p.next = tail;
		}
	}


	@Override
	public T remove() {
		if (tail == null) {
			throw new IllegalStateException("Cannot remove from empty queue");
		}
		if (tail.next == tail) {
			Node<T> p = tail;
			tail = null;
			return p.info;
		} else {
			Node<T> p = tail.next;
			tail.next = p.next;
			return p.info;
		}
	}


	@Override
	public int getLength() {
		if (tail == null) {
			return 0;
		} else {
			Node<T> p = tail.next;
			int len = 1;
			while (p != tail) {
				len++;
				p = p.next;
			}
			return len;
		}
	}
	
	@Override
	public String toString() {
		if (tail == null) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		Node<T> p = tail.next;
		while (p != tail) {
			sb.append(p.info);
			sb.append(", ");
			p = p.next;
		}
		
		sb.append(tail.info);
		sb.append("]");
		
		return sb.toString();
	}
	
	private static class Node<T> {
		private T info;
		private Node<T> next;
	}
}
