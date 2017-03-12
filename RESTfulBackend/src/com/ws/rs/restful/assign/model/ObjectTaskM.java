package com.ws.rs.restful.assign.model;

import java.io.Serializable;
import java.util.HashMap;

public class ObjectTaskM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, TaskM> hashMapTask;

	public HashMap<Integer, TaskM> getHashMapTask() {
		return hashMapTask;
	}

	public void setHashMapTask(HashMap<Integer, TaskM> hashMapTask) {
		this.hashMapTask = hashMapTask;
	}
	
	
}
