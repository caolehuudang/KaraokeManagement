package com.karaoke.bo;

public class ResponseMessage<T> {

	private T entity;
	
	private String message;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseMessage(T entity, String message) {
		super();
		this.entity = entity;
		this.message = message;
	}
	
}
