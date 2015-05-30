package org.joolzminer.examples.adt.lists.runners;

import java.util.OptionalInt;
import java.util.Random;
import java.util.Scanner;

import org.joolzminer.examples.adt.queues.IntArrayBackedQueue;
import org.joolzminer.examples.adt.queues.IntQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupermarketQueueFunctionalRunner {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SupermarketQueueFunctionalRunner.class);
	
	private static final Random rand = new Random();
	
	private static final int MIN_NUM_CUSTOMERS_MINUTE = 1;
	private static final int MAX_NUM_CUSTOMERS_MINUTE = 10;

	private static final int MIN_NUM_PRODUCTS_CUSTOMER = 1;
	private static final int MAX_NUM_PRODUCTS_CUSTOMER = 35;
	
	private static final int NUM_SECONDS_PER_PRODUCT = 2;
	private static final int NUM_SECONDS_CASH = 45;
	private static final int NUM_SECONDS_CARD = 30;
	
	public static void main(String[] args) {
				
		int cashierIdleWorkUnits = 0;
		int cashierWorkedUnits = 0;
		int numCustomersServed = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		IntQueue supermarketQueue = new IntArrayBackedQueue();
		boolean done = false;
		int minute = 0;
		OptionalInt remSecondsToCompleteCustomer = OptionalInt.empty();
		while (!done) {
			System.out.println("Simulating minute: " + minute);
			int numCustomers = getRandomIntBetween(MIN_NUM_CUSTOMERS_MINUTE, MAX_NUM_CUSTOMERS_MINUTE);
			for (int i = 0; i < numCustomers; i++) {
				int numProducts = getRandomIntBetween(MIN_NUM_PRODUCTS_CUSTOMER, MAX_NUM_PRODUCTS_CUSTOMER);
				boolean isUsingCash = rand.nextBoolean();
				System.out.println("Customer joins the queue with " + numProducts + " product(s)." + (isUsingCash ? " Will use cash." : " Will use credit card."));				
				
				supermarketQueue.add(numProducts * NUM_SECONDS_PER_PRODUCT + (isUsingCash ? NUM_SECONDS_CASH : NUM_SECONDS_CARD));
			}
			
			int workUnits = 60;
			while (workUnits > 0) {
				if (remSecondsToCompleteCustomer.isPresent()) {
					if (remSecondsToCompleteCustomer.getAsInt() > workUnits) {
						remSecondsToCompleteCustomer = OptionalInt.of(remSecondsToCompleteCustomer.getAsInt() - workUnits);
						cashierWorkedUnits += workUnits;
						workUnits = 0;						
					} else {
						numCustomersServed++;
						workUnits -= remSecondsToCompleteCustomer.getAsInt();
						cashierWorkedUnits += remSecondsToCompleteCustomer.getAsInt();
						if (supermarketQueue.isEmpty()) {
							remSecondsToCompleteCustomer = OptionalInt.empty();
							cashierIdleWorkUnits += workUnits;
							workUnits = 0;
						} else {
							remSecondsToCompleteCustomer = OptionalInt.of(supermarketQueue.remove());
						}
					}
				} else {
					if (supermarketQueue.isEmpty()) {
						remSecondsToCompleteCustomer = OptionalInt.empty();
						cashierIdleWorkUnits += workUnits;
						workUnits = 0;
					} else {
						remSecondsToCompleteCustomer = OptionalInt.of(supermarketQueue.remove());
					}					
				}
				
			}
			
			System.out.println("************** stats ***********************");
			System.out.println(" minutes worked        : " + (minute + 1));
			System.out.println(" num customers served  : " + numCustomersServed);
			System.out.println(" cashier worked units  : " + cashierWorkedUnits);
			System.out.println(" cashier idle units    : " + cashierIdleWorkUnits);
			System.out.println(" customers in the queue: " + supermarketQueue.getLength());
			System.out.println(" currently serving     : " + remSecondsToCompleteCustomer.isPresent() + " (" + remSecondsToCompleteCustomer.orElse(0) + ")");
			System.out.println("************** stats ***********************");
			
			minute++;

			System.out.print("Type 'q' to Quit simulation, any other key to continue: ");
			String userInput = scanner.next();
			if (userInput.equalsIgnoreCase("q")) {
				done = true;
			}
		}
				
		scanner.close();
	}
	
	private static int getRandomIntBetween(int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}
		
}
