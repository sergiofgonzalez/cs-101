package org.joolzminer.examples.adt.recursion.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fibonacci {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Fibonacci.class);
	
	public static int fibonacci(int n) {
		if (n < 0) {
			LOGGER.error("Bad argument: n should be positive, but was {}", n);
			throw new IllegalArgumentException("Bad argument");
		} else {
			return fib(n);
		}
	}
	
	private static int fib(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fib(n-1) + fib(n-2);
		}
	}
}
