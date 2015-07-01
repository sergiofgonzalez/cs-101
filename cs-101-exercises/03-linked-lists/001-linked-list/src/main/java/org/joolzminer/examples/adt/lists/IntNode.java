package org.joolzminer.examples.adt.lists;

import java.util.OptionalInt;

public class IntNode {
	private int info;
	private OptionalInt next;
	
	public int getInfo() {
		return info;
	}
	
	public void setInfo(int info) {
		this.info = info;
	}
	
	public OptionalInt getNext() {
		return next;
	}
	
	public void setNext(OptionalInt next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "IntNode [info=" + info + ", next=" + next.orElse(-1) + "]";
	}	
}
