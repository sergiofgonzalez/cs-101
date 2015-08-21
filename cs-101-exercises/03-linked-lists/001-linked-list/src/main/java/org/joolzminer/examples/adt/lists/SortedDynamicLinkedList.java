package org.joolzminer.examples.adt.lists;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortedDynamicLinkedList<T extends Comparable<T>> implements SortedList<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SortedDynamicLinkedList.class);
	
	private Node<T> list;
	
	@Override
	public boolean isEmpty() {
		return list == null;
	}

	@Override
	public void add(T element) {
		if (list == null) {
			list = new Node<>();
			list.info = element;
			list.next = null;
		} else {
			Node<T> p = list;
			Node<T> q = null;
			while (p != null && p.info.compareTo(element) < 0) {
				q = p;
				p = p.next;
			}
			Node<T> r = new Node<>();
			r.info = element;
			r.next = p;
			
			if (q != null) {
				q.next = r;
			}
			
			if (p == list) {
				list = r;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (list == null) {
			throw new IllegalStateException("Cannot remove from empty list");
		}
		
		if (list.info.compareTo(element) == 0) {
			list = list.next;
		} else {
			Node<T> p = list;
			Node<T> q = null;
			while (p != null && p.info.compareTo(element) < 0) {
				q = p;
				p = p.next;
			}
			
			if (p != null && p.info.compareTo(element) == 0) {
				if (p == list ) {
					list = p.next;
				} else {
					q.next = p.next;
				}
			} else {
				LOGGER.error("Element `{}` not found in the list: cannot remove", element);
				throw new IllegalArgumentException("Element asked to be removed not found in the list");
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
	public boolean contains(T element) {
		if (list == null) {
			return false;
		} else {
			Node<T> p = list;
			while (p != null && p.info.compareTo(element) < 0) {
				p = p.next;
			}
			
			if (p == null) {
				return false;
			} else {
				if (p.info.compareTo(element) == 0) {
					return true;
				} else {
					return false;
				}
			}
		}
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
	}
}
