package org.joolzminer.examples.adt.queues;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class IntArrayBackedQueueTest {

	/**************************** creation ********************/
	@Test
	public void testCreateNoSize() {
		IntQueue intQueue = new IntArrayBackedQueue();
		
		assertThat(intQueue, is(notNullValue()));
		assertThat(intQueue.isEmpty(), is(true));
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.toString(), is(equalTo("[]")));
	}
	
	
	@Test
	public void testCreateWithValidSize() {
		IntQueue intQueue = new IntArrayBackedQueue(5);
		
		assertThat(intQueue, is(notNullValue()));
		assertThat(intQueue.isEmpty(), is(true));
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.toString(), is(equalTo("[]")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateZeroSize() {
		new IntArrayBackedQueue(0);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateNegativeSize() {
		new IntArrayBackedQueue(-1);		
	}	
	
	
	/**************************** add ********************/
	@Test
	public void testAddSingleElement() {
		IntQueue intQueue = new IntArrayBackedQueue();
		
		intQueue.add(1_000);
		assertThat(intQueue.getLength(), is(equalTo(1)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.toString(), is(equalTo("[1000]")));
	}
	
	@Test
	public void testAddSeveralElementsBelowCapacity() {
		IntQueue intQueue = new IntArrayBackedQueue(5);
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		intQueue.add(1_003);
		assertThat(intQueue.getLength(), is(equalTo(4)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.toString(), is(equalTo("[1000, 1001, 1002, 1003]")));
	}
	
	
	@Test
	public void testAddSeveralElementsMatchingCapacity() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		
		assertThat(intQueue.getLength(), is(equalTo(3)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	@Test
	public void testAddSeveralElementsBeyondCapacity() {
		IntQueue intQueue = new IntArrayBackedQueue(2);
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		
		assertThat(intQueue.getLength(), is(equalTo(3)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.toString(), is(equalTo("[1000, 1001, 1002]")));
	}	
	
	
	/**************************** remove ********************/
	@Test(expected=IllegalStateException.class)
	public void testRemoveFromEmptyQueue() {
		IntQueue intQueue = new IntArrayBackedQueue();
		
		intQueue.remove();
	}
	
	@Test
	public void testRemoveElemFromQueueWithOneElem() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		
		int elem = intQueue.remove();
		
		assertThat(elem, is(equalTo(1_000)));
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.isEmpty(), is(equalTo(true)));
		assertThat(intQueue.toString(), is(equalTo("[]")));
	}
	
	@Test
	public void testRemoveElemFromQueueWithTwoElems() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		intQueue.add(1_001);
		
		int elem = intQueue.remove();
		
		assertThat(elem, is(equalTo(1_000)));
		assertThat(intQueue.getLength(), is(equalTo(1)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.toString(), is(equalTo("[1001]")));
	}
	
	@Test
	public void testRemoveSecondElemFromQueueWithTwoElems() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		intQueue.add(1_001);
		
		intQueue.remove();
		int elem = intQueue.remove();
		
		assertThat(elem, is(equalTo(1_001)));
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.isEmpty(), is(equalTo(true)));
		assertThat(intQueue.toString(), is(equalTo("[]")));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testRemoveFromEmptyQueueThatWasNotEmpty() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		intQueue.add(1_001);
		
		intQueue.remove();
		intQueue.remove();
		intQueue.remove();		
	}
	
	/**************************** length ********************/
	
	@Test
	public void testLengthIsZeroUponCreation() {
		IntQueue intQueue = new IntArrayBackedQueue();
		
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.isEmpty(), is(true));
	}
	
	@Test
	public void testLengthIsOne() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		
		assertThat(intQueue.getLength(), is(equalTo(1)));
		assertThat(intQueue.isEmpty(), is(false));
	}
	
	@Test
	public void testLengthIsFour() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		intQueue.add(1_003);
		
		assertThat(intQueue.getLength(), is(equalTo(4)));
		assertThat(intQueue.isEmpty(), is(false));
	}
	
	@Test
	public void testLengthCircularity() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.add(1_001);		
		intQueue.remove();
		
		intQueue.add(1_002);
		intQueue.remove();
		
		intQueue.add(1_003);
		intQueue.remove();
		
		assertThat(intQueue.getLength(), is(equalTo(1)));
		assertThat(intQueue.isEmpty(), is(false));
	}
	
	
	/**************************** string rep **********************/
	@Test
	public void testEmptyQueue() {
		IntQueue intQueue = new IntArrayBackedQueue();
		
		assertThat(intQueue.toString(), is(equalTo("[]")));		
	}
	
	@Test
	public void testQueueOneElemToZero() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		intQueue.remove();
		
		assertThat(intQueue.toString(), is(equalTo("[]")));		
	}
	
	@Test
	public void testQueueOneElem() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		
		assertThat(intQueue.toString(), is(equalTo("[1000]")));		
	}
		
	@Test
	public void testQueueTwoElems() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		intQueue.add(1_001);
		
		assertThat(intQueue.toString(), is(equalTo("[1000, 1001]")));		
	}
	
	@Test
	public void testQueueTwoElemsToOne() {
		IntQueue intQueue = new IntArrayBackedQueue();
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.remove();
		
		
		assertThat(intQueue.toString(), is(equalTo("[1001]")));		
	}
	
	
	@Test
	public void testQueueShiftingOneElem_1() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.remove();
		
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);
		
		
		assertThat(intQueue.toString(), is(equalTo("[1002]")));		
	}
	
	@Test
	public void testQueueShiftingOneElem_2() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.remove();
		
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);
		intQueue.remove();
		
		intQueue.add(1_003);
		
		
		assertThat(intQueue.toString(), is(equalTo("[1003]")));		
	}
	
	
	@Test
	public void testQueueShiftingOneElem_3() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.remove();
		
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);
		intQueue.remove();
		
		intQueue.add(1_003);
		intQueue.remove();
		
		intQueue.add(1_004);
				
		assertThat(intQueue.toString(), is(equalTo("[1004]")));		
	}
	
	
	@Test
	public void testQueueShiftingTwoElems_1() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);

		assertThat(intQueue.toString(), is(equalTo("[1001, 1002]")));		
	}
	
	
	@Test
	public void testQueueShiftingTwoElems_2() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);
		intQueue.remove();
		
		intQueue.add(1_003);

		assertThat(intQueue.toString(), is(equalTo("[1002, 1003]")));		
	}
	
	
	@Test
	public void testQueueShiftingTwoElems_3() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);
		intQueue.remove();
		
		intQueue.add(1_003);
		intQueue.remove();
		
		intQueue.add(1_004);

		assertThat(intQueue.toString(), is(equalTo("[1003, 1004]")));		
	}
	
	
	@Test
	public void testQueueShiftingTwoElems_4() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);
		intQueue.remove();
		
		intQueue.add(1_003);
		intQueue.remove();
		
		intQueue.add(1_004);
		intQueue.remove();
		
		intQueue.add(1_005);

		assertThat(intQueue.toString(), is(equalTo("[1004, 1005]")));		
	}
	
	
	/**************************** circularity ********************/
	
	@Test
	public void testAddRemoveSequenceInOneElementQueue() {
		IntQueue intQueue = new IntArrayBackedQueue(1);
		
		intQueue.add(1_000);
		int elem0 = intQueue.remove();
		intQueue.add(1_001);
		int elem1 = intQueue.remove();
		
		assertThat(elem0, is(equalTo(1_000)));
		assertThat(elem1, is(equalTo(1_001)));
		assertThat(intQueue.isEmpty(), is(true));
		assertThat(intQueue.getLength(), is(equalTo(0)));
	}
	
	@Test
	public void testAddRemoveOneSequenceInTwoElementsQueue() {
		IntQueue intQueue = new IntArrayBackedQueue(2);
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		
		int elem0 = intQueue.remove();
		intQueue.add(1_002);
		
		assertThat(elem0, is(equalTo(1_000)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.getLength(), is(equalTo(2)));
		assertThat(intQueue.toString(), is(equalTo("[1001, 1002]")));
	}
	
	
	@Test
	public void testAddRemoveSeveralTimesNoInflateQueue() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		
		int elem0 = intQueue.remove();
		intQueue.add(1_003);
		
		int elem1 = intQueue.remove();
		intQueue.add(1_004);
		
		int elem2 = intQueue.remove();
		intQueue.add(1_005);
		
		
		assertThat(elem0, is(equalTo(1_000)));
		assertThat(elem1, is(equalTo(1_001)));
		assertThat(elem2, is(equalTo(1_002)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.getLength(), is(equalTo(3)));
		assertThat(intQueue.toString(), is(equalTo("[1003, 1004, 1005]")));
	}
	
	
	@Test
	public void testAddRemoveSeveralTimesInflateQueue() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		
		int elem0 = intQueue.remove();
		intQueue.add(1_003);
		
		int elem1 = intQueue.remove();
		intQueue.add(1_004);
		
		int elem2 = intQueue.remove();
		intQueue.add(1_005);
		
		intQueue.add(1_006);
				
		assertThat(elem0, is(equalTo(1_000)));
		assertThat(elem1, is(equalTo(1_001)));
		assertThat(elem2, is(equalTo(1_002)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.getLength(), is(equalTo(4)));
		assertThat(intQueue.toString(), is(equalTo("[1003, 1004, 1005, 1006]")));
	}
	
	@Test
	public void testAddRemoveSeveralTimesInflateQueueWithSeveralElems() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		
		int elem0 = intQueue.remove();
		intQueue.add(1_003);
		
		int elem1 = intQueue.remove();
		intQueue.add(1_004);
		
		int elem2 = intQueue.remove();
		intQueue.add(1_005);
		
		intQueue.add(1_006);
		intQueue.add(1_007);
		intQueue.add(1_008);
		intQueue.add(1_009);
		intQueue.add(1_010);
				
		assertThat(elem0, is(equalTo(1_000)));
		assertThat(elem1, is(equalTo(1_001)));
		assertThat(elem2, is(equalTo(1_002)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.getLength(), is(equalTo(8)));
		assertThat(intQueue.toString(), is(equalTo("[1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010]")));
	}	
	
	@Test
	public void testInflateInCircularUseCase() {
		IntQueue intQueue = new IntArrayBackedQueue(3);
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		
		intQueue.remove();
		intQueue.add(1_003);
		
		intQueue.add(1004);
		
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.getLength(), is(equalTo(4)));
		assertThat(intQueue.toString(), is(equalTo("[1001, 1002, 1003, 1004]")));		
		
	}
}
