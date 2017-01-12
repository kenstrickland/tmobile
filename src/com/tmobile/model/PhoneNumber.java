package com.tmobile.model;

import java.io.Serializable;

import org.apache.commons.validator.routines.IntegerValidator;

import com.tmobile.subscribers.model.SubscriberException;
import com.tmobile.subscribers.model.SubscriberExceptionType;

public class PhoneNumber implements Serializable {

	private static final long serialVersionUID = -1182423789155561686L;
	private String areaCode;
	private String prefix;
	private String lineNumber;
	private PhoneNumberType phoneType;
	
	@SuppressWarnings("unused")
	private PhoneNumber() {
	}
	
	public PhoneNumber(String areaCode, String prefix, String lineNumber) throws SubscriberException {
		
		if(areaCode == null) {
			throw new SubscriberException(SubscriberExceptionType.InValidAreaCode);
		}

		if(areaCode.length() != 3) {
			throw new SubscriberException(SubscriberExceptionType.InValidAreaCode);
		}
		
		if(!isValid(areaCode)) {
			throw new SubscriberException(SubscriberExceptionType.InValidAreaCode);
		}
		
		if(prefix == null) {
			throw new SubscriberException(SubscriberExceptionType.InValidPhonePrefix);
		}

		if(prefix.length() != 3) {
			throw new SubscriberException(SubscriberExceptionType.InValidPhonePrefix);
		}
		
		if(!isValid(prefix)) {
			throw new SubscriberException(SubscriberExceptionType.InValidPhonePrefix);
		}

		if(lineNumber == null) {
			throw new SubscriberException(SubscriberExceptionType.InValidLineNumber);
		}

		if(lineNumber.length() != 4) {
			throw new SubscriberException(SubscriberExceptionType.InValidLineNumber);
		}

		if(!isValid(lineNumber)) {
			throw new SubscriberException(SubscriberExceptionType.InValidLineNumber);
		}
		
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.lineNumber = lineNumber;
		this.phoneType = PhoneNumberType.Cell;
	}
	
	public PhoneNumberType getPhoneType() {
		return phoneType;
	}
	
	public void setPhoneType(PhoneNumberType phoneType) {
		this.phoneType = phoneType;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public boolean isValid(String value) {
		return IntegerValidator.getInstance().isValid(value);
	}

	@Override
	public boolean equals(Object phoneNumber){
		PhoneNumber f = (PhoneNumber)phoneNumber;
		return this.toString().equals(f.toString());
	}
	
	@Override
	public int hashCode(){
		return this.toString().hashCode();
	}	
		
	@Override
	public String toString() {
		return "PhoneNumber ["
				+ (areaCode != null ? "areaCode=" + areaCode + ", " : "")
				+ (prefix != null ? "prefix=" + prefix + ", " : "")
				+ (lineNumber != null ? "lineNumber=" + lineNumber : "") + "]";
	}
}
