package com.tmobile.subscribers.model;

import com.tmobile.model.AbstractCustomer;

public class SubscriberCustomer extends AbstractCustomer {

	private static final long serialVersionUID = -619172482605810681L;
	private SubscriberAccount account;
	
	public SubscriberCustomer() {
	}

	public void setAccount(SubscriberAccount account) {
		this.account = account;
	}

	public void setAccount(Float amtBalance, Float amtChargePerMinute) {
		this.account = new SubscriberAccount(amtBalance, amtChargePerMinute);
	}
	
	public SubscriberAccount getAccount() {
		return account;
	}
	
	@Override
	public boolean equals(Object phoneNumber){
		SubscriberCustomer f = (SubscriberCustomer) phoneNumber;
		return getPhoneNumber().equals(f.getPhoneNumber());
	}
		
	@Override
	public int hashCode(){
		return getPhoneNumber().hashCode();
	}	

	@Override
	public String toString(){
		return " " + this.getName() + " " + this.getPhoneNumber() + " " + this.getAccount();
	}
				
}
