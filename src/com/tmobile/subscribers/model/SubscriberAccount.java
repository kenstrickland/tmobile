package com.tmobile.subscribers.model;

import java.util.logging.Logger;

import com.tmobile.model.Account;

public class SubscriberAccount  extends Account  {

	private static final long serialVersionUID = -6667935482411811414L;
	public static final float STANDARD_CPM = (float) 5.00;
	private Float amtChargePerMinute;
	
	static Logger logger = Logger.getLogger(SubscriberAccount.class.toString());
	
	public SubscriberAccount() {
		this.amtChargePerMinute = new Float(STANDARD_CPM);
	}

	public SubscriberAccount(Float amtChargePerMinute) {
		this.amtChargePerMinute = amtChargePerMinute;
	}

	public SubscriberAccount(Float amtBalance, Float amtChargePerMinute) {
		super(amtBalance);
		this.amtChargePerMinute = amtChargePerMinute;
	}
	
	public Float getAmtChargePerMinute() {
		return amtChargePerMinute;
	}

	public void setAmtChargePerMinute(Float amtChargePerMinute) {
		this.amtChargePerMinute = amtChargePerMinute;
	}
	
	public void decrementByCPM(Integer minutes) throws SubscriberException {
		Float amtToRemove;
		
		if((minutes != null) && (minutes.intValue() > 0)) {
			amtToRemove = new Float(minutes *  getAmtChargePerMinute());
			
			if(isValid(this.getAmtBalance() - amtToRemove)) {
				this.setAmtBalance(this.getAmtBalance() - amtToRemove);
			} else {
				throw new SubscriberException(SubscriberExceptionType.InValidAccountBalance);
			}
			
		} else {
			logger.info("Adjusting charge per minute, but nothing to adjust.");
		}
	
	}
	
	public Float getMinutesLeft() {
		if((this.getAmtChargePerMinute() != null) && (this.getAmtChargePerMinute() > 0.00F)) {
			return (getAmtBalance() / getAmtChargePerMinute());
		} else {
			return 0.00F;
		}
	}

	@Override
	public String toString() {
		return super.toString() + " SubscriberAccount ["+ (amtChargePerMinute != null ? "amtChargePerMinute="+ amtChargePerMinute : "") + "]";
	}
	
}
