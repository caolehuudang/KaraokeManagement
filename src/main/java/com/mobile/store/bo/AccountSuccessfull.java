package com.mobile.store.bo;

public class AccountSuccessfull {

	private String message;
	
	private Boolean isvalid;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public AccountSuccessfull(String message, Boolean isvalid) {
		super();
		this.message = message;
		this.isvalid = isvalid;
	}

	public AccountSuccessfull() {
		super();
	}
	
}
