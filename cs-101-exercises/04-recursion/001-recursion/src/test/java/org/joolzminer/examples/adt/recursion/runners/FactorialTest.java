package org.joolzminer.examples.adt.recursion.runners;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import static org.joolzminer.examples.adt.recursion.functions.Factorial.*;

/**
 * Verifies Recursive Factorial implementation
 * 
 * @author sergio.f.gonzalez
 *
 */
public class FactorialTest {
		
	
	/**
	 * Negative Argument
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeArgument() {
		factorial(-1);
	}
		
	
	/**
	 * Argument 0
	 */
	@Test
	public void testArgumentZero() {
		assertThat(factorial(0), is(equalTo(1L)));
	}
	
	/**
	 * Argument 1: first recursive call
	 */
	@Test
	public void testArgumentOne() {
		assertThat(factorial(1), is(equalTo(1L)));
	}
	
	/**
	 * Argument 2: second recursive call
	 */
	@Test
	public void testArgumentTwo() {
		assertThat(factorial(2), is(equalTo(2L)));
	}
	
	/**
	 * Argument 5: several recursive calls
	 */
	@Test
	public void testArgumentFive() {
		assertThat(factorial(5), is(equalTo(120L)));
	}
	
}
