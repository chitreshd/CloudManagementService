/**
 * 
 */
package com.cms.bean;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author Chitresh Deshpande
 * 
 * Copyright 2011, San Jose State University
 *
 */
public class TestJobList {

	@Test
	public void testConstructedBean(){
		JobList jobList = new JobList();
		assertEquals(2,jobList.getJobs().length);
		assertEquals("JobList [jobs=[WordCount, PatternFinder]]",jobList.toString());
		jobList.addJob("WordPad");
		assertEquals(3,jobList.getJobs().length);
		assertEquals("JobList [jobs=[WordCount, PatternFinder, WordPad]]",jobList.toString());		
	}
}
