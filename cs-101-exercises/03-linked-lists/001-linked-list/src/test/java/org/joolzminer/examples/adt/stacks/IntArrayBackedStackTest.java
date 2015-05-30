package org.joolzminer.examples.adt.stacks;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class IntArrayBackedStackTest {

	/****************************** creation ****************/
	
	@Test
	public void testStackCreationNoSize() {
		IntStack intStack = new IntArrayBackedStack();
		
		assertThat(intStack, is(notNullValue()));
		assertThat(intStack.isEmpty(), is(true));
	}
	
	@Test
	public void testStackCreationWithInitialSize() {
		IntStack intStack = new IntArrayBackedStack(5);
		
		assertThat(intStack, is(notNullValue()));
		assertThat(intStack.isEmpty(), is(true));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testStackCreationWithNegativeSize() {
		new IntArrayBackedStack(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStackCreationWithZeroSize() {
		new IntArrayBackedStack(0);
	}
	
	/****************************** isEmpty ****************/
	
	@Test
	public void testStackIsEmpty() {
		IntStack intStack = new IntArrayBackedStack();
		
		assertThat(intStack.isEmpty(), is(true));		
	}
	
	@Test
	public void testStackIsNotEmpty() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		
		assertThat(intStack.isEmpty(), is(false));		
	}
	
	/****************************** push ****************/
	
	@Test
	public void testStackPushNoInflate() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		
		assertThat(intStack.isEmpty(), is(false));
		assertThat(intStack.pop(), is(equalTo(1_000)));
	}
	
	@Test
	public void testStackPushInflate() {
		IntStack intStack = new IntArrayBackedStack(1);
		intStack.push(1_000);
		intStack.push(1_001);
		
		assertThat(intStack.isEmpty(), is(false));
		assertThat(intStack.pop(), is(equalTo(1_001)));
	}
	
	/****************************** pop ****************/
	
	@Test(expected=IllegalStateException.class)
	public void testStackPopOnEmptyList() {
		IntStack intStack = new IntArrayBackedStack();
		
		intStack.pop();	
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStackPopOnBeyondEmptyStack() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		
		intStack.pop();
		intStack.pop();
	}

	@Test(expected=IllegalStateException.class)
	public void testStackPopOnBeyondEmptyStackSeveralElem() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		intStack.push(1_001);
		intStack.push(1_002);
		
		intStack.pop();
		intStack.pop();
		intStack.pop();
		intStack.pop();
	}
	
	@Test
	public void testStackPopUntilEmptyOneElem() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
				
		int topElem = intStack.pop();
		
		assertThat(intStack.isEmpty(), is(true));
		assertThat(topElem, is(equalTo(1000)));
	}
	
	@Test
	public void testStackPopUntilEmptySeveralElem() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		intStack.push(1_001);
		intStack.push(1_002);
						
		int[] elems = new int[] { intStack.pop(), intStack.pop(), intStack.pop() };
		assertThat(intStack.isEmpty(), is(true));
		assertThat(elems[2], is(equalTo(1_000)));
		assertThat(elems[1], is(equalTo(1_001)));
		assertThat(elems[0], is(equalTo(1_002)));
	}
	
	/****************************** toString ****************/
	
	@Test
	public void testToStringEmptyStack() {
		IntStack intStack = new IntArrayBackedStack();
		
		assertThat(intStack.toString(), is(equalTo("0: ")));
	}
	
	@Test
	public void testToStringStackOneElem() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		
		assertThat(intStack.toString(), is(equalTo("0: 1000")));
	}
	
	@Test
	public void testToStringStackTwoElems() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		intStack.push(1_001);
		
		String stackStr = 	"1: 1000\n" +
							"0: 1001";
		
		assertThat(intStack.toString(), is(equalTo(stackStr)));
	}
	
	@Test
	public void testToStringStackSeveralElems() {
		IntStack intStack = new IntArrayBackedStack();
		intStack.push(1_000);
		intStack.push(1_001);
		intStack.push(1_002);
		intStack.push(1_003);
		
		String stackStr = 	"3: 1000\n" +
							"2: 1001\n" +
							"1: 1002\n" +
							"0: 1003";
		
		assertThat(intStack.toString(), is(equalTo(stackStr)));
	}
}
