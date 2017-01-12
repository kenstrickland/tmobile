package com.tmobile.model;


public enum PhoneNumberType {

	Cell("Cell Phone");
	
	private final String value;
	
	private PhoneNumberType(final String value) {
		this.value = value;
	}
	
	public String toValue() {
		return value;
	}
	
}
