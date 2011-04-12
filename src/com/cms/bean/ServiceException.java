/**
 * 
 */
package com.cms.bean;

/**
 * @author Chitresh Deshpande
 * 
 * Copyright 2011, San Jose State University
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6631733327408677550L;
	
	private String errorMessage;
	
	
	/**
	 * @param errorMessage
	 */
	public ServiceException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public StackTraceElement[] getStackTrace(){
		return this.getStackTrace();		
	}

}
