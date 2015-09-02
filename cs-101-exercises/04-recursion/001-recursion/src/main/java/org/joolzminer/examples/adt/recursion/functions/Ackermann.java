package org.joolzminer.examples.adt.recursion.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ackermann {
	private static final Logger LOGGER = LoggerFactory.getLogger(Ackermann.class);
	
	public static int ackermann(int m, int n) {
		if (m < 0 || n < 0) {
			LOGGER.error("Bad arguments: m and n should be non-negative but m={} and n={}", m, n);
			throw new IllegalArgumentException("Bad arguments");
		}
		return a(m, n);
	}
	
	private static int a(int m, int n) {
		if (m == 0) {
			return n + 1;			
		} else if (n == 0) {
			return a(m - 1, 1);
		} else {
			return a(m - 1, a(m, n - 1));
		}		
	}
}
