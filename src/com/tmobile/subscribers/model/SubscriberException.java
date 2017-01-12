package com.tmobile.subscribers.model;

public class SubscriberException extends Exception {

	private static final long serialVersionUID = -8684097071808707701L;

	private SubscriberExceptionType type;

	public SubscriberException(SubscriberException se) {
		this.type = se.type;
	}
	
	public SubscriberException(SubscriberExceptionType type) {
		this.type = type;
	}

	public SubscriberExceptionType getType() {
		return type;
	}
	
	@Override
	public String toString(){
		return this.getType().toValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object objToCompare) {
		if (this == objToCompare)
			return true;
		if (objToCompare == null)
			return false;
		if (getClass() != objToCompare.getClass())
			return false;
		SubscriberException other = (SubscriberException) objToCompare;
		if (type != other.type)
			return false;
		return true;
	}
}
