package org.joolzminer.examples.adt.lists.runners;

import org.joolzminer.examples.adt.lists.DynamicLinkedList;
import org.joolzminer.examples.adt.lists.IntArrayBackedLinkedList;
import org.joolzminer.examples.adt.lists.IntList;
import org.joolzminer.examples.adt.lists.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListCopy {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(ListCopy.class);
	
	public static void main(String[] args) {
		List<Character> charList = new DynamicLinkedList<>();
		charList.add('a', 0);
		charList.add('e', 1);
		charList.add('i', 2);
		charList.add('o', 3);
		charList.add('u', 4);
		System.out.println(charList);
		
		List<Character> charListCopy = copyList(charList);
		System.out.println(charListCopy);
		
		charListCopy.add('b', 1);
		charListCopy.add('c', 2);
		System.out.println(charList);
		System.out.println(charListCopy);
		
		System.out.println("======================= ArrayBackedImpl");
		IntList intList = new IntArrayBackedLinkedList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		intList.add(1_002, 2);
		intList.add(1_003, 3);
		intList.add(1_004, 4);
		intList.add(1_005, 5);
		System.out.println(intList);
		
		IntList intListCopy = copyList(intList);
		System.out.println(intListCopy);

		intListCopy.add(999, 0);
		intListCopy.add(1_006, intListCopy.getLength());
		System.out.println(intListCopy);
	}
	
	public static <T> List<T> copyList(List<T> srcList) {
		List<T> copiedList = new DynamicLinkedList<T>();
		srcList.traverse(elem -> copiedList.add(elem, copiedList.getLength()));
		
		return copiedList;
	}
	
	public static IntList copyList(IntList srcList) {
		IntList copiedIntList = new IntArrayBackedLinkedList(srcList.getLength());		
		srcList.traverse(elem -> copiedIntList.add(elem, copiedIntList.getLength()));
		
		return copiedIntList;
	}
}
