package com.tmobile.subscribers.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.tmobile.model.Name;
import com.tmobile.model.PhoneNumber;
import com.tmobile.subscribers.model.SubscriberCustomer;
import com.tmobile.subscribers.model.SubscriberAccount;
import com.tmobile.subscribers.model.SubscriberException;
import com.tmobile.subscribers.model.SubscriberExceptionType;

@Component("subscriberBuilder")
public class SubscriberBuilderImpl implements SubscriberBuilder {

    private SubscriberCustomer subscriber;
    private boolean validSubscriber = false;

	final Logger logger = Logger.getLogger(SubscriberBuilderImpl.class.toString());
	
    private SubscriberBuilderImpl() {
    	this.ensureSubscriber();
    }
	
    private void ensureSubscriber() {
		if(subscriber == null) {
			this.setValidSubscriber(false);
			this.setSubscriber(new SubscriberCustomer());
		}
	}
    
	private void destroySubscriber() {
		this.setValidSubscriber(false);
		this.setSubscriber(null);
	}

	private SubscriberCustomer getSubscriber() {
		return subscriber;
	}

	private void setSubscriber(SubscriberCustomer subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public boolean isValidSubscriber() {
		return validSubscriber;
	}

	private void setValidSubscriber(boolean validSubscriber) {
		this.validSubscriber = validSubscriber;
	}

	@Override
	public void setName(Name name)  throws SubscriberException {
		this.ensureSubscriber();
		this.subscriber.setName(name);
	}

	@Override
	public void setName(String firstName, String middleName, String lastName) throws SubscriberException  {
		this.ensureSubscriber();
		this.subscriber.setName(firstName, middleName, lastName);
	}
	
	@Override
	public void setPhoneNumber(PhoneNumber phone)  throws SubscriberException {
		this.ensureSubscriber();
		this.subscriber.setPhoneNumber(phone);
	}

	@Override
	public void setPhoneNumber(String areaCode, String prefix, String lineNumber) throws SubscriberException {
		this.ensureSubscriber();
		this.subscriber.setPhoneNumber(areaCode, prefix, lineNumber);
	}
	
	@Override
	public void setAccount(SubscriberAccount account)  throws SubscriberException {
		this.ensureSubscriber();
		this.setAccount(account);
	}

	@Override
	public void setAccount(Float amtBalance, Float amtChargePerMinute)  throws SubscriberException {
		this.ensureSubscriber();
		this.subscriber.setAccount(amtBalance, amtChargePerMinute);
	}
	
	@Override
	public boolean build() throws SubscriberException{
		if((this.subscriber != null) && (this.subscriber.getName() != null) && (this.subscriber.getPhoneNumber() != null) && (this.subscriber.getAccount() != null)) {
			this.setValidSubscriber(true);
			return this.isValidSubscriber();
		} else {
			this.setValidSubscriber(false);
			throw new SubscriberException(SubscriberExceptionType.SubscriberIncomplete);
		}
	}

    @Override
	public SubscriberCustomer releaseSubscriber() {
    	SubscriberCustomer subscriber = this.getSubscriber();
    	
    	if(this.isValidSubscriber()) {
        	destroySubscriber();
    		return subscriber;
    	} else {
    		return null;
    	}
	}

    @Override
	 public String toString() {
    	return subscriber.toString();
	 }
    
}
