/**
 * 
 */
package com.cms.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chitresh Deshpande
 * 
 *         Copyright 2011, San Jose State University
 *         
 *         There is this problem service which dosent seem to accept List<String>.
 *         Hence I am creating a List<String> but while returning I am returning as an String[].
 *         This is a temp solution. Once I find the solution I will modify the code.
 * 
 */
public class JobList {
	private List<String> jobs = new ArrayList<String>();
	
	public JobList(){
		jobs.add("WordCount");
		jobs.add("PatternFinder");
	}

	public String[] getJobs() {
		Object [] jobObjectArr = jobs.toArray();
		int length = jobObjectArr.length;
		String[] jobString = new String[length];
		for(int i = 0; i < length; i++){
			jobString[i] = jobObjectArr[i].toString();
		}
		return jobString;
	}
	
	public List<String> getJob(){
		return jobs;
	}
	
	public void addJob(String newJob){
		if(newJob == null || newJob.isEmpty())
			throw new IllegalArgumentException("Null job name value is not allowed.");
		if(!jobs.contains(newJob))
			jobs.add(newJob);
	}

	@Override
	public String toString() {
		return "JobList [jobs=" + jobs + "]";
	}
	
	

	
}
