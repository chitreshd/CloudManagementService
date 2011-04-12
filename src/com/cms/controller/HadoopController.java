/**
 * 
 */
package com.cms.controller;

import com.cms.bean.JobList;

/**
 * @author Chitresh Deshpande
 * 
 * Copyright 2011, San Jose State University
 *
 */
public class HadoopController {
	private JobList jList = new JobList();
	private static HadoopController instance;
	
	private HadoopController(){
		
	}
	
	public static HadoopController getInstance(){
		if(instance == null)
			instance = new HadoopController();
		return instance;
	}
	
	public JobList getJobs(){
		return jList;
	}	
	
}
