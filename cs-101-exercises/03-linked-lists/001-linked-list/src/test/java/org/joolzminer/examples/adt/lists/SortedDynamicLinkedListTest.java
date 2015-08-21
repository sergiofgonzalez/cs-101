package org.joolzminer.examples.adt.lists;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


/**
 * Verifies SortedDynamicLinkedList implementation of List
 * 
 * @author sergio.f.gonzalez
 *
 */
public class SortedDynamicLinkedListTest {
		
	// List creation
	
	/**
	 * Create List<Integer>
	 * 
	 * pre: none
	 * post: an empty list is created
	 * 
	 */
	@Test
	public void testCreate() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		assertThat(intList, is(notNullValue()));
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
		
	
	// Checking if List is empty
	
	/**
	 *  Checking listEmpty with with an empty list 
	 */
	@Test
	public void testIsEmptyWithEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		
		assertThat(intList.isEmpty(), is(equalTo(true)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
	
	/**
	 *  Checking listEmpty with with non empty list 
	 */
	@Test
	public void testIsEmptyWithNonEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		
		assertThat(intList.isEmpty(), is(equalTo(false)));
		assertThat(intList.toString(), is(equalTo("[1000]")));
	}
	
	/**
	 *  Checking listEmpty with with non empty list with several 
	 */
	@Test
	public void testIsEmptyWithNonEmptyListWithSeveralElements() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
		
		assertThat(intList.isEmpty(), is(equalTo(false)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	
	// Adding Elements to a List
	
	/**
	 * Add first elem to empty List<Integer>
	 * 
	 *  pre: list is created and empty
	 *  post: lenght of the list is 1
	 */
	@Test
	public void testAddFirstElemToEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		
		intList.add(1_000);
		assertThat(intList.getLength(), is(equalTo(1)));
		assertThat(intList.toString(), is(equalTo("[1000]")));
	}
	
	
	/**
	 * Add elem to a non empty List<Integer> in intermediate position w/o inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInIntermediatePosNoInflating() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_002);
		int prevLen = intList.getLength();
		
		intList.add(1_001);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	/**
	 * Add elem to a non empty List<Integer> in first position w/o inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInFirstPosNoInflating() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_001);
		intList.add(1_002);
		int prevLen = intList.getLength();
		
		intList.add(1_000);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	/**
	 * Add elem to a non empty List<Integer> in last position w/o inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInLastPosNoInflating() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_001);
		int prevLen = intList.getLength();
		
		intList.add(1_002);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	// Forcing inflating on the list because avail space has exhausted

	/**
	 * Add elem to a non empty List<Integer> in first position w inflating
	 * 
	 *  pre: list is created and empty, list has available space
	 *  post: 	element is inserted in given position
	 *  		elements before the inserted are preserved
	 *  		elements after the inserted one are shifted right
	 *  		list length is incremented in one
	 */
	@Test
	public void testAddElemToNonEmptyListInFirstPosInflating() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_001);
		intList.add(1_002);
		int prevLen = intList.getLength();
		
		intList.add(1_000);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	
	/**
	 * Add elem to a non empty List<Integer> in intermediate position w/o inflating
	 * 
	 */
	@Test
	public void testAddElemToNonEmptyListInIntermediatePosWithInflating() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_002);
		int prevLen = intList.getLength();
		
