package com.tmobile.subscribers.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tmobile.model.PhoneNumber;
import com.tmobile.subscribers.model.SubscriberAccount;
import com.tmobile.subscribers.model.SubscriberCustomer;
import com.tmobile.subscribers.model.SubscriberException;
import com.tmobile.subscribers.model.SubscriberExceptionType;

@Service("subscriberService")
public class SubscriberServiceImpl implements SubscriberService {

	 private HashMap <PhoneNumber, SubscriberCustomer>subscribers = new HashMap <PhoneNumber, SubscriberCustomer>();

	 @Autowired
	 @Qualifier("subscriberBuilder")
	 private SubscriberBuilder subscriberBuilder;
	 
	 static Logger logger = Logger.getLogger(SubscriberServiceImpl.class.toString());

     @Override
	 public SubscriberBuilder getSubscriberBuilder() {
		return subscriberBuilder;
	 }
	 
 	@SuppressWarnings("unused")
	 private void setSubscriberBuilder(SubscriberBuilder subscriberBuilder) {
		this.subscriberBuilder = subscriberBuilder;
	 }
     
     @Override
     public synchronized boolean addSubscriber(SubscriberCustomer subscriber) throws SubscriberException {
	     if(subscribers.containsKey(subscriber.getPhoneNumber())) {
	    	 throw new SubscriberException(SubscriberExceptionType.SubscriberExists);
	     } else {
    		 this.subscribers.put(subscriber.getPhoneNumber(), subscriber);
	    	 return true;
	     }
     }
     
     @Override
     public void displaySubscribers() {
    	 SubscriberCustomer subscriber;
    	 Iterator<PhoneNumber> iter = subscribers.keySet().iterator(); 
    	 System.out.println("**** Begin Current Subscribers Report ****");
    	 if(subscribers.size() > 0) {
        	 while (iter.hasNext()){
       		  subscriber = subscribers.get(iter.next());
       		  System.out.println(subscriber.getPhoneNumber().toString()+" "+subscriber.getName().toString());
        	 }
    	 } else {
        	 System.out.println("None");
    	 }
    	 System.out.println("**** End Current Subscribers Report ****");
    	 System.out.println(" ");
     }

     @Override
     public synchronized void updateSubscriberBalance(PhoneNumber phoneNumber, Integer minutes) throws SubscriberException {
 		try {
 			SubscriberCustomer subscriber = this.findSubscriber(phoneNumber);
 			subscriber.getAccount().decrementByCPM(minutes);
 			removeSubscriber(subscriber.getPhoneNumber());
 			addSubscriber(subscriber);
		} 
		catch (SubscriberException se) {
   		 	throw new SubscriberException(se);
		}
    	 
     }

     @Override
     public SubscriberCustomer findSubscriber(PhoneNumber phoneNumber)  throws SubscriberException {
    	 SubscriberCustomer subscriber = subscribers.get(phoneNumber);
    	 if(subscriber == null) {
    		 throw new SubscriberException(SubscriberExceptionType.SubscriberDoesNotExists);
    	 } else {
        	 return subscriber;
    	 }
     }

     @Override
     public synchronized SubscriberCustomer removeSubscriber(PhoneNumber phoneNumber)  throws SubscriberException {
    	 SubscriberCustomer subscriber = subscribers.remove(phoneNumber);
    	 if(subscriber == null) {
    		 throw new SubscriberException(SubscriberExceptionType.SubscriberDoesNotExists);
    	 } else {
        	 return subscriber;
    	 }
     }
     
     @Override
     public SubscriberAccount getSubscriberBalance(PhoneNumber phoneNumber) throws SubscriberException {
 		try {
 			SubscriberCustomer subscriber = this.findSubscriber(phoneNumber);
 			return subscriber.getAccount();
		} 
		catch (SubscriberException se) {
   		 	throw new SubscriberException(se);
		}
    	 
     }
}
