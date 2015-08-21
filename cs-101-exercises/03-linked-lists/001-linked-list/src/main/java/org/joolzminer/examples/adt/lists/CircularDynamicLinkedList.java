package org.joolzminer.examples.adt.lists;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircularDynamicLinkedList<T> implements List<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CircularDynamicLinkedList.class);
	
	protected Node<T> list;
	
	@Override
	public boolean isEmpty() {
		return list == null;
	}

	@Override
	public void add(T element, int position) {	
		int len = getLength();
		if (position < 0 || position > len) {
			LOGGER.error("Cannot add: invalid value for position: {}; list length={}", position, len);
			throw new IllegalArgumentException("Position is out of allowed range");
		}
		
		if (list == null) {
			list = new Node<>();
			list.info = element;
			list.next = list;
		} else if (position == 0) {
			Node<T> p = list;
			list = new Node<>();
			list.info = element;
			list.next = p;
			for (int i = 0; i < len - 1; i++) {
				p = p.next;
			}
			p.next = list;
		}
		else {
			Node<T> p = list;
			for (int i = 0; i < position - 1; i++) {
				p = p.next;
			}
			
			Node<T> node = new Node<>();
			node.info = element;
			node.next = p.next;
			p.next = node;
		}		
	}

	@Override
	public void remove(int position) {
		int len = getLength();
		if (position < 0 || position >= len) {
			LOGGER.error("Cannot remove element in given position: {}; list length = {}", position, len);
			throw new IllegalArgumentException("Position is out of allowed range for remove()");
		}
		
		if (len == 1) {
			list = null;
		} else if (position == 0) {
			Node<T> p = list;
			list = list.next;
			for (int i = 0; i < len - 1; i++) {
				p = p.next;
			}
			p.next = list;
		} else {
			Node<T> p = list;
			for (int i = 0; i < position - 1; i++) {
				p = p.next;
			}
			
			p.next = p.next.next;
		}
	}

	@Override
	public int getLength() {
		if (list == null) {
			return 0;
		}
		
		int len = 1;
		Node<T> p = list.next;
				
		while (p != list) {
			p = p.next;
			len++;
		}
		
		return len;
	}

	@Override
	public void traverse(Consumer<T> consumer) {
		if (list == null) {
			return; // nothing to do
		}
		
		Node<T> p = list;
		do {
			consumer.accept(p.info);
			p = p.next;
		} while (p != list);
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
	}
}
