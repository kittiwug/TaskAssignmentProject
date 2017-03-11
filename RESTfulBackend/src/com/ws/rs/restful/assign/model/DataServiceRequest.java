package com.ws.rs.restful.assign.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataServiceRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement
	private List<TaskM> taskList;
	
	@XmlElement
	private String operationName;
	
	@XmlElement
	private String statusCode;
	
	@XmlElement
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
