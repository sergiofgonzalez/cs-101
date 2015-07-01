package org.joolzminer.examples.adt.lists.runners;

import java.util.Scanner;

import org.joolzminer.examples.adt.lists.ArrayBackedIntLinkedList;
import org.joolzminer.examples.adt.lists.IntList;

public class PrintEnteredNumbers {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IntList intList = new ArrayBackedIntLinkedList();
		int userInput;
		System.out.println("Type integers: ");
		do {
			userInput = scanner.nextInt();
			if (userInput != 0) {
				intList.add(userInput, intList.getLength());
			}
		} while (userInput != 0);
		
		scanner.close();
		
		intList.traverse(System.out::println);
	}
}
