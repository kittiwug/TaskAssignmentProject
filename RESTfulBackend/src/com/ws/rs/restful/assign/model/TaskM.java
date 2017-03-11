package com.ws.rs.restful.assign.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "taskm")
public class TaskM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idTask;
	private String subjectTask;
	private String subjectDetail;
	private String status;
	private String startDate;
	private String endDate;
	private String dateCreate;
	private String dateUpdate;

	public TaskM() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getIdTask() {
		return idTask;
	}



	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}



	public String getSubjectTask() {
		return subjectTask;
	}

	public void setSubjectTask(String subjectTask) {
		this.subjectTask = subjectTask;
	}

	public String getSubjectDetail() {
		return subjectDetail;
	}

	public void setSubjectDetail(String subjectDetail) {
		this.subjectDetail = subjectDetail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
