package org.joolzminer.examples.adt.recursion.structures;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecursiveList<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveList.class);
	
	
	private Optional<T> head;
	private RecursiveList<T> rest;

	public static <T> RecursiveList<T> prepend(T head, RecursiveList<T> list) {
		return new RecursiveList<>(head, list);
	}
	
	public static <T> RecursiveList<T> emptyList() {
		return new RecursiveList<T>();
	}
	
	private RecursiveList(T head, RecursiveList<T> list) {
		this.head = Optional.of(head);
		this.rest = list;
	}
	
	private RecursiveList() {
		this.head = Optional.empty();
		this.rest = null;
	}
		
	public boolean isEmpty() {
		return !head.isPresent();
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> arrayList = new ArrayList<>();
		traverseConvertingIntoArrayList(this, arrayList);
		return arrayList;
	}
	
	private static <T> void traverseConvertingIntoArrayList(RecursiveList<T> subList, ArrayList<T> accList) {
		if (subList.head.isPresent()) {
			accList.add(subList.head.get());
			traverseConvertingIntoArrayList(subList.rest, accList);
		}
	}
	
	public static <T> RecursiveList<T> fromArrayList(ArrayList<T> arrayList) {
		if (arrayList == null) {
			LOGGER.error("cannot convert to Recursive list if arrayList is null");
			throw new IllegalArgumentException("Cannot convert to RecursiveList from null arrayList");
		}
		return buildFromArrayList(arrayList, 0);
	}
	
	private static <T> RecursiveList<T> buildFromArrayList(ArrayList<T> arrayList, int pos) {
		if (pos < arrayList.size()) {
			return prepend(arrayList.get(pos), buildFromArrayList(arrayList, pos + 1));
		} else {
			return new RecursiveList<T>();
		}
	}

	public int getLength() {
		return getLengthRecursively(this);
	}
	
	private static <T> int getLengthRecursively(RecursiveList<T> list) {
		if (list.head.isPresent()) {
			return 1 + getLengthRecursively(list.rest);
		} else {
			return 0;
		}
	}
	
	public Optional<T> searchFor(T element) {
		return search(this, element);
	}
	
	private static <T> Optional<T> search(RecursiveList<T> subList, final T element) {
		if (subList.head.isPresent()) {
			if (subList.head.get().equals(element)) {
				return subList.head;
			} else {
				return search(subList.rest, element);
			}
		} else {
			return Optional.empty();
		}
	}

	public void traverse(Consumer<T> consumer) {
		if (!head.isPresent()) {
			return;
		} else {
			consumer.accept(head.get());
			rest.traverse(consumer);		
		}
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "{}";
		} else {
			return doToStringTraverse(this);	
		}		
	}	

	private static <T> String doToStringTraverse(RecursiveList<T> list) {
		if (list.head.isPresent()) {
			String str = "{ head: " + list.head.get() + ", rest: ";
			return str + doToStringTraverse(list.rest) + " }";
		} else {
			return "NULL";
		}
	}

}
