package org.joolzminer.examples.runners;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearchRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BinarySearchRunner.class);
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("list=" + list);

		Optional<Integer> index = binarySearch(3, list);
		if (index.isPresent()) {
			System.out.println("search for 3=" + index.get());
		} else {
			System.out.println("search for 3= NOT FOUND" + binarySearch(3, list).get());
		}
		printSeparator();
		
		index = binarySearch(-1, list);
		if (index.isPresent()) {
			System.out.println("search for -1=" + index.get());
		} else {
			System.out.println("search for -1= NOT FOUND" + binarySearch(3, list).get());
		}
		printSeparator();
		
		index = binarySearch(1, list);
		if (index.isPresent()) {
			System.out.println("search for 1=" + index.get());
		} else {
			System.out.println("search for 3= NOT FOUND" + binarySearch(1, list).get());
		}
		printSeparator();
		
		index = binarySearch(8, list);
		if (index.isPresent()) {
			System.out.println("search for 8=" + index.get());
		} else {
			System.out.println("search for 8= NOT FOUND" + binarySearch(8, list).get());
		}
		printSeparator();
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("binSearch(" + i + ")=" + binarySearch(i, list).get());
		}
		printSeparator();
		
		list = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18);
		for (int i = 1; i < 20; i++) {
			System.out.println("binSearch(" + i + ")=" + binarySearch(i, list));
		}
	}
	
	public static <T extends Comparable<T>> Optional<Integer> binarySearch(T elem, List<T> list) {
		int i = 0;
		int j = list.size() - 1;
		int k = (j - i) / 2;
		LOGGER.debug("i={}; j={}; k={}", i, j, k);
		while ((i < j) && (elem != list.get(k))) {						
			if (elem.compareTo(list.get(k)) < 0) {
				j = k;
				k = i + (j - i) / 2;
			} else {
				i = k + 1;
				k = i + (j - i) / 2;
			}
			LOGGER.debug("i={}; j={}; k={}", i, j, k);
		}
		
		Optional<Integer> resultIndex;
		if (elem.equals(list.get(k))) {
			resultIndex = Optional.of(k);
		} else {
			resultIndex = Optional.empty();
		}
		
		return resultIndex;
	}	
	
	public static void printSeparator() {
		System.out.println("======================================");
	}
}
