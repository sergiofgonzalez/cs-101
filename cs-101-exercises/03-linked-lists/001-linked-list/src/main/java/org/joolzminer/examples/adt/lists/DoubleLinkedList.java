package org.joolzminer.examples.adt.lists;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleLinkedList<T> implements List<T> {

	private static Logger LOGGER = LoggerFactory.getLogger(DoubleLinkedList.class);
	
	private Node<T> list;
	
	@Override
	public boolean isEmpty() {
		return list == null;
	}

	@Override
	public void add(T element, int position) {
		int len = getLength();
		
		if (position < 0 || position > getLength()) {
			LOGGER.error("Cannot add: Position `{}` is out of range for a list of length `{}`", position, len);
			throw new IllegalArgumentException("Position is out of bounds");
		}
						
		if (position == 0) {
			Node<T> p = new Node<>();
			p.info = element;
			p.next = list;
			p.prev = null;
			list = p;
		} else {
			Node<T> p = list;
			for (int i = 0; i < position - 1; i++) {
				p = p.next;
			}
			Node<T> q = new Node<>();
			q.info = element;
			q.next = p.next;
			q.prev = p;
			p.next = q;
		}
	}

	@Override
	public void remove(int position) {
		int len = getLength();
		if (position < 0 || position >= getLength()) {
			LOGGER.error("Cannot remove: Position `{}` is out of range for a list of length `{}`", position, len);
			throw new IllegalArgumentException("Position is out of bounds");			
		}
		
		if (position == 0) {
			list = list.next;
		} else {
			Node<T> p = list;
			for (int i = 0; i < position -1; i++) {
				p = p.next;
			}
			p.next = p.next.next;
			if (p.next != null) {
				p.next.prev = p;
			}
		}
	}

	@Override
	public int getLength() {
		int len = 0;
		Node<T> p = list;
		while (p != null) {
			p = p.next;
			len++;
		}
		return len;
	}

	@Override
	public void traverse(Consumer<T> consumer) {
		Node<T> p = list;
		while (p != null) {
			consumer.accept(p.info);
			p = p.next;
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
	
	private static class Node<T> {
		private T info;
		private Node<T> next;
		private Node<T> prev;
	}
}
