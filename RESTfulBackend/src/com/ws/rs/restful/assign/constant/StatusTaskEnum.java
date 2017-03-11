package com.ws.rs.restful.assign.constant;

public enum StatusTaskEnum {

	P("Pending"), D("Done");

	private String desc;

	StatusTaskEnum(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return desc;
	}
}
