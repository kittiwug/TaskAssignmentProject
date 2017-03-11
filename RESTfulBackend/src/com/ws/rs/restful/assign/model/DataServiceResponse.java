package com.ws.rs.restful.assign.model;

import java.io.Serializable;
import java.util.List;

public class DataServiceResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<TaskM> taskList;
	
	private String operationName;
	
	private String statusCode;
	
	private String statusMessage;

	public List<TaskM> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskM> taskList) {
		this.taskList = taskList;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	
	
	
}
