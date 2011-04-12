/**
 * 
 */
package com.cms.controller;

import java.io.IOException;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

/**
 * @author Chitresh Deshpande
 * 
 * Copyright 2011, San Jose State University
 *
 */
public class TestHDFSController {
	private static Logger log = LogManager.getLogger(TestHDFSController.class);
	
	@Test
	public void testGetPrintFormat(){
		
		try {
			HDFSController hdfs = HDFSController.getInstance();
			String print = hdfs.getPrintFormat(true, false,"rwxr--r--", "chitresh", "chitresh", 2345, System.currentTimeMillis(), "/user/chitresh/gutenberg");
			System.out.println(print);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testls(){
		try{
			HDFSController hdfs = HDFSController.getInstance();
			List<String> fileList = hdfs.ls("/user/chitresh/");
			System.out.println(fileList);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testpwd(){
		try {
			HDFSController hdfs = HDFSController.getInstance();
			System.out.println("Current Working Directory: " + hdfs.pwd());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCopyFromLocal(){
		try {
			HDFSController hdfs = HDFSController.getInstance();
			hdfs.copyFromLocal("/home/chitresh/sampleFile.txt", "/user/chitresh");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCopyToLocal(){
		try {
			HDFSController hdfs = HDFSController.getInstance();
			hdfs.copyToLocal("/user/chitresh/sampleFile.txt", "/home/chitresh/software");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete(){
		try {
			HDFSController hdfs = HDFSController.getInstance();
			List<String> fileList = hdfs.ls("/user/chitresh/");
			log.info(fileList);
			if(hdfs.delete("/user/chitresh/sampleFile.txt", false))
				log.info("sampleFile.txt was deleted...");
			else
				log.error("sampleFile.txt was not deleted...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMkdir(){
		try {
			HDFSController hdfs = HDFSController.getInstance();
			if(hdfs.mkdir("/user/chitresh/test"))
				log.info("Created directory ... /user/chitresh/test");
			else
				log.error("Test dir could not be deleted");
			List<String> fileList = hdfs.ls("/user/chitresh/");
			log.info(fileList);
			log.info("Cleaning up ....");
			if(hdfs.delete("/user/chitresh/wordCountTestInput.txt", false))
				log.info("/user/chitresh/wordCountTestInput.txt .... deleted");
			else
				log.error("Test file could not be deleted");
			if(hdfs.delete("/user/chitresh/test", true))
				log.info("/user/chitresh/test .... deleted");
			else
				log.error("Test dir could not be deleted");
			fileList = hdfs.ls("/user/chitresh/");
			log.info(fileList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void mapRedSetup() throws IOException{
		HDFSController hdfs = HDFSController.getInstance();
		if(hdfs.mkdir("/user/chitresh/test"))
			log.info("Created directory ... /user/chitresh/test");
		else
			log.error("Test dir could not be created");
		hdfs.copyFromLocal("/home/chitresh/workspace/HadoopController/wordCountTestInput.txt", "/user/chitresh/test");
		hdfs.delete("/user/chitresh/output_test", true);
		List<String> fileList = hdfs.ls("/user/chitresh/test");
		System.out.println("Input Dir Listing: " + fileList);
	}
	
	

}
