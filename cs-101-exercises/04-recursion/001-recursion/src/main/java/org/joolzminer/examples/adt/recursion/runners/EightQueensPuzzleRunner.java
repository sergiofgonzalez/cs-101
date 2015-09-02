package org.joolzminer.examples.adt.recursion.runners;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EightQueensPuzzleRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(EightQueensPuzzleRunner.class);
	
	public static void main(String[] args) {
		List<Integer> queenCols = new ArrayList<>();
		 queenCols = findSolution(queenCols, 8);
		 if (queenCols != null) {
			 System.out.println("A solution was found: " + queenCols);
			 prettyPrintSolution(queenCols);
		 } else {
			 System.out.println("No solution was found");
		 }
	}
	
	@SuppressWarnings("serial")
	private static List<Integer> findSolution(List<Integer> queenCols, int numQueens) {
		if (!isValidArrangement(queenCols)) {			
			return null;
		} else if (queenCols.size() == numQueens) {
			return queenCols;
		} else {
			List<Integer> solution = null;
			for (int i = 0; i < 8 && solution == null; i++) {	
				final int candidatePos = i;
				solution = findSolution(new ArrayList<Integer>(queenCols) {{
					add(candidatePos); 
				}}, numQueens);
			}
			return solution;
		}
	}
	
	
	private static boolean isValidArrangement(List<Integer> queenCols) {
		boolean isEaten = false;
		if (queenCols.size() > 1) {
			int candidateCol = queenCols.get(queenCols.size() - 1);
			LOGGER.debug("queenCols={}; queenCols.size={}", queenCols, queenCols.size()); 
			for (int i = 0; i < queenCols.size() - 1 && !isEaten; i++) {
				int vertical = queenCols.get(i);
				int diagonalRight = queenCols.get(i) + (queenCols.size() - 1 - i);
				int diagonalLeft = queenCols.get(i) - (queenCols.size() - 1- i);
				LOGGER.debug("i={}, vertical={}, diagonalRight={}, diagonalLeft={}", i, vertical, diagonalRight, diagonalLeft);
				isEaten = candidateCol == queenCols.get(i) ||
							candidateCol == queenCols.get(i) + (queenCols.size() - 1 - i) ||
							candidateCol == queenCols.get(i) - (queenCols.size() - 1 - i);
			}
		}
		return !isEaten;
	}
	
	
	/**
	 * ┌─┬─┬─┬─┬─┬─┬─┬─┐
     * │♕
	 * @param queenCols
	 */
	private static void prettyPrintSolution(List<Integer> queenCols) {
		for (int col = 0; col < 8; col++) {
			if (col == 0) {
				System.out.print('┌');
			} else {
				System.out.print('┬');
			}
			System.out.print("──");
		}		
		System.out.println('┐');
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				System.out.print('│');
				System.out.print(row < queenCols.size() && queenCols.get(row) == col? "▓▓" : "  ");
			}
			System.out.println('│');
			
			if (row < 7) {
				for (int col = 0; col < 8; col++) {
					if (col == 0) {
						System.out.print('├');	
					} else {
						System.out.print('┼');
					}
					System.out.print("──");
				}
				System.out.println('┤');			
			}
		}
		
		for (int col = 0; col < 8; col++) {
			if (col == 0) {
				System.out.print('└');
			} else {
				System.out.print('┴');
			}
			System.out.print("──");
		}		
		System.out.println('┘');		
	}
}
