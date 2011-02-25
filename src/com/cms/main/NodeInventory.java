package com.cms.main;

import java.util.HashMap;

public class NodeInventory {
	
	private HashMap<String, Node> nodeRepo = new HashMap<String, Node>();	
	
	public String getNodeName(String input){
		/*Node temp = (Node) nodeRepo.get(input);
		return temp.getName();*/
		return "Node1";
	}

}
