package com.tmobile.model;

import java.io.Serializable;

import org.apache.commons.validator.routines.FloatValidator;

public class Account implements Serializable  {

	private static final long serialVersionUID = -3122490884275377193L;

	private Float amtBalance;
	
	public Account() {
		this.amtBalance = new Float(0);
	}
	
	public Account(Float amtBalance) {
		this.amtBalance = amtBalance;
	}
	
	public Float getAmtBalance() {
		return amtBalance;
	}
	public void setAmtBalance(Float amtBalance) {
		this.amtBalance = amtBalance;
	}
	
	public boolean isValid(Float value) {
		return FloatValidator.getInstance().isInRange(value, 0.00, 1000.00);
	}

	@Override
	public String toString() {
		return "Account ["
				+ (amtBalance != null ? "amtBalance=" + amtBalance : "") + "]";
	}

}
