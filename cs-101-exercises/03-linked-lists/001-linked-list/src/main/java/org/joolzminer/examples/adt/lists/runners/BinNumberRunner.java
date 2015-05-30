package org.joolzminer.examples.adt.lists.runners;

import org.joolzminer.examples.adt.stacks.IntArrayBackedStack;
import org.joolzminer.examples.adt.stacks.IntStack;

public class BinNumberRunner {
	
	public static void main(String[] args) {
		for (int i = 0; i < 32; i++) {
			System.out.println(i + " = " + decToBin(i));
		}
	}
	
	public static String decToBin(int number) {
		IntStack stack = new IntArrayBackedStack();
		
		stack.push(number);
		int num;
		do {
			num = stack.pop();
			stack.push(num % 2);
			stack.push(num / 2);
		} while (num / 2 != 0);
		
		stack.pop();
		
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
}
