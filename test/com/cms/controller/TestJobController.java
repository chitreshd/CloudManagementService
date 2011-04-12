/**
 * 
 */
package com.cms.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * @author Chitresh Deshpande
 * 
 *         Copyright 2011, San Jose State University
 * 
 */
public class TestJobController {
	private static final Log log = LogFactory.getLog(TestJobController.class);
	
	@Before
	public void setup() throws IOException{
		HDFSController hdfs = HDFSController.getInstance();
		log.info("Current Working Directory: " + hdfs.pwd());
		log.info("Copying test file from local to HDFS.");
		hdfs.mkdir("/user/chitresh/input");
		hdfs.copyFromLocal("/home/chitresh/workspace/HadoopController/wordCountTestInput.txt", "/user/chitresh/input");
		hdfs.delete("/user/chitresh/output_test", true);
		log.info("/user/chitresh/output_test .... deleted");
		List<String> fileList = hdfs.ls("/user/chitresh/");
		log.info(fileList);
	}
	

	@Test
	public void testExecute() throws IOException, InterruptedException, ClassNotFoundException {
		HDFSController hdfs = HDFSController.getInstance();
		List<String> fileList = hdfs.ls("/user/chitresh/");
		log.info(fileList);
		JobController controller = JobController.getInstance();
		controller.execute("WordCount", Text.class, IntWritable.class, 
				com.cms.controller.TestJobController.Map.class, 
				com.cms.controller.TestJobController.Reduce.class, 
				com.cms.controller.TestJobController.Reduce.class,
				TextInputFormat.class, TextOutputFormat.class, 
				new Path("/user/chitresh/input"), 
				new Path("/user/chitresh/output_test"));
		
		hdfs.copyToLocal("/user/chitresh/output_test", "/home/chitresh/test_output");
		
	}

	public static class Map extends
			Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			log.info("Map is being called");
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				word.set(tokenizer.nextToken());
				context.write(word, one);
			}
		}
	}

	public static class Reduce extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			log.info("Reduce is being called");
			/*while (values.hasNext()) {
				sum += values.next().get();
			}*/
			for (IntWritable val : values) {
		        sum += val.get();
			}
			result.set(sum);
			context.write(key, new IntWritable(sum));
		}
	}
	
	@After
	public void cleanUp() throws IOException{
		log.info("Starting cleanup .....");
		HDFSController hdfs = HDFSController.getInstance();
		if(hdfs.delete("/user/chitresh/input", true))
			log.info("Test input dir deleted from HDFS");
		List<String> fileList = hdfs.ls("/user/chitresh/output_test");
		log.info(fileList);
		hdfs.delete("/user/chitresh/output_test", true);
		log.info("/user/chitresh/output_test .... deleted");
	}
}
