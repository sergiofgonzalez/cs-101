package org.joolzminer.examples.adt.recursion.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TowerOfHanoiRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(TowerOfHanoiRunner.class);
	
	private enum PEG {
		A('A'),
		B('B'),
		C('C');
	
		private char value;
		
		private PEG(char value) {
			this.value = value;
		}
		
		@Override
		public String toString() {			
			return "" + this.value;
		}
		
	};
	
	
	public static void main(String[] args) {
		int numDiscs = 20;
		solveTowerOfHanoi(numDiscs, PEG.A, PEG.C, getPegsWithInitialArrangement(numDiscs));
	}
	
	private static final Map<PEG, List<Integer>> getPegsWithInitialArrangement(int numDiscs) {
		Map<PEG, List<Integer>> pegs = new TreeMap<>();
		pegs.put(PEG.A, new ArrayList<Integer>());
		pegs.put(PEG.B, new ArrayList<Integer>());
		pegs.put(PEG.C, new ArrayList<Integer>());
		
		for (int i = numDiscs; i > 0; i--) {
			pegs.get(PEG.A).add(i);
		}
		
		System.out.println(pegs);
		
		return pegs;
	}
	
	public static void solveTowerOfHanoi(int numDiscs, PEG orig, PEG dest, Map<PEG, List<Integer>> pegs) {
		if (numDiscs >= 1) {
			solveTowerOfHanoi(numDiscs - 1, orig, getDestination(orig, dest), pegs);
			moveDisc(orig, dest, pegs);
			solveTowerOfHanoi(numDiscs - 1, getDestination(orig, dest), dest, pegs);
		}
	}
		
	
	private static void moveDisc(PEG origin, PEG destination, Map<PEG, List<Integer>> pegs) {
		int originSize = pegs.get(origin).size();
		if (pegs.get(origin).size() == 0) {
			LOGGER.error("Cannot move from empty peg: origin={}; destination={}, status={}", origin, destination, pegs);
			throw new IllegalArgumentException("Cannot move from empty peg");
		}
		
		
		Integer discFromOrigin = pegs.get(origin).get(originSize - 1);
		
		int destinationSize = pegs.get(destination).size();
		if (destinationSize > 0) {
			Integer discFromDestination = pegs.get(destination).get(destinationSize - 1);
			if (discFromDestination < discFromOrigin) {
				LOGGER.error("Cannot place disc={} on top of smaller disc={}; origin={}; destination={}, status={}", discFromOrigin, discFromDestination, origin, destination, pegs);
				throw new IllegalArgumentException("Cannot place disc on top of smaller disc");
			}
		}
		 
		LOGGER.debug("Moving disc: origin={}; destination={}, status={}", origin, destination, pegs);
		pegs.get(origin).remove(originSize - 1);  
		pegs.get(destination).add(discFromOrigin);
		
		System.out.println(pegs);
	}
	
	@SuppressWarnings("serial")
	private static PEG getDestination(PEG prevOrig, PEG prevDest) {
		List<PEG> possibleDestinations = new ArrayList<PEG>(3) {{
			add(PEG.A);
			add(PEG.B);
			add(PEG.C);
		}};
		
		possibleDestinations.remove(prevOrig);
		possibleDestinations.remove(prevDest);
		
		return possibleDestinations.get(0); 
	}
}
