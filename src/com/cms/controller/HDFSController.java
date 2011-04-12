/**
 * 
 */
package com.cms.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

/**
 * @author Chitresh Deshpande
 * 
 * Copyright 2011, San Jose State University
 * 
 * This class facilitates important HDFS commands
 * 
 * Design consideration:
 * Static - Easy way to ensure single instance of this class
 * Singleton - With this I can implement interfaces thus opening 
 * options of passing it as a interface.
 * 
 * For now going with singleton, as it seems to be clean way.
 * 
 * References:
 * http://www.kodejava.org/examples/244.html - for date format conversion.
 * http://www.ics.uci.edu/~abehm/hadoop.html#howto_hdfs_java_app - for tips on accessing HDFS from java app.
 *
 */
public class HDFSController {
	
	/**
	 * HDFS instance which is used to implement Singleton pattern.
	 */
	private static HDFSController instance;
	/**
	 * 
	 */
	private Configuration conf;
	/**
	 * 
	 */
	private FileSystem fs;
	
	/**
	 * @throws IOException
	 */
	private HDFSController() throws IOException{
		conf = new Configuration();
		conf.addResource(new Path("/home/chitresh/software/hadoop/hadoop-0.20.2/conf/core-site.xml"));
		conf.addResource(new Path("/home/chitresh/software/hadoop/hadoop-0.20.2/conf/mapred-site.xml"));
		conf.addResource(new Path("/home/chitresh/software/hadoop/hadoop-0.20.2/conf/hdfs-site.xml"));
		fs = FileSystem.get(conf);
	}
	
	/**
	 * @return Singleton HDFS instance
	 * @throws IOException
	 */
	public static HDFSController getInstance() throws IOException{
		if(instance == null)
			return new HDFSController();
		else
			return instance;
	}
	
	/**
	 * @param file - Path of the file/dir of which one wants content listings
	 * @return - List of String describing the content listings.
	 * @throws IOException
	 */
	public List<String> ls(String file) throws IOException{
		FileStatus[] status = fs.listStatus(new Path(file));
		List<String> fileList = new ArrayList<String>();
		
		for(FileStatus current:status){
			fileList.add(getPrintFormat(current.isDir(), true, current.getPermission(), current.getOwner(),
										current.getGroup(), current.getBlockSize(), current.getModificationTime(), 
										current.getPath()));			
		}
		
		return fileList;
	}
	
	/**
	 * @param src - HDFS src path
	 * @param dest - dest path on Local machine.
	 * @throws IOException
	 */
	public void copyToLocal(String src, String dest) throws IOException{
		fs.copyToLocalFile(new Path(src), new Path(dest));
	}
	
	/**
	 * @param src - src path on Local machine.
	 * @param dest - dest path on HDFS
	 * @throws IOException
	 */
	public void copyFromLocal(String src, String dest) throws IOException{
		fs.copyFromLocalFile(new Path(src), new Path(dest));
	}
	
	/**
	 * @return - same as pwd in linux.
	 */
	public String pwd(){
		return fs.getWorkingDirectory().toString();
	}
	
	/**
	 * @param isDir
	 * @param isFile
	 * @param permissions
	 * @param owner
	 * @param group
	 * @param size
	 * @param modtime
	 * @param path
	 * @return
	 */
	public String getPrintFormat(boolean isDir,boolean isFile,FsPermission permissions, String owner, String group, long size,long modtime,Path path){
		return getPrintFormat(isDir, isFile, permissions.toString(), owner, group, size, modtime, path.toString());
		
	}
	
	/**
	 * @param path
	 * @param recurrsive
	 * @throws IOException
	 */
	public boolean delete(String path, boolean recurrsive) throws IOException{
		return fs.delete(new Path(path), recurrsive);
	}
	
	/**
	 * @param path
	 * @throws IOException
	 */
	public boolean  mkdir(String path) throws IOException{
		return fs.mkdirs(new Path(path));
	}
	
	/**
	 * @param isDir
	 * @param isFile
	 * @param permissions
	 * @param owner
	 * @param group
	 * @param size
	 * @param modtime
	 * @param path
	 * @return
	 */
	public String getPrintFormat(boolean isDir,boolean isFile,String permissions, String owner, String group, long size,long modtime,String path){
		String temp = "";
		String separator = " ";
		if(isDir)
			temp = temp + "d";
		else if(isFile)
			temp = temp + "-";
		else
			temp = temp + "s";
		
		temp = temp + permissions + separator;
		temp = temp + owner + separator;
		temp = temp + group + separator;
		temp = temp + size + separator;
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(modtime);
		
		temp = temp + formatter.format(cal.getTime()) + separator;
		temp = temp + path.toString();
		
		return temp;
	}

}
