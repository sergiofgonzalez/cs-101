package org.joolzminer.examples.adt.recursion.structures;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

public class RecursiveListTest {
	
	// -- List creation
	
	/**
	 * Create empty list
	 */
	@Test
	public void testCreateEmptyList() {
		RecursiveList<Character> charList = RecursiveList.<Character> emptyList();
		
		assertThat(charList.isEmpty(), is(equalTo(true)));
		assertThat(charList.getLength(), is(equalTo(0)));
		assertThat(charList.toString(), is(equalTo("{}")));
	}
	
	/**
	 * Create list with one element
	 */
	@Test
	public void testCreateListSizeOne() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', RecursiveList.<Character> emptyList());
		
		
		assertThat(charList.isEmpty(), is(equalTo(false)));
		assertThat(charList.getLength(), is(equalTo(1)));
		assertThat(charList.toString(), is(equalTo("{ head: a, rest: NULL }")));
	}
	
	/**
	 * Create list with two elements
	 */
	@Test
	public void testCreateListSizeTwo() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
												RecursiveList.<Character> prepend('b', RecursiveList.<Character> emptyList()));
		
		
		assertThat(charList.isEmpty(), is(equalTo(false)));
		assertThat(charList.getLength(), is(equalTo(2)));
		assertThat(charList.toString(), is(equalTo("{ head: a, rest: { head: b, rest: NULL } }")));
	}
	
	/**
	 * Create list with three elements
	 */
	@Test
	public void testCreateListSizeThree() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
												RecursiveList.<Character> prepend('b', 
												RecursiveList.<Character> prepend('c', RecursiveList.<Character> emptyList())));
		
		
		assertThat(charList.isEmpty(), is(equalTo(false)));
		assertThat(charList.getLength(), is(equalTo(3)));
		assertThat(charList.toString(), is(equalTo("{ head: a, rest: { head: b, rest: { head: c, rest: NULL } } }")));
	}

	// -- isEmpty
	
	/**
	 * List is empty with empty List
	 */
	@Test
	public void testIsEmptyWithEmptyList() {
		RecursiveList<Character> charList = RecursiveList.<Character> emptyList();
		
		assertThat(charList.isEmpty(), is(equalTo(true)));
	}
	
	/**
	 * List is empty with non empty List
	 */
	@Test
	public void testIsEmptyWithNonEmptyList() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', RecursiveList.<Character> emptyList());
		
		assertThat(charList.isEmpty(), is(equalTo(false)));
	}	
	
	// - toArrayList
	
	/**
	 * toArrayList with empty list
	 */
	@Test
	public void testToArrayListWithEmptyList() {
		RecursiveList<Character> charList = RecursiveList.<Character> emptyList();
		
		ArrayList<Character> charArray = charList.toArrayList();
		
		assertThat(charArray.size(), is(equalTo(0)));
		assertThat(charArray.toString(), is(equalTo("[]")));
	}
	
	/**
	 * toArrayList with non-empty list one element
	 */
	@Test
	public void testToArrayListWithNonEmptyListOneElement() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', RecursiveList.<Character> emptyList());
		
		ArrayList<Character> charArray = charList.toArrayList();
		
		assertThat(charArray.size(), is(equalTo(1)));
		assertThat(charArray.toString(), is(equalTo("[a]")));
	}
	
	/**
	 * toArrayList with non-empty list several elements
	 */
	@Test
	public void testToArrayListWithNonEmptyListSeveralElements() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', 
				RecursiveList.<Character> prepend('c', RecursiveList.<Character> emptyList())));
		
		ArrayList<Character> charArray = charList.toArrayList();
		
		assertThat(charArray.size(), is(equalTo(3)));
		assertThat(charArray.toString(), is(equalTo("[a, b, c]")));
	}	
	
	// -- fromArrayList
	
	/**
	 * fromArrayList with null arrayList
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFromArrayListWithNullArrayList() {
		ArrayList<Character> charArray = null;
		
		RecursiveList.<Character> fromArrayList(charArray);
	}
	
	/**
	 * fromArrayList with empty arrayList
	 */
	@Test
	public void testFromArrayListWithEmptyArrayList() {
		ArrayList<Character> charArray = new ArrayList<>();
		
		RecursiveList<Character> charList = RecursiveList.<Character> fromArrayList(charArray);
		
		assertThat(charList.isEmpty(), is(equalTo(true)));
		assertThat(charList.getLength(), is(equalTo(0)));
		assertThat(charList.toString(), is(equalTo("{}")));
	}
	
	/**
	 * fromArrayList with arrayList with one element
	 */
	@SuppressWarnings("serial")
	@Test
	public void testFromArrayListWithArrayListWithOneElement() {
		ArrayList<Character> charArray = new ArrayList<Character>() {{ 
			add('a');
		}};
		
		RecursiveList<Character> charList = RecursiveList.<Character> fromArrayList(charArray);
		
		assertThat(charList.isEmpty(), is(equalTo(false)));
		assertThat(charList.getLength(), is(equalTo(1)));
		assertThat(charList.toString(), is(equalTo("{ head: a, rest: NULL }")));
	}
	
	/**
	 * fromArrayList with arrayList with two elements
	 */
	@SuppressWarnings("serial")
	@Test
	public void testFromArrayListWithArrayListWithTwoElements() {
		ArrayList<Character> charArray = new ArrayList<Character>() {{ 
			add('a');
			add('b');
		}};
		
		RecursiveList<Character> charList = RecursiveList.<Character> fromArrayList(charArray);
		
		assertThat(charList.isEmpty(), is(equalTo(false)));
		assertThat(charList.getLength(), is(equalTo(2)));
		assertThat(charList.toString(), is(equalTo("{ head: a, rest: { head: b, rest: NULL } }")));
	}	
	
	/**
	 * fromArrayList with arrayList with several elements
	 */
	@SuppressWarnings("serial")
	@Test
	public void testFromArrayListWithArrayListWithSeveralElements() {
		ArrayList<Character> charArray = new ArrayList<Character>() {{ 
			add('a');
			add('b');
			add('c');
			add('d');
			add('e');
		}};
		
		RecursiveList<Character> charList = RecursiveList.<Character> fromArrayList(charArray);
		
		assertThat(charList.isEmpty(), is(equalTo(false)));
		assertThat(charList.getLength(), is(equalTo(5)));
		assertThat(charList.toString(), is(equalTo("{ head: a, rest: { head: b, rest: { head: c, rest: { head: d, rest: { head: e, rest: NULL } } } } }")));
	}		
	
	// -- search

	/**
	 * Search for element on empty list
	 */
	@Test
	public void testSearchForOnEmptyList() {
		RecursiveList<Character> charList = RecursiveList.<Character> emptyList();
		
		Optional<Character> foundElement = charList.searchFor('a');
		
		assertThat(foundElement.isPresent(), equalTo(false));
	}
	
	/**
	 * Search for element on list with one element: not found
	 */
	@Test
	public void testSearchForListOneElementNotFound() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', RecursiveList.<Character> emptyList());
		
		Optional<Character> foundElement = charList.searchFor('b');
		
		assertThat(foundElement.isPresent(), equalTo(false));
	}	
	
	/**
	 * Search for element on list with one element: found
	 */
	@Test
	public void testSearchForListOneElementFound() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', RecursiveList.<Character> emptyList());
		
		Optional<Character> foundElement = charList.searchFor('a');
		
		assertThat(foundElement.isPresent(), equalTo(true));
		assertThat(foundElement.get(), is(equalTo('a')));
	}	
	
	/**
	 * Search for element on list with two elements: not found
	 */
	@Test
	public void testSearchForListTwoElementsNotFound() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', RecursiveList.<Character> emptyList()));
		
		Optional<Character> foundElement = charList.searchFor('c');
		
		assertThat(foundElement.isPresent(), equalTo(false));
	}	
	
	/**
	 * Search for element on list with two elements: found on first
	 */
	@Test
	public void testSearchForListTwoElementsFoundOnFirst() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', RecursiveList.<Character> emptyList()));
		
		Optional<Character> foundElement = charList.searchFor('a');
		
		assertThat(foundElement.isPresent(), equalTo(true));
		assertThat(foundElement.get(), is(equalTo('a')));
	}
	
	/**
	 * Search for element on list with two elements: found on second
	 */
	@Test
	public void testSearchForListTwoElementsFoundOnSecond() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', RecursiveList.<Character> emptyList()));
		
		Optional<Character> foundElement = charList.searchFor('b');
		
		assertThat(foundElement.isPresent(), equalTo(true));
		assertThat(foundElement.get(), is(equalTo('b')));
	}	
	
	
	/**
	 * Search for element on list with several elements: not found
	 */
	@Test
	public void testSearchForListSeveralElementsNotFound() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', RecursiveList.<Character> emptyList()));
		
		Optional<Character> foundElement = charList.searchFor('d');
		
		assertThat(foundElement.isPresent(), equalTo(false));
	}	
	
	/**
	 * Search for element on list with several elements: found in the first
	 */
	@Test
	public void testSearchForListSeveralElementsFoundInFirst() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', 
				RecursiveList.<Character> prepend('c', RecursiveList.<Character> emptyList())));
		
		Optional<Character> foundElement = charList.searchFor('a');
		
		assertThat(foundElement.isPresent(), equalTo(true));
		assertThat(foundElement.get(), is(equalTo('a')));
	}
	
	/**
	 * Search for element on list with several elements: found in the middle
	 */
	@Test
	public void testSearchForListSeveralElementsFoundInMiddle() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', 
				RecursiveList.<Character> prepend('c', RecursiveList.<Character> emptyList())));
		
		Optional<Character> foundElement = charList.searchFor('b');
		
		assertThat(foundElement.isPresent(), equalTo(true));
		assertThat(foundElement.get(), is(equalTo('b')));
	}
	
	/**
	 * Search for element on list with several elements: found in last
	 */
	@Test
	public void testSearchForListSeveralElementsFoundInLast() {
		RecursiveList<Character> charList = RecursiveList.<Character> prepend('a', 
				RecursiveList.<Character> prepend('b', 
				RecursiveList.<Character> prepend('c', RecursiveList.<Character> emptyList())));
		
		Optional<Character> foundElement = charList.searchFor('c');
		
		assertThat(foundElement.isPresent(), equalTo(true));
		assertThat(foundElement.get(), is(equalTo('c')));
	}	
	
}


