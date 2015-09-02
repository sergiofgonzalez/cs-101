package org.joolzminer.examples.adt.recursion.runners;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import static org.joolzminer.examples.adt.recursion.functions.Fibonacci.*;

/**
 * Verifies Fibonacci sequence recursive implementation
 * 
 * @author sergio.f.gonzalez
 *
 */
public class FibonacciTest {
		
	
	/**
	 * Negative Argument
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeArgument() {
		fibonacci(-1);
	}
		
	
	/**
	 * Argument 0
	 */
	@Test
	public void testArgumentZero() {
		assertThat(fibonacci(0), is(equalTo(0)));
	}
	
	/**
	 * Argument 1: first recursive call
	 */
	@Test
	public void testArgumentOne() {
		assertThat(fibonacci(1), is(equalTo(1)));
	}
	
	/**
	 * Argument 2: second recursive call
	 */
	@Test
	public void testArgumentTwo() {
		assertThat(fibonacci(2), is(equalTo(1)));
	}
	
	/**
	 * Argument 5: several recursive calls
	 */
	@Test
	public void testArgumentFive() {
		assertThat(fibonacci(5), is(equalTo(5)));
	}
	
	
	
}
