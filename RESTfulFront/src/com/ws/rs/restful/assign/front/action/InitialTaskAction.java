package com.ws.rs.restful.assign.front.action;

import com.opensymphony.xwork2.ActionSupport;

public class InitialTaskAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String redirectPage;
	
	private String idTask;
	
	public String execute() {
		System.out.println("Redirect Page = " + redirectPage + ", idTask = " + idTask);
		if (redirectPage != null) {
			return redirectPage;
		}
		
		return SUCCESS;
	}

	
	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public String getRedirectPage() {
		return redirectPage;
	}

	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}

}
