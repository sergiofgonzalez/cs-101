package org.joolzminer.examples.adt.lists;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayBackedIntLinkedList implements IntList {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArrayBackedIntLinkedList.class);
	
	private static final int DEFAULT_INITIAL_SIZE = 10;
	private static final double INFLATE_RATIO = .5;

	private IntNode[] storage;
	
	private OptionalInt elementsIndex;
	private OptionalInt availableIndex;
	
	public ArrayBackedIntLinkedList() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public ArrayBackedIntLinkedList(int initialSize) {
		storage = new IntNode[DEFAULT_INITIAL_SIZE];
		for (int i = 0; i < initialSize; i++) {
			storage[i] = new IntNode();
			storage[i].setNext(OptionalInt.of(i + 1));
		}
		storage[initialSize - 1].setNext(OptionalInt.empty());
		availableIndex = OptionalInt.of(0);
		elementsIndex = OptionalInt.empty();
		
		LOGGER.debug("ArrayBackedIntList has been created: storage={}; elementsIndex={}; availableIndex={}", Arrays.toString(storage), elementsIndex, availableIndex);
	}
	
	
	@Override
	public boolean isEmpty() {
		return !elementsIndex.isPresent();
	}

	@Override
	public void add(int element, int position) {

		if (elementsIndex.isPresent()) {
		
			int prevNode = elementsIndex.getAsInt();

			for(int i = 0; i < position; i++) {
				prevNode = storage[prevNode].getNext().getAsInt();
			}

			if (!availableIndex.isPresent()) {
				inflate(INFLATE_RATIO);
			}
			
			OptionalInt newAvailableIndex = storage[availableIndex.getAsInt()].getNext();
			storage[availableIndex.getAsInt()].setInfo(element);
			
			int temp = storage[prevNode].getNext().getAsInt();
			storage[prevNode].setNext(availableIndex);
			storage[availableIndex.getAsInt()].setNext(OptionalInt.of(temp));
			
			availableIndex = newAvailableIndex;						
		} else {
			OptionalInt newAvailableIndex = storage[availableIndex.getAsInt()].getNext();
			storage[availableIndex.getAsInt()].setInfo(element);
			storage[availableIndex.getAsInt()].setNext(elementsIndex);
			
			elementsIndex = availableIndex;
			availableIndex = newAvailableIndex;		
		}
	}

	@Override
	public void remove(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLength() {
		if (!elementsIndex.isPresent()) {
			return 0;
		}
		
		int len = 1;
		for (int tr = elementsIndex.getAsInt(); storage[tr].getNext().isPresent(); tr = storage[tr].getNext().getAsInt()) {
			len++;
		}
		return len;
	}

	@Override
	public void traverse(IntConsumer consumer) {
		// TODO Auto-generated method stub
		
	}

	private void inflate(double ratio) {
	}
}
