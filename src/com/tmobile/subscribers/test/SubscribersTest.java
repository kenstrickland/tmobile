package com.tmobile.subscribers.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tmobile.MobileSubscribers;
import com.tmobile.model.PhoneNumber;
import com.tmobile.subscribers.model.SubscriberAccount;
import com.tmobile.subscribers.model.SubscriberCustomer;
import com.tmobile.subscribers.model.SubscriberException;
import com.tmobile.subscribers.model.SubscriberExceptionType;
import com.tmobile.subscribers.service.SubscriberBuilder;
import com.tmobile.subscribers.service.SubscriberService;

public class SubscribersTest { 

	private static SubscriberService subscriberService;
	private static SubscriberBuilder scriberBuilder;
	
	@Before
	public void subscriberSetUp() {
		subscriberService = (SubscriberService) MobileSubscribers.tMobileAppCntxt.getBean("subscriberService");
		scriberBuilder = subscriberService.getSubscriberBuilder();
	}
	
	@Test
	public void testAddSubscriber() {
		try {
			SubscriberCustomer subscriberToBuild;
		
			// Subscriber 1
			scriberBuilder.setName("Kenneth", null, "Strickland");
			scriberBuilder.setPhoneNumber("678", "672", "8732");
			scriberBuilder.setAccount(new Float(0F), new Float(5.32F));
			scriberBuilder.build();

			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);

			// Subscriber 2
			scriberBuilder.setName("Christopher", null, "Alexander");
			scriberBuilder.setPhoneNumber("678", "111", "2222");
			scriberBuilder.setAccount(new Float(100.54F), new Float(6.00F));
			scriberBuilder.build();
		
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);
		
