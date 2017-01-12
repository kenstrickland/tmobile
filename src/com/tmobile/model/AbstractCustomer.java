package com.tmobile.model;

import java.io.Serializable;

import com.tmobile.subscribers.model.SubscriberException;

public abstract class AbstractCustomer implements Serializable  {

	private static final long serialVersionUID = -8341504303526817862L;
	private Name name;
	private PhoneNumber phoneNumber;
	private Account account;

	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	
	public void setName(String firstName, String middleName, String lastName)  throws SubscriberException  {
		this.name = new Name(firstName, middleName, lastName);
	}
	
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	 
	public void setPhoneNumber(String areaCode, String prefix, String lineNumber) throws SubscriberException {
		try {
			this.phoneNumber = new PhoneNumber(areaCode, prefix, lineNumber);
		} 
		catch (SubscriberException se) {
			throw new SubscriberException(se);
		}
	}

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
