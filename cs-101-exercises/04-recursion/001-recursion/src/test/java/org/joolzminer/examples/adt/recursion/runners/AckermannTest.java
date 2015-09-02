package org.joolzminer.examples.adt.recursion.runners;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import static org.joolzminer.examples.adt.recursion.functions.Ackermann.*;

/**
 * Verifies Ackermann sequence recursive implementation
 * 
 * @author sergio.f.gonzalez
 *
 */
public class AckermannTest {
		
	
	/**
	 * Negative Argument for m
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeArgumentForM() {
		ackermann(-1, 2);
	}
	
	/**
	 * Negative Argument for n
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeArgumentForN() {
		ackermann(2, -1);
	}

	/**
	 * Negative Argument for m and n
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeArgumentForMandN() {
		ackermann(-2, -1);
	}
	
	
	/**
	 * Argument (0,0)
	 */
	@Test
	public void testArgumentZeroZero() {
		assertThat(ackermann(0, 0), is(equalTo(1)));
	}
	
	/**
	 * Argument (0,1)
	 */
	@Test
	public void testArgumentZeroOne() {
		assertThat(ackermann(0, 1), is(equalTo(2)));
	}
	
	/**
	 * Argument (0,2)
	 */
	@Test
	public void testArgumentZeroTwo() {
		assertThat(ackermann(0, 2), is(equalTo(3)));
	}
	
	/**
	 * Argument (1,0)
	 */
	@Test
	public void testArgumentOneZero() {
		assertThat(ackermann(1, 0), is(equalTo(2)));
	}
	
	/**
	 * Argument (2,0)
	 */
	@Test
	public void testArgumentTwoZero() {
		assertThat(ackermann(2, 0), is(equalTo(3)));
	}
	
	/**
	 * Argument (2,2)
	 */
	@Test
	public void testArgumentTwoTwo() {
		assertThat(ackermann(2, 2), is(equalTo(7)));
	}
}
