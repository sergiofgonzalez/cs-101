package org.joolzminer.examples.adt.lists;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicLinkedList<T> implements List<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicLinkedList.class);
	
	protected Node<T> list;
	
	public DynamicLinkedList() {		
		this.list = null;
	}
		

	@Override
	public boolean isEmpty() {
		return list == null;
	}

	@Override
	public void add(T element, int position) {
		if (position < 0 || position > getLength()) {
			LOGGER.error("position must be a value between 0 and {}, but was {}", getLength(), position);
			throw new IllegalArgumentException("position is out of range");
		}
		
		Node<T> node = new Node<>();
		node.setInfo(element);
		
		if (position == 0) {
			node.setNext(list);
			list = node;
		} else {
			Node<T> p = list;
			for (int i = 0; i < position - 1; i++) {
				p = p.getNext();
			}
			Node<T> q = p.getNext();
			p.setNext(node);
			node.setNext(q);			
		}		
	}

	@Override
	public void remove(int position) {
		if (position < 0 || position >= getLength()) {
			LOGGER.error("position must be a value between 0 and {} but was {}", getLength() - 1, position);
			throw new IllegalArgumentException("position is out of range");
		}
		
		if (position == 0) {
			list = list.getNext();
		} else {
			Node<T> p = list;
			for (int i = 0; i < position - 1; i++) {
				p = p.getNext();
			}
			p.setNext(p.getNext().getNext());
		}
	}

	@Override
	public int getLength() {
		int length = 0;
		Node<T> p = list;
		while (p != null) {
			++length;
			p = p.getNext();
		}
		return length;
	}

	@Override
	public void traverse(Consumer<T> consumer) {
		Node<T> p = list;
		while (p != null) {
			consumer.accept(p.getInfo());
			p = p.getNext();
		}
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

	protected static class Node<T> {
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
