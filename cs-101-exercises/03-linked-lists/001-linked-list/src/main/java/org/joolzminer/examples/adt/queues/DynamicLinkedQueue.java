package org.joolzminer.examples.adt.queues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicLinkedQueue<T> implements Queue<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicLinkedQueue.class);
	
	private Node<T> head;
	private Node<T> tail;
	
	
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void add(T element) {
		if (head == null) {
			head = new Node<T>();
			head.setInfo(element);
			head.setNext(null);
			tail = head;
		} else {
			Node<T> node = new Node<T>();
			node.setInfo(element);
			node.setNext(null);
			
			tail.setNext(node);
			tail = node;
		}
	}

	@Override
	public T remove() {
		if (head == null) {
			LOGGER.error("Cannot remove element from empty queue");
			throw new IllegalStateException("Cannot remove element from empty queue");
		}
		
		Node<T> node = head;
		head = head.getNext();
		return node.getInfo();
	}

	@Override
	public int getLength() {
		int i = 0;
		Node<T> p = head;
		while (p != null) {
			p = p.getNext();
			i++;
		}
		return i;
	}
	
	@Override
	public String toString() {
		if (head == null) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		Node<T> p = head;
		while (p != tail) {
			sb.append(p.getInfo());
			sb.append(", ");
			p = p.getNext();
		}
		
		sb.append(tail.getInfo());
		sb.append("]");
		
		return sb.toString();
	}

	private static class Node<T> {
		private T info;
		private Node<T> next;
		
		public T getInfo() {
			return info;
		}
		
		public void setInfo(T info) {
			this.info = info;
		}
		
		public Node<T> getNext() {
			return next;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [info=" + info + ", next=" + next + "]";
		}
	}
}
