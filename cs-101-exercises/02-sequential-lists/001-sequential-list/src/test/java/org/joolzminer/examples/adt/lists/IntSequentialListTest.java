package org.joolzminer.examples.adt.lists;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


/**
 * Verifies SequentialList implementation of List
 * 
 * @author sergio.f.gonzalez
 *
 */
public class IntSequentialListTest {
	
	// List creation
	
	/**
	 * Create IntList
	 * 
	 * pre: none
	 * post: an empty list is created
	 * 
	 */
	@Test
	public void testCreate() {
		IntList intList = new IntSequentialList();
		assertThat(intList, is(notNullValue()));
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
	

	/**
	 * Create IntList with initialSize
	 * 
	 * pre: none
	 * post: an empty list is created
	 * 
	 */
	@Test
	public void testCreateWithSize() {
		IntList intList = new IntSequentialList(100);
		assertThat(intList, is(notNullValue()));
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
	
	/**
	 * Create IntList with size 0
	 * 
	 * pre: none
	 * post: an exception is thrown
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCreateWithSizeZero() {
		new IntSequentialList(0);
	}
	
	/**
	 * Create IntList with negative size
	 * 
	 * pre: none
	 * post: an exception is thrown
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCreateWithNegativeSize() {
		new IntSequentialList(-1);	
	}
	
	
	// Checking if List is empty
	
	/**
	 *  Checking listEmpty with with an empty list 
	 */
	@Test
	public void testIsEmptyWithEmptyList() {
		IntList intList = new IntSequentialList();
		
		assertThat(intList.isEmpty(), is(equalTo(true)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
	
	/**
	 *  Checking listEmpty with with non empty list 
	 */
	@Test
	public void testIsEmptyWithNonEmptyList() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		
		assertThat(intList.isEmpty(), is(equalTo(false)));
		assertThat(intList.toString(), is(equalTo("[1000]")));
	}
	
	/**
	 *  Checking listEmpty with with non empty list with several 
	 */
	@Test
	public void testIsEmptyWithNonEmptyListWithSeveralElements() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		intList.add(1_002, 2);
		
		assertThat(intList.isEmpty(), is(equalTo(false)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	
	// Adding Elements to a List
	
	/**
	 * Add first elem to empty IntList
	 * 
	 *  pre: list is created and empty
	 *  post: lenght of the list is 1
	 */
	@Test
	public void testAddFirstElemToEmptyList() {
		IntList intList = new IntSequentialList();
		
		intList.add(1_000, 0);
		assertThat(intList.getLength(), is(equalTo(1)));
		assertThat(intList.toString(), is(equalTo("[1000]")));
	}
	
	/**
	 * Add elem to empty IntList in invalid position
	 * 
	 *  pre: list is created and empty
	 *  post: n/a an exception is thrown
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddElemToEmptyListInInvalidPos() {
		IntList intList = new IntSequentialList();
		
		intList.add(1_000, 1);
	}
	
	/**
	 * Add elem to empty IntList in invalid position (negative)
	 * 
	 *  pre: list is created and empty
	 *  post: n/a an exception is thrown
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddElemToEmptyListInInvalidPosNegative() {
		IntList intList = new IntSequentialList();
		
		intList.add(1_000, -1);
	}
	
	/**
	 * Add elem to a non empty IntList in intermediate position w/o inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInIntermediatePosNoInflating() {
		IntList intList = new IntSequentialList();		
		intList.add(1_000, 0);
		intList.add(1_002, 1);
		int prevLen = intList.getLength();
		
		intList.add(1_001, 1);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	/**
	 * Add elem to a non empty IntList in first position w/o inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInFirstPosNoInflating() {
		IntList intList = new IntSequentialList();		
		intList.add(1_001, 0);
		intList.add(1_002, 1);
		int prevLen = intList.getLength();
		
		intList.add(1_000, 0);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	/**
	 * Add elem to a non empty IntList in last position w/o inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInLastPosNoInflating() {
		IntList intList = new IntSequentialList();		
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		int prevLen = intList.getLength();
		
		intList.add(1_002, 2);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	// Forcing inflating on the list because avail space has exhausted

	/**
	 * Add elem to a non empty IntList in first position w inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInFirstPosInflating() {
		IntList intList = new IntSequentialList(2);		
		intList.add(1_001, 0);
		intList.add(1_002, 1);
		int prevLen = intList.getLength();
		
		intList.add(1_000, 0);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	
	/**
	 * Add elem to a non empty IntList in intermediate position w/o inflating
	 * 
	 */
	@Test
	public void testAddElemToNonEmptyListInIntermediatePosWithInflating() {
		IntList intList = new IntSequentialList(2);		
		intList.add(1_000, 0);
		intList.add(1_002, 1);
		int prevLen = intList.getLength();
		
		intList.add(1_001, 1);

		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	

	
	/**
	 * Add elem to a non empty IntList in last position with inflating
	 * 
	 */
	@Test
	public void testAddElemToNonEmptyListInLastPosWithInflating() {
		IntList intList = new IntSequentialList(2);		
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		int prevLen = intList.getLength();
		
		intList.add(1_002, 2);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}		
	
	// Remove
	
	/**
	 * Try to remove elements from an empty IntList
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveElemFromEmptyList() {
		IntList intList = new IntSequentialList();
		intList.remove(0);
	}
	
	/**
	 * Try to remove elements from a non empty IntList in Invalid position
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveElemFromNonEmptyListInvalidPos() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		
		intList.remove(2);
	}
	
	/**
	 * Try to remove elements from a non empty IntList in Invalid position (neg)
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveElemFromNonEmptyListInvalidPosNegative() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		
		intList.remove(-1);
	}
	
	/**
	 * Try to remove elements from a List with one element first element
	 */
	@Test
	public void testRemoveElemFromListWithOneElemFirstElem() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		
		intList.remove(0);
		
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}	
	
	
	/**
	 * Try to remove elements from a List with several elements first element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsFirstElem() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		
		intList.remove(0);
		
		assertThat(intList.getLength(), is(equalTo(1)));
		assertThat(intList.toString(), is(equalTo("[1001]")));
	}
	
	/**
	 * Try to remove elements from a List with several elements first element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsFirstElem1() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		intList.add(1_002, 2);
		
		intList.remove(0);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1001, 1002]")));
	}	
	
	/**
	 * Try to remove elements from a List with several elements intermediate element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsIntermediateElem() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		intList.add(1_002, 2);
		
		intList.remove(1);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1000, 1002]")));
	}
	
	/**
	 * Try to remove elements from a List with several elements last element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsLastElem() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		intList.add(1_002, 2);
		
		intList.remove(2);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001]")));
	}
	
	// Length of the list
	/**
	 * Get length of empty list
	 */
	@Test
	public void testGetLengthOfEmptyList() {
		IntList intList = new IntSequentialList();
		
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
	
	/**
	 * Get length of list with one elem
	 */
	@Test
	public void testGetLengthOfListWithOneElem() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		
		assertThat(intList.getLength(), is(equalTo(1)));
		assertThat(intList.toString(), is(equalTo("[1000]")));
	}
	
	/**
	 * Get length of list with several elem
	 */
	@Test
	public void testGetLengthOfListWithSeveralElem() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001]")));
	}	
	
	
	// -- traverse
	
	/**
	 * Traverse and join in a string
	 */
	@Test
	public void testTraverseCase1() {
		IntList intList = new IntSequentialList();
		intList.add(1_000, 0);
		intList.add(1_001, 1);
		intList.add(1_002, 2);
		
		StringBuilder sb = new StringBuilder();
		
		intList.traverse(elem -> sb.append(elem));
		
		assertThat(intList.getLength(), is(equalTo(3)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
		assertThat(sb.toString(), is(equalTo("100010011002")));
	}
}
