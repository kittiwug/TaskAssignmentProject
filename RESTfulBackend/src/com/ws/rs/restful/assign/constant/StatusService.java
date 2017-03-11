package com.ws.rs.restful.assign.constant;

public enum StatusService {

	SUCCESS("0"), ERROR("1");

	private String code;

	StatusService(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
