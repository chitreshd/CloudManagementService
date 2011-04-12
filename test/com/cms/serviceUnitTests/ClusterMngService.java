/**
 * 
 */
package com.cms.serviceUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cms.bean.JobList;
import com.cms.bean.ServiceException;
import com.cms.service.ClusterManagementService;

/**
 * @author Chitresh Deshpande
 * 
 * Copyright 2011, San Jose State University
 *
 */
public class ClusterMngService {
	
	@Test
	public void testGetHadoopJobs(){
		ClusterManagementService service = new ClusterManagementService();
		JobList jobs = service.getHadoopJobs();
		String [] jobArr = jobs.getJobs();
		assertEquals(2,jobArr.length);
		assertEquals("WordCount",jobArr[0]);
	}
	
	@Test
	public void testSubmitHadoopJobs(){
		ClusterManagementService service = new ClusterManagementService();
		try {
			assertTrue(service.submitJob("WordCount"));
		} catch (ServiceException e) {
			assertTrue(false);
		}
	}
	
	@Test(expected=ServiceException.class)
	public void testSubmitHadoopJobsException() throws Exception{
		ClusterManagementService service = new ClusterManagementService();
		service.submitJob("Word");		
	}
	
	@Test
	public void testGetNodeName(){
		ClusterManagementService service = new ClusterManagementService();
		try {
			String temp = service.getNodeName(" abc");			
			assertEquals("Your input abc",temp);
		} catch (ServiceException e) {
			assertTrue(false);
		}
	}

}
