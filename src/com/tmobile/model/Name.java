package com.tmobile.model;

import java.io.Serializable;

import com.tmobile.subscribers.model.SubscriberException;
import com.tmobile.subscribers.model.SubscriberExceptionType;

public class Name  implements Serializable  {

	private static final long serialVersionUID = 3545736989826786043L;

	public Name(String firstName, String middleName, String lastName)  throws SubscriberException {

		if(firstName == null) {
			throw new SubscriberException(SubscriberExceptionType.InValidFirstName);
		}

		if(lastName == null) {
			throw new SubscriberException(SubscriberExceptionType.InValidLastName);
		}

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	private String firstName;
	private String middleName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public String getName() {
		return lastName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Name ["
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (middleName != null ? "middleName=" + middleName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName : "") + "]";
	}
	
}
