/**
 * 
 */
package com.cms.stubtesting;

import java.rmi.RemoteException;



import main.clustermanagementservice.ClusterManagementServiceStub;
import main.clustermanagementservice.ClusterManagementServiceStub.GetHadoopJobsResponse;
import main.clustermanagementservice.ClusterManagementServiceStub.GetNodeName;
import main.clustermanagementservice.ClusterManagementServiceStub.GetNodeNameResponse;
import main.clustermanagementservice.ServiceException;

import org.apache.axis2.AxisFault;
import org.junit.Test;

/**
 * @author Chitresh Deshpande
 * 
 * Copyright 2011, San Jose State University
 *
 */
public class TestService {
	
	@Test
	public void testGetNameNode() throws RemoteException, ServiceException{
		String targetEPR = "http://localhost:8080/axis2/services/ClusterManagementService";
		ClusterManagementServiceStub stub = new ClusterManagementServiceStub(targetEPR);	
		GetNodeName req = new GetNodeName();
		req.setInput(" Awesome!!");
		GetNodeNameResponse resp = new GetNodeNameResponse();
		resp = stub.getNodeName(req);
		System.out.println("Response from stub: " + resp.get_return());
		GetHadoopJobsResponse jbresp = new GetHadoopJobsResponse();
		jbresp = stub.getHadoopJobs();
		
	}
}
