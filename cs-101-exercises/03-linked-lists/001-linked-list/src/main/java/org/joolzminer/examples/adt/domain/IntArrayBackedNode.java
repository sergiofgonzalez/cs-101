package org.joolzminer.examples.adt.domain;

public class IntArrayBackedNode {
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
