package org.joolzminer.examples.adt.recursion.runners;

public class LabyrinthSolver {
	
	private static final char WALL = 'W';
	private static final char START = 'S';
	private static final char EXIT = 'E';
	private static final char VISITED_PATH = 'X';
	private static final char EXIT_FOUND = '⓿';
	
	
	private static char[][] labyrinth = 
		{ 
			{'S', ' ', 'W', ' ', ' ', ' ', ' ', ' '},
			{'W', ' ', 'W', ' ', 'W', ' ', 'W', ' '},
			{'W', ' ', 'W', ' ', 'W', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', 'W', 'W', 'E', 'W'},
		};
	
	private static char[][] labyrinth2 = 
		{ 
			{'S', ' ', ' ', ' ', 'W', 'W', 'W', ' ', ' ', 'W', ' ', ' '},
			{' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', ' ', 'W', ' ', 'W'},
			{'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'W', ' ', ' ', ' ', 'W', ' ', ' ', 'W', ' ', ' ', 'W', ' '},
			{'W', ' ', ' ', ' ', 'W', 'W', 'W', ' ', ' ', ' ', 'W', ' '},
			{' ', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' '},
			{' ', 'W', 'W', 'W', ' ', ' ', 'W', 'W', 'W', 'W', ' ', 'W'},
			{' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', 'W', ' ', ' '},
			{' ', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'E'},
		};
	
	private static char[][] labyrinth3 = 
		{ 
			{'S', ' ', ' ', ' ', 'W', 'W', 'W', ' ', ' ', 'W', ' ', ' '},
			{' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', ' ', 'W', ' ', 'W'},
			{'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'W', ' ', ' ', ' ', 'W', ' ', ' ', 'W', ' ', ' ', 'W', ' '},
			{'W', ' ', ' ', ' ', 'W', 'W', 'W', ' ', ' ', ' ', 'W', ' '},
			{' ', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' '},
			{' ', 'W', 'W', 'W', ' ', ' ', 'W', 'W', 'W', 'W', ' ', 'W'},
			{' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', 'W', ' ', ' '},
			{' ', 'W', ' ', 'W', 'W', 'W', ' ', ' ', ' ', 'W', 'W', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', 'W', 'E'},
		};
	
	public static void main(String[] args) throws Exception {
		
		// Easy one, with solution
		prettyPrint(labyrinth);
		findLabyrinthExit(labyrinth, 0, 0);
		prettyPrint(labyrinth);
		
		// More complicated one with solution
		prettyPrint(labyrinth2);
		findLabyrinthExit(labyrinth2, 0, 0);
		prettyPrint(labyrinth2);
		
		// More complicated one without solution
		prettyPrint(labyrinth3);
		findLabyrinthExit(labyrinth3, 0, 0);
		prettyPrint(labyrinth3);		
	}	
	
	private static boolean findLabyrinthExit(char[][] labyrinth, int row, int col) {
		if (row < 0 || row >= labyrinth.length || col < 0 || col >= labyrinth[0].length) {
			return false;
		} else if (EXIT == labyrinth[row][col]) {
			labyrinth[row][col] = EXIT_FOUND;
			return true;
		} else if (WALL == labyrinth[row][col] || VISITED_PATH == labyrinth[row][col]) {
			return false;
		} else {
			if (START != labyrinth[row][col]) {
				labyrinth[row][col] = VISITED_PATH;
				prettyPrint(labyrinth);
				delay();
			}
			return findLabyrinthExit(labyrinth, row - 1, col) ||
					findLabyrinthExit(labyrinth, row, col + 1) ||
					findLabyrinthExit(labyrinth, row + 1, col) ||
					findLabyrinthExit(labyrinth, row, col - 1);
		}
	}
	
	/**
	 * 		┌─┬─┬─┬─┬─┬─┬─┐
	 * 		│S│ │▓│ │ │ │▓│
	 * 		├─┼─┼─┼─┼─┼─┼─┤
	 *      │E│ │▓│ │ │ │▓│
	 *      └─┴─┴─┴─┴─┴─┴─┘
	 */
	public static void prettyPrint(char[][] labyrinth) {
		int rows = labyrinth.length;
		int cols = labyrinth[0].length;
		
		for (int col = 0; col < cols; col++) {
			if (col == 0) {
				System.out.print('┌');
			} else {
				System.out.print('┬');
			}
			System.out.print('─');
		}		
		System.out.println('┐');
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				System.out.print('│');
				System.out.print((WALL == labyrinth[row][col])? '▓' : labyrinth[row][col]);
			}
			System.out.println('│');
			
			if (row < rows -1) {
				for (int col = 0; col < cols; col++) {
					if (col == 0) {
						System.out.print('├');	
					} else {
						System.out.print('┼');
					}
					System.out.print('─');
				}
				System.out.println('┤');			
			}
		}
		
		for (int col = 0; col < cols; col++) {
			if (col == 0) {
				System.out.print('└');
			} else {
				System.out.print('┴');
			}
			System.out.print('─');
		}		
		System.out.println('┘');		
	}


	private static void delay() {
		try {
			Thread.sleep(750L);
		} catch (InterruptedException e) {
			// swallow
		}
	}

}
