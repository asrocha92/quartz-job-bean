package com.quartzjob.job.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.quartzjob.job.jobbean.IntegracaoJob;

@Configuration
public class QuartzConfig {
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Bean
	public Scheduler scheduler() throws Exception{
		Scheduler s = schedulerFactoryBean.getScheduler();
		s.start();
		
		
		JobDetail jobDetail = JobBuilder.newJob(IntegracaoJob.class).withIdentity("integracaoJob" + "JOB", "grupo1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("integracaoJob" + "TRIGGER", "grupo1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
		s.scheduleJob(jobDetail, trigger);
		return s;
	}
	
	public void criarJog(Scheduler sscheduler, Class<? extends Job> obj, String nomeClasse, String grupo, String cronTrigger) throws Exception{
		JobDetail jobDetail = JobBuilder.newJob(obj).withIdentity(nomeClasse + "JOB", grupo).build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(nomeClasse + "TRIGGER", grupo)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronTrigger)).build();
		scheduler().scheduleJob(jobDetail, trigger);
	}
	
	public void stopJob(String nomeClasse, String grupo) throws Exception {

		JobKey jkey = new JobKey(nomeClasse + "JOB", grupo);
		// this.scheduler.pauseTrigger(tkey);
		scheduler().pauseJob(jkey);
		scheduler().deleteJob(jkey);
		System.out.println("stop job : " + nomeClasse + "JOB");
	}

}