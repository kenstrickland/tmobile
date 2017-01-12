package com.tmobile.subscribers.service;

import com.tmobile.model.Name;
import com.tmobile.model.PhoneNumber;
import com.tmobile.subscribers.model.SubscriberCustomer;
import com.tmobile.subscribers.model.SubscriberAccount;
import com.tmobile.subscribers.model.SubscriberException;

public interface SubscriberBuilder {

    public void setName(Name name) throws SubscriberException;
	public void setName(String firstName, String middleName, String lastName)  throws SubscriberException;
	
    public void setPhoneNumber(PhoneNumber phone)  throws SubscriberException;
	public void setPhoneNumber(String areaCode, String prefix, String lineNumber) throws SubscriberException;
    
    public void setAccount(SubscriberAccount account)  throws SubscriberException;
	public void setAccount(Float amtBalance, Float amtChargePerMinute) throws SubscriberException;
    
    public boolean build() throws SubscriberException;
   
    public boolean isValidSubscriber();

    public SubscriberCustomer releaseSubscriber();
	
}
