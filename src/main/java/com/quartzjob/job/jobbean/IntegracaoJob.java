package com.quartzjob.job.jobbean;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartzjob.job.repository.HelloRepository;

@DisallowConcurrentExecution
public class IntegracaoJob implements Job {

	@Autowired
	private HelloRepository integracaoRhRepository;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("aqui");

		try {
			System.out.println(integracaoRhRepository.getHello());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
