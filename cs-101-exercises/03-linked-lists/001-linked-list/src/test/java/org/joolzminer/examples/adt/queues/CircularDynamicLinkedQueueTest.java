package org.joolzminer.examples.adt.queues;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class CircularDynamicLinkedQueueTest {

	/**************************** creation ********************/
	@Test
	public void testCreateNoSize() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
		assertThat(intQueue, is(notNullValue()));
		assertThat(intQueue.isEmpty(), is(true));
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.toString(), is(equalTo("[]")));
	}
		
	
	/**************************** add ********************/
	@Test
	public void testAddSingleElement() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
		intQueue.add(1_000);
		assertThat(intQueue.getLength(), is(equalTo(1)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.toString(), is(equalTo("[1000]")));
	}
	
	@Test
	public void testAddSeveralElementsBelowCapacity() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		
		assertThat(intQueue.getLength(), is(equalTo(3)));
		assertThat(intQueue.isEmpty(), is(equalTo(false)));
		assertThat(intQueue.toString(), is(equalTo("[1000, 1001, 1002]")));
	}
	
	@Test
	public void testAddSeveralElementsBeyondCapacity() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
		intQueue.remove();
	}
	
	@Test
	public void testRemoveElemFromQueueWithOneElem() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		
		int elem = intQueue.remove();
		
		assertThat(elem, is(equalTo(1_000)));
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.isEmpty(), is(equalTo(true)));
		assertThat(intQueue.toString(), is(equalTo("[]")));
	}
	
	@Test
	public void testRemoveElemFromQueueWithTwoElems() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		intQueue.add(1_001);
		
		intQueue.remove();
		intQueue.remove();
		intQueue.remove();		
	}
	
	/**************************** length ********************/
	
	@Test
	public void testLengthIsZeroUponCreation() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
		assertThat(intQueue.getLength(), is(equalTo(0)));
		assertThat(intQueue.isEmpty(), is(true));
	}
	
	@Test
	public void testLengthIsOne() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		
		assertThat(intQueue.getLength(), is(equalTo(1)));
		assertThat(intQueue.isEmpty(), is(false));
	}
	
	@Test
	public void testLengthIsFour() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.add(1_002);
		intQueue.add(1_003);
		
		assertThat(intQueue.getLength(), is(equalTo(4)));
		assertThat(intQueue.isEmpty(), is(false));
	}
	
	@Test
	public void testLengthCircularity() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
		assertThat(intQueue.toString(), is(equalTo("[]")));		
	}
	
	@Test
	public void testQueueOneElemToZero() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		intQueue.remove();
		
		assertThat(intQueue.toString(), is(equalTo("[]")));		
	}
	
	@Test
	public void testQueueOneElem() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		
		assertThat(intQueue.toString(), is(equalTo("[1000]")));		
	}
		
	@Test
	public void testQueueTwoElems() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		intQueue.add(1_001);
		
		assertThat(intQueue.toString(), is(equalTo("[1000, 1001]")));		
	}
	
	@Test
	public void testQueueTwoElemsToOne() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.remove();
		
		
		assertThat(intQueue.toString(), is(equalTo("[1001]")));		
	}
	
	
	@Test
	public void testQueueShiftingOneElem_1() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		intQueue.remove();
		
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);
		
		
		assertThat(intQueue.toString(), is(equalTo("[1002]")));		
	}
	
	@Test
	public void testQueueShiftingOneElem_2() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		intQueue.add(1_000);
		intQueue.add(1_001);
		intQueue.remove();
		
		intQueue.add(1_002);

		assertThat(intQueue.toString(), is(equalTo("[1001, 1002]")));		
	}
	
	
	@Test
	public void testQueueShiftingTwoElems_2() {
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
		
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
		Queue<Integer> intQueue = new CircularDynamicLinkedQueue<>();
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
