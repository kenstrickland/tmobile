package com.tmobile;

import com.tmobile.subscribers.jobs.SubscriberJob;
import com.tmobile.subscribers.test.SubscribersTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class MobileSubscribers {
 
	public static ApplicationContext tMobileAppCntxt = new ClassPathXmlApplicationContext("tmobile.xml");

	public static void main(String[] args) {

		/* Setup and Start Subscriber Scheduled Job */

		SchedulerFactory tMobileSchedFact = new StdSchedulerFactory();
		Scheduler tMobileSched;

		JobDetail tMobilejob = newJob(SubscriberJob.class)
	    	      .withIdentity("TMobileJob", "TMobileReports")
	    	      .build();    	
	    	  
    	simpleSchedule();
	    	
		Trigger tMobileTrigger = newTrigger()
	    			.withIdentity("TMobileTrigger", "TMobileReports")
	    			.startNow()
	    			.withSchedule(SimpleScheduleBuilder.repeatMinutelyForTotalCount(5, 1))
	    			.build();

		try {
			tMobileSched = tMobileSchedFact.getScheduler();
			tMobileSched.start();
			tMobileSched.scheduleJob(tMobilejob, tMobileTrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		System.out.println("**** Begin Main ****");
		System.out.println(" ");
		
		/* Run all Subscriber Tests using the Subscriber Builder and Service */
		Result result = JUnitCore.runClasses(SubscribersTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	
		System.out.println(" ");
		System.out.println("**** End Main  ****");
		System.out.println(" ");
		
	}

}
