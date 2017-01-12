package com.tmobile.subscribers.service;

import com.tmobile.model.PhoneNumber;
import com.tmobile.subscribers.model.SubscriberAccount;
import com.tmobile.subscribers.model.SubscriberCustomer;
import com.tmobile.subscribers.model.SubscriberException;

public interface SubscriberService {

	public SubscriberBuilder getSubscriberBuilder();
    public boolean addSubscriber(SubscriberCustomer subscriber) throws SubscriberException;
    public void updateSubscriberBalance(PhoneNumber phoneNumber, Integer minutes) throws SubscriberException;
    public SubscriberCustomer findSubscriber(PhoneNumber phoneNumber) throws SubscriberException;
    public SubscriberCustomer removeSubscriber(PhoneNumber phoneNumber)  throws SubscriberException;
    public SubscriberAccount getSubscriberBalance(PhoneNumber phoneNumber) throws SubscriberException;
	public void displaySubscribers();

}
