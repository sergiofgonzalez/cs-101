package org.joolzminer.examples.runners;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BubbleSortRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BubbleSortRunner.class);
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(5, 4, 3, 2, 1);
		System.out.println("before: " + numbers);
		bubbleSort(numbers);
		System.out.println("after : " + numbers);
	}
	
	
	public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
		int n = list.size();
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j > i; j--) {
				LOGGER.debug("i={}, j={}", i, j);
				if (list.get(j - 1).compareTo(list.get(j)) > 0) {
					swap(j - 1, j, list);
					LOGGER.debug("list={}", list);
				}
			}
		}
	}
	
	private static <T> void swap(int i, int j, List<T> list) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
}
