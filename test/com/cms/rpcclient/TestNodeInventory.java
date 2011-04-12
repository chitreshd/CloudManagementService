/**
 * 
 */
package com.cms.rpcclient;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.cms.bean.JobList;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Chitresh Deshpande
 * 
 *         Copyright 2011, San Jose State University
 * 
 */
public class TestNodeInventory {
	private Logger log = Logger.getLogger(this.getClass());

	@Test
	public void testGetNode() throws AxisFault {

		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();

		EndpointReference targetEPR = new EndpointReference(
				"http://localhost:8080/axis2/services/ClusterManagementService/getNodeName");

		options.setTo(targetEPR);

		QName opGetNode = new QName("http://service.cms.com", "getNodeName");

		String input = " Awesomeness!!";

		Object[] opGetNodeArgs = new Object[] { input };
		Class[] returnTypes = new Class[] { String.class };

		Object[] responses = serviceClient.invokeBlocking(opGetNode,
				opGetNodeArgs, returnTypes);

		String output = (String) responses[0];

		if (output == null) {
			log.error("Got null response.");
			return;
		}

		System.out.println("Response from Service: " + output);
	}

	@Test
	public void testGetHadoopJobs() throws AxisFault {

		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();

		EndpointReference targetEPR = new EndpointReference(
				"http://localhost:8080/axis2/services/ClusterManagementService/getHadoopJobs");

		options.setTo(targetEPR);

		QName opGetNode = new QName("http://service.cms.com", "getHadoopJobs");

		Object[] opGetNodeArgs = new Object[] {  };
		Class[] returnTypes = new Class[] { JobList.class };

		Object[] responses = serviceClient.invokeBlocking(opGetNode,
				opGetNodeArgs, returnTypes);
		
		JobList jList = (JobList) responses[0];
		System.out.println("Hadoop Job Response: " + jList);
		
	}

}
