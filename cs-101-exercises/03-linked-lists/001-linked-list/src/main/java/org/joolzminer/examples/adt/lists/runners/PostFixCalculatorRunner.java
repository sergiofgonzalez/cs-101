package org.joolzminer.examples.adt.lists.runners;

import java.util.Scanner;

import org.joolzminer.examples.adt.stacks.IntArrayBackedStack;
import org.joolzminer.examples.adt.stacks.IntStack;

public class PostFixCalculatorRunner {
	
	public static void main(String[] args) {
		IntStack stack = new IntArrayBackedStack();
		
		System.out.println("PostFix calculator (type 'q' to Quit)");
		
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		while (!userInput.equalsIgnoreCase("q")) {
			System.out.println(stack);
			System.out.print("> ");
			userInput = scanner.next();			
			
			switch (userInput) {
				case "+" :
					stack.push(stack.pop() + stack.pop());
					break;
				
				case "*" :
					stack.push(stack.pop() * stack.pop());
					break;
					
				case "-" :
					stack.push(-1 * stack.pop() + stack.pop());
					break;
					
				case "/" :
					int op2 = stack.pop();
					stack.push(stack.pop() / op2);
					break;

				case "Q" :
				case "q" :
					break;
				
				case "C" :
				case "c" :
					while (!stack.isEmpty()) {
						stack.pop();
					}
					break;
					
				default :
					try {
						stack.push(Integer.parseInt(userInput));
					} catch (NumberFormatException e) {
						System.out.println("Unrecognized input: number or '+', '-', '*', '/' expected; 'c' to clear, 'q' to Quit");
					}										
			}
			System.out.println("==================================\n");			
		}	
		
		System.out.println("done!!!");
		scanner.close();
	}
}
