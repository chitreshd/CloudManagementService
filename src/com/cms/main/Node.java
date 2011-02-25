package com.cms.main;

public class Node {
	
	private String name;	
	private int id;
	private int availableResources;
	private int networkStatus;	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availableResources;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + networkStatus;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (availableResources != other.availableResources)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (networkStatus != other.networkStatus)
			return false;
		return true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAvailableResources() {
		return availableResources;
	}
	public void setAvailableResources(int availableResources) {
		this.availableResources = availableResources;
	}
	public int getNetworkStatus() {
		return networkStatus;
	}
	public void setNetworkStatus(int networkStatus) {
		this.networkStatus = networkStatus;
	}
	
	@Override
	public String toString() {
		return "Node [name=" + name + ", id=" + id + ", availableResources="
				+ availableResources + ", networkStatus=" + networkStatus + "]";
	}
	

}
