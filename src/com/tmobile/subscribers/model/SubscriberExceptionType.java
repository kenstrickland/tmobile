package com.tmobile.subscribers.model;

public enum SubscriberExceptionType {

	InValidAreaCode(100,"Invalid Subscriber Definition; Invalid Area Code"),
	InValidPhonePrefix(100,"Invalid Subscriber Definition; Invalid Phone Prefix"),
	InValidLineNumber(100,"Invalid Subscriber Definition; Invalid Phone Line Number"),
	InValidFirstName(100,"Invalid Subscriber Definition; Invalid First Name"),
	InValidLastName(100,"Invalid Subscriber Definition; Invalid Last Name"),
	SubscriberIncomplete(100,"Invalid Subscriber Definition; Subscriber Incomplete"),
	SubscriberDoesNotExists(200,"Subscriber Does Not Exist!"),
	InValidAccountBalance(300,"Balance is out of range!"),
	SubscriberExists(400,"Subscriber Already Exists");
	
	private final int code;
	private final String message;
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	private SubscriberExceptionType(final int code, final String message) {
		this.message = message;
		this.code = code;
	}

	public String toValue() {
		return " SubscriberExceptionType [code=" + code + ", "
				+ (message != null ? "message=" + message : "") + "]";
	}
	
}
