package org.joolzminer.examples.adt.stacks;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class DynamicLinkedStackTest {

	/****************************** creation ****************/
	
	@Test
	public void testStackCreationNoSize() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		
		assertThat(intStack, is(notNullValue()));
		assertThat(intStack.isEmpty(), is(true));
	}
	
	
	/****************************** isEmpty ****************/
	
	@Test
	public void testStackIsEmpty() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		
		assertThat(intStack.isEmpty(), is(true));		
	}
	
	@Test
	public void testStackIsNotEmpty() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		intStack.push(1_000);
		
		assertThat(intStack.isEmpty(), is(false));		
	}
	
	/****************************** push ****************/
	
	@Test
	public void testStackPushNoInflate() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		intStack.push(1_000);
		
		assertThat(intStack.isEmpty(), is(false));
		assertThat(intStack.pop(), is(equalTo(1_000)));
	}
	
	@Test
	public void testStackPushInflate() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		intStack.push(1_000);
		intStack.push(1_001);
		
		assertThat(intStack.isEmpty(), is(false));
		assertThat(intStack.pop(), is(equalTo(1_001)));
	}
	
	/****************************** pop ****************/
	
	@Test(expected=IllegalStateException.class)
	public void testStackPopOnEmptyList() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		
		intStack.pop();	
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStackPopOnBeyondEmptyStack() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		intStack.push(1_000);
		
		intStack.pop();
		intStack.pop();
	}

	@Test(expected=IllegalStateException.class)
	public void testStackPopOnBeyondEmptyStackSeveralElem() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
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
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		intStack.push(1_000);
				
		int topElem = intStack.pop();
		
		assertThat(intStack.isEmpty(), is(true));
		assertThat(topElem, is(equalTo(1000)));
	}
	
	@Test
	public void testStackPopUntilEmptySeveralElem() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
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
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		
		assertThat(intStack.toString(), is(equalTo("0: ")));
	}
	
	@Test
	public void testToStringStackOneElem() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		intStack.push(1_000);
		
		assertThat(intStack.toString(), is(equalTo("0: 1000")));
	}
	
	@Test
	public void testToStringStackTwoElems() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
		intStack.push(1_000);
		intStack.push(1_001);
		
		String stackStr = 	"1: 1000\n" +
							"0: 1001";
		
		assertThat(intStack.toString(), is(equalTo(stackStr)));
	}
	
	@Test
	public void testToStringStackSeveralElems() {
		Stack<Integer> intStack = new DynamicLinkedStack<>();
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