		intList.add(1_001);

		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	

	
	/**
	 * Add elem to a non empty List<Integer> in last position with inflating
	 * 
	 */
	@Test
	public void testAddElemToNonEmptyListInLastPosWithInflating() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_001);
		int prevLen = intList.getLength();
		
		intList.add(1_002);
		assertThat(intList.getLength(), is(equalTo(prevLen + 1)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}		
	
	// Remove
	
	/**
	 * Try to remove elements from an empty List<Integer>
	 */
	@Test(expected=IllegalStateException.class)
	public void testRemoveElemFromEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.remove(0);
	}
	
	/**
	 * Try to remove elements from a List with one element first element
	 */
	@Test
	public void testRemoveElemFromListWithOneElemFirstElem() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		
		intList.remove(1_000);
		
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}	
	
	
	/**
	 * Try to remove elements from a List with several elements first element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsFirstElem() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		intList.add(1_001);
		
		intList.remove(1_000);
		
		assertThat(intList.getLength(), is(equalTo(1)));
		assertThat(intList.toString(), is(equalTo("[1001]")));
	}
	
	/**
	 * Try to remove elements from a List with several elements first element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsFirstElem1() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_002);
		intList.add(1_001);
		intList.add(1_000);
		
		
		
		intList.remove(1_000);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1001, 1002]")));
	}	
	
	/**
	 * Try to remove elements from a List with several elements intermediate element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsIntermediateElem() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		intList.add(1_002);
		intList.add(1_001);
		
		intList.remove(1_001);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1000, 1002]")));
	}
	
	/**
	 * Try to remove elements from a List with several elements last element
	 */
	@Test
	public void testRemoveElemFromListWithSeveralElemsLastElem() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_002);
		intList.add(1_000);
		intList.add(1_001);
		
		intList.remove(1_002);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001]")));
	}
	
	// Length of the list
	/**
	 * Get length of empty list
	 */
	@Test
	public void testGetLengthOfEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
	
	/**
	 * Get length of list with one elem
	 */
	@Test
	public void testGetLengthOfListWithOneElem() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		
		assertThat(intList.getLength(), is(equalTo(1)));
		assertThat(intList.toString(), is(equalTo("[1000]")));
	}
	
	/**
	 * Get length of list with several elem
	 */
	@Test
	public void testGetLengthOfListWithSeveralElem() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_001);
		intList.add(1_000);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001]")));
	}	
	
	
	// -- traverse
	
	/**
	 * Traverse and join in a string
	 */
	@Test
	public void testTraverseCase1() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_002);
		intList.add(1_001);
		intList.add(1_000);
		
		StringBuilder sb = new StringBuilder();
		
		intList.traverse(elem -> sb.append(elem));
		
		assertThat(intList.getLength(), is(equalTo(3)));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
		assertThat(sb.toString(), is(equalTo("100010011002")));
	}	
	
	/* Sorted list */
	
	// -- add
	
	/**
	 * Add element to empty list
	 */
	@Test
	public void testAddToEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		
		assertThat(intList.getLength(), is(equalTo(1)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1000]")));
	}
	
	/**
	 * Add element to non-empty list in first position
	 */
	@Test
	public void testAddToNonEmptyListInFirstPos() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_001);
		
		intList.add(1_000);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1000, 1001]")));
	}
	
	/**
	 * Add element to non-empty list in middle position
	 */
	@Test
	public void testAddToNonEmptyListInMiddlePos() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_002);		
		intList.add(1_000);
		
		intList.add(1_001);
		
		assertThat(intList.getLength(), is(equalTo(3)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	/**
	 * Add element to non-empty list in last position
	 */
	@Test
	public void testAddToNonEmptyListInLastPos() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_001);		
		intList.add(1_000);
		
		intList.add(1_002);
		
		assertThat(intList.getLength(), is(equalTo(3)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	// -- remove
	
	/**
	 * Remove from empty list
	 */
	@Test(expected = IllegalStateException.class)
	public void testRemoveFromEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		
		intList.remove(1_000);
	}
	
	/**
	 * Remove from list with one element
	 */
	@Test
	public void testRemoveFromListWithOneElement() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		
		intList.remove(1_000);
		
		assertThat(intList.getLength(), is(equalTo(0)));
		assertThat(intList.isEmpty(), is(true));
		assertThat(intList.toString(), is(equalTo("[]")));
	}
	
	/**
	 * Remove non-existing from list with one element
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveFromListWithOneElementNonExisting() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		
		intList.remove(1_001);
	}
	
	/**
	 * Remove from list with several elements: first
	 */
	@Test
	public void testRemoveFromListSeveralElementsFirst() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
		
		intList.remove(1_000);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1001, 1002]")));
	}
	
	/**
	 * Remove from list with several elements: middle
	 */
	@Test
	public void testRemoveFromListSeveralElementsMiddle() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
		intList.add(1_003);
		
		intList.remove(1_002);
		
		assertThat(intList.getLength(), is(equalTo(3)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1000, 1001, 1003]")));
	}
	
	/**
	 * Remove from list with several elements: middle2
	 */
	@Test
	public void testRemoveFromListSeveralElementsMiddle2() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
		
		intList.remove(1_001);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1000, 1002]")));
	}
	
	/**
	 * Remove from list with several elements: last
	 */
	@Test
	public void testRemoveFromListSeveralElementsLast() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
		
		intList.remove(1_002);
		
		assertThat(intList.getLength(), is(equalTo(2)));
		assertThat(intList.isEmpty(), is(false));
		assertThat(intList.toString(), is(equalTo("[1000, 1001]")));
	}
	
	/**
	 * Remove from list with several elements: non-existing middle
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveFromListSeveralElementsNonExistingMiddle() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_002);
		intList.add(1_004);
		
		intList.remove(1_001);
	}
	
	/**
	 * Remove from list with several elements: non-existing last
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveFromListSeveralElementsNonExistingLast() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_002);
		intList.add(1_004);
		
		intList.remove(1_005);
	}
	
	/**
	 * Remove from list with several elements: non-existing first
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveFromListSeveralElementsNonExistingFirst() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();		
		intList.add(1_000);
		intList.add(1_002);
		intList.add(1_004);
		
		intList.remove(999);
	}	
	
	// -- contains
	
	/**
	 * Contains in list with no elements 
	 */
	
	@Test
	public void testContainsInEmptyList() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		
				
		assertThat(intList.contains(1_000), is(equalTo(false)));
	}
	
	/**
	 * Contains in list with single element that is in the list 
	 */
	
	@Test
	public void testContainsInListWithSingleElementFound() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
				
		assertThat(intList.contains(1_000), is(equalTo(true)));
	}
	
	/**
	 * Contains in list with single element that is NOT in the list 
	 */
	
	@Test
	public void testContainsInListWithSingleElementNotFound() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
				
		assertThat(intList.contains(1_001), is(equalTo(false)));
	}	
	
	
	/**
	 * Contains in list with several elements that is in the middle of the list 
	 */
	
	@Test
	public void testContainsInListWithSingleElementsFoundInMiddle() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
				
		assertThat(intList.contains(1_001), is(equalTo(true)));
	}
	
	/**
	 * Contains in list with several elements that is in last position 
	 */
	
	@Test
	public void testContainsInListWithSingleElementsFoundInLastPos() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
				
		assertThat(intList.contains(1_002), is(equalTo(true)));
	}
	
	/**
	 * Contains in list with several elements that is NOT in the list 
	 */
	
	@Test
	public void testContainsInListWithSeveralElementsNotFound() {
		SortedList<Integer> intList = new SortedDynamicLinkedList<>();
		intList.add(1_000);
		intList.add(1_001);
		intList.add(1_002);
		
				
		assertThat(intList.contains(1_003), is(equalTo(false)));
	}	
	
}
