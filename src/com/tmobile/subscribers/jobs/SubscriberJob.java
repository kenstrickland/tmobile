package com.tmobile.subscribers.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tmobile.MobileSubscribers;
import com.tmobile.subscribers.service.SubscriberService;

public class SubscriberJob implements Job  {

	public SubscriberJob() {
    }

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SubscriberService subscriberService = (SubscriberService) MobileSubscribers.tMobileAppCntxt.getBean("subscriberService");
		subscriberService.displaySubscribers();
	}
}