			// Subscriber 3
			scriberBuilder.setName("Steve", null, "Jobs");
			scriberBuilder.setPhoneNumber("678", "222", "3333");
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
		
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber add exception    **** " + se);
			fail();
		}
	} 

	@Test
	public void testAddSubscriberInvalidFirstName() {
		try {
			SubscriberCustomer subscriberToBuild;
			scriberBuilder.setName(null, null, "LastName");
			scriberBuilder.setPhoneNumber("111", "222", "3333");
			scriberBuilder.setAccount(new Float(100.54F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);

			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber add exception    **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.InValidFirstName)));
		}
	} 

	@Test
	public void testAddSubscriberInvalidLastName() {
		try {
			SubscriberCustomer subscriberToBuild;
			scriberBuilder.setName("FirstName", null, null);
			scriberBuilder.setPhoneNumber("111", "222", "3333");
			scriberBuilder.setAccount(new Float(100.54F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);
			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber add exception    **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.InValidLastName)));
		}
	} 
	
	@Test
	public void testAddSubscriberInvalidAreaCode() {
		try {
			SubscriberCustomer subscriberToBuild;
			scriberBuilder.setName("John", null, "Negative");
			scriberBuilder.setPhoneNumber(null, "222", "3333");
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);
			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber add exception    **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.InValidAreaCode)));
		}
	} 

	@Test
	public void testAddSubscriberInvalidPhonePrefix() {
		try {
			SubscriberCustomer subscriberToBuild;
			scriberBuilder.setName("John", null, "Negative");
			scriberBuilder.setPhoneNumber("111", null, "3333");
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);
			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber add exception    **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.InValidPhonePrefix)));
		}
	} 
	
	@Test
	public void testAddSubscriberInvalidLineNumber() {
		try {
			SubscriberCustomer subscriberToBuild;
			scriberBuilder.setName("John", null, "Negative");
			scriberBuilder.setPhoneNumber("111", "222", null);
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			System.out.println("Subscriber added            **** " + subscriberToBuild);
			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber add exception    **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.InValidLineNumber)));
		}
	} 

	@Test
	public void testAddSubscriberExists() {
		try {
			SubscriberCustomer subscriberToBuild;

			scriberBuilder.setName("Joe", null, "Duplicate");
			scriberBuilder.setPhoneNumber("678", "555", "6666");
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);

			scriberBuilder.setName("Joe", null, "Duplicate");
			scriberBuilder.setPhoneNumber("678", "555", "6666");
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToBuild = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToBuild);
			
			System.out.println("Subscriber added            **** " + subscriberToBuild);
			fail();
			
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber add exception    **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.SubscriberExists)));
		}
	} 

	@Test
	public void testSubscriberFind() {
		try {
			SubscriberCustomer subscriberToFind;

			scriberBuilder.setName("Guy", null, "ToFind");
			scriberBuilder.setPhoneNumber("678", "777", "8888");
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToFind = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToFind);
			
			PhoneNumber phoneNumberToFind = new PhoneNumber("678", "222", "3333");
			subscriberToFind = subscriberService.findSubscriber(phoneNumberToFind);
			
			System.out.println("Subscriber found            **** " + subscriberToFind);
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber found exception  **** " + se);
			fail();
		}
	} 

	@Test
	public void testSubscriberFindNegative() {
		try {
			PhoneNumber phoneNumberToFind = new PhoneNumber("678", "888", "9999");
			SubscriberCustomer subscriberToFind = subscriberService.findSubscriber(phoneNumberToFind);
			System.out.println("Subscriber found            **** " + subscriberToFind);
			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber found exception  **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.SubscriberDoesNotExists)));
		}
	} 

	@Test
	public void testSubscriberRemove() {
		try {
			SubscriberCustomer subscriberToRemove;
			
			scriberBuilder.setName("Guy", null, "ToRemove");
			scriberBuilder.setPhoneNumber("678", "989", "8989");
			scriberBuilder.setAccount(new Float(0F), new Float(6.00F));
			scriberBuilder.build();
			
			subscriberToRemove = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToRemove);

			PhoneNumber phoneNumberToRemove = new PhoneNumber("678", "989", "8989");
			subscriberToRemove = subscriberService.removeSubscriber(phoneNumberToRemove);
			System.out.println("Subscriber remove           **** " + subscriberToRemove);
			
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber remove exception **** " + se);
			fail();
		}
	} 

	@Test
	public void testSubscriberRemoveNegative() {
		try {
			PhoneNumber phoneNumberToRemove = new PhoneNumber("678", "878", "7878");
			SubscriberCustomer subscriberToFind = subscriberService.removeSubscriber(phoneNumberToRemove);
			System.out.println("Subscriber remove           **** " + subscriberToFind);
			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber remove exception **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.SubscriberDoesNotExists)));
		}
	} 

	@Test
	public void testSubscriberUpdateBalance() {
		try {
			SubscriberCustomer subscriberToUpdate;
			
			scriberBuilder.setName("Guy", null, "ToUpdate");
			scriberBuilder.setPhoneNumber("678", "939", "3939");
			scriberBuilder.setAccount(new Float(300F), new Float(5.00F));
			scriberBuilder.build();
			
			subscriberToUpdate = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToUpdate);

			System.out.println("Subscriber update before    **** " + subscriberToUpdate);
			
			PhoneNumber phoneNumberToUpdate = new PhoneNumber("678", "939", "3939");

			// This will effective charge 5 minutes 
			subscriberService.updateSubscriberBalance(phoneNumberToUpdate, new Integer(5));
			
			subscriberToUpdate = subscriberService.findSubscriber(phoneNumberToUpdate);

			// Should be left over with $250
			assertTrue(subscriberToUpdate.getAccount().getAmtBalance().compareTo(new Float(275.00F)) == 0);

			System.out.println("Subscriber update after     **** " + subscriberToUpdate);
			
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber update exception **** " + se);
			fail();
		}
	} 

	@Test
	public void testSubscriberUpdateInvalidBalance() {
		try {
			SubscriberCustomer subscriberToUpdate;

			scriberBuilder.setName("Guy", null, "ToUpdate");
			scriberBuilder.setPhoneNumber("678", "565", "6565");
			scriberBuilder.setAccount(new Float(0F), new Float(5.00F));
			scriberBuilder.build();
			
			subscriberToUpdate = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberToUpdate);
			
			PhoneNumber phoneNumberToUpdate = new PhoneNumber("678", "565", "6565");
			subscriberToUpdate = subscriberService.findSubscriber(phoneNumberToUpdate);
			System.out.println("Subscriber update before    **** " + subscriberToUpdate);
			
			subscriberService.updateSubscriberBalance(phoneNumberToUpdate, new Integer(500));
			
			subscriberToUpdate = subscriberService.findSubscriber(phoneNumberToUpdate);
			System.out.println("Subscriber update after     **** " + subscriberToUpdate);
			
			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber update exception **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.InValidAccountBalance)));
		}
	} 

	@Test
	public void testSubscriberGetBalance() {
		try {
			SubscriberCustomer subscriberForBalance;
			SubscriberAccount  subscriberAccount;
			 
			scriberBuilder.setName("Guy", null, "ForBalance");
			scriberBuilder.setPhoneNumber("678", "232", "3232");
			scriberBuilder.setAccount(new Float(200F), new Float(5.00F));
			scriberBuilder.build();
			
			subscriberForBalance = scriberBuilder.releaseSubscriber();
			subscriberService.addSubscriber(subscriberForBalance);

			System.out.println("Subscriber for balance      **** " + subscriberForBalance);
			
			PhoneNumber phoneNumberForBalance = new PhoneNumber("678", "232", "3232");

			subscriberAccount = subscriberService.getSubscriberBalance(phoneNumberForBalance);

			// Should have a balance of $200
			assertTrue(subscriberAccount.getAmtBalance().compareTo(new Float(200.00F)) == 0);
			
			// Should have 40 minutes available
			assertTrue(subscriberAccount.getMinutesLeft().compareTo(new Float(40.00F)) == 0);

			System.out.println("Subscriber balance          **** $" + subscriberForBalance.getAccount().getAmtBalance());
			System.out.println("Subscriber minutes          ****  " + subscriberForBalance.getAccount().getMinutesLeft());
			
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber balance exeption **** " + se);
			fail();
		}
	} 

	@Test
	public void testSubscriberGetBalanceNegative() {
		try {
			@SuppressWarnings("unused")
			SubscriberAccount  subscriberAccount;
			PhoneNumber phoneNumberForBalance = new PhoneNumber("678", "232", "5454");

			subscriberAccount = subscriberService.getSubscriberBalance(phoneNumberForBalance);

			fail();
		} 
		catch (SubscriberException se) {
			System.out.println("Subscriber balance exeption **** " + se);
			assertTrue(se.equals(new SubscriberException(SubscriberExceptionType.SubscriberDoesNotExists)));
		}
	} 
	
	@After
	 public void subscriberTearDown() {
		subscriberService = null;
		scriberBuilder = null;
	 }	
}
