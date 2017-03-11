package com.ws.rs.restful.assign.model;

import java.io.Serializable;
import java.util.HashMap;

public class ObjectTaskM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, TaskM> hashMapTask;

	public HashMap<String, TaskM> getHashMapTask() {
		return hashMapTask;
	}

	public void setHashMapTask(HashMap<String, TaskM> hashMapTask) {
		this.hashMapTask = hashMapTask;
	}
	
	
}
