package com.cms.service;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.cms.bean.JobList;
import com.cms.bean.ServiceException;
import com.cms.controller.HadoopController;

public class ClusterManagementService {

	private Logger log = Logger.getLogger(this.getClass());

	public String getNodeName(String input) throws ServiceException {
		if (input == null)
			throw new ServiceException("Null value not allowed.");
		log.info("getNodeName got called.");
		String temp = "Your input" + input;
		return temp;
	}

	public JobList getHadoopJobs() {
		log.info("getHadoopJobs got called.");
		return HadoopController.getInstance().getJobs();
	}
	
	public boolean submitJob(String jobName) throws ServiceException{
		if(jobName == null || jobName.isEmpty())
			throw new ServiceException("Empty or null jobName is not allowed.");
		JobList jList = HadoopController.getInstance().getJobs();
		List<String> jobList = Arrays.asList(jList.getJobs());
		if(jobList.contains(jobName))
			log.info("Job was submitted");			
		else
			throw new ServiceException("Job not found. Check the list of implemented jobs");
		return true;
	}

}
