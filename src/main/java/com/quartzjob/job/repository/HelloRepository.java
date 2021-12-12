package com.quartzjob.job.repository;

import org.springframework.stereotype.Service;

@Service
public class HelloRepository {

	public String getHello() {
		return "Hello word!1";
	}
}
