package org.joolzminer.examples.adt.recursion.runners;

/**
 * Starting from number 1 and repeatedly either adding 5 or multiplying by 3,
 * this program finds the sequence of such additions and multiplications that
 * produce that number.
 * 
 * For example:
 * 		13 -> 1 * 3 + 5 + 5
 * 		15 -> cannot be reached
 * @author sergio.f.gonzalez
 *
 */
public class RecursionPuzzleRunner {
	
	public static void main(String[] args) {
		
		System.out.println(findPuzzleSolution(13));
		System.out.println(findPuzzleSolution(15));
		System.out.println(findPuzzleSolution(154));
		
	}
	
	private static String findPuzzleSolution(int number) {		
		return find(number, 1, "1");
	}
	
	private static String find(int number, int current, String operationsHistory) {
		if (current == number) {
			return operationsHistory;
		} else if (current > number) {
			return null;
		} else {
			String result = find(number, current + 5, "(" + operationsHistory + " + 5)");
			if (result == null) {
				return find(number, current * 3, "(" + operationsHistory + " * 3)");
			} else {
				return result;
			}
		}
	}	
}
