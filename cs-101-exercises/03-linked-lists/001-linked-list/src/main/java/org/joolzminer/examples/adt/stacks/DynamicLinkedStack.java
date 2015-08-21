package org.joolzminer.examples.adt.stacks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicLinkedStack<T> implements Stack<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicLinkedStack.class);
	
	private Node<T> stack;
	
	public DynamicLinkedStack() {
		stack = null;
	}
	
	@Override
	public boolean isEmpty() {
		return stack == null;
	}

	@Override
	public void push(T element) {
		Node<T> node = new Node<>(element, stack);
		stack = node;
	}

	@Override
	public T pop() {
		if (stack == null) {
			LOGGER.error("The stack is empty: cannot pop()");
			throw new IllegalStateException("Cannot pop on an empty stack");
		}
		
		Node<T> node = stack;
		stack = stack.getNext();
		
		return node.getInfo();
	}

	@Override
	public String toString() {
		if (stack == null) {
			return "0: ";
		}
		
		Stack<String> stackStr = new DynamicLinkedStack<>();

		Node<T> p = stack;
		int i = 0;
		while (p != null) {
			stackStr.push(i + ": " + p.getInfo());
			p = p.getNext();
			i++;
		}
		
		StringBuilder sb = new StringBuilder();
		while (!stackStr.isEmpty()) {
			sb.append(stackStr.pop());
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}


	private static class Node<T> {
		private T info;
		private Node<T> next;

		public Node(T info, Node<T> next) {
			this.info = info;
			this.next = next;
		}
		
		public T getInfo() {
			return info;
		}
		
		public Node<T> getNext() {
			return next;
		}
		
		@Override
		public String toString() {
			return "Node [info=" + info + ", next=" + next + "]";
		}		
	}
}
