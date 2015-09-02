package org.joolzminer.examples.adt.recursion.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Factorial {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Factorial.class);
	
	public static long factorial(int n) {
		if (n < 0) {
			LOGGER.error("Bad argument: factorial is not defined for negative numbers: n={}", n);
			throw new IllegalArgumentException("Bad argument: negative number received");
		}
		return recursiveFactorial(n);
	}
	
	private static long recursiveFactorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * recursiveFactorial(n - 1);
		}
	}
}
