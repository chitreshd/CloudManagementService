/**
 * 
 */
package com.cms.controller;

import java.io.IOException;

import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * @author Chitresh Deshpande
 * 
 *         Copyright 2011, San Jose State University
 * 
 */
public class JobController {
	private static JobController instance;

	private JobController() {

	}

	public static JobController getInstance() {
		if (instance == null)
			instance = new JobController();
		return instance;
	}

	public void execute(String jobTitle, Class<?> outputKeyClass,
			Class<?> outputValueClass, Class<? extends Mapper> mapper,
			Class<? extends Reducer> combiner,Class<? extends Reducer> reducer,
			Class<? extends InputFormat> inputFormat,
			Class<? extends OutputFormat> outputFormat, Path inputPath,
			Path outputPath) throws IOException, InterruptedException,
			ClassNotFoundException {
		Configuration conf = new Configuration();
		conf.addResource(new Path("/home/chitresh/software/hadoop/hadoop-0.20.2/conf/core-site.xml"));	
		
		Job job = new Job(conf, jobTitle);
		
		job.setOutputKeyClass(outputKeyClass);
		job.setOutputValueClass(outputValueClass);

		job.setMapperClass(mapper);
		job.setCombinerClass(combiner);
		job.setReducerClass(reducer);

		job.setInputFormatClass(inputFormat);
		job.setOutputFormatClass(outputFormat);

		FileInputFormat.addInputPath(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		
		//job.submit();
		job.waitForCompletion(true);
	}

}
