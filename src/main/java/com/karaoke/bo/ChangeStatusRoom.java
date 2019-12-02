package com.karaoke.bo;

public class ChangeStatusRoom {

	private Long id;
	
	private String status;
	
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ChangeStatusRoom(Long id, String status, String type) {
		super();
		this.id = id;
		this.status = status;
		this.type = type;
	}

	public ChangeStatusRoom() {
		super();
	}

	@Override
	public String toString() {
		return "ChangeStatusRoom [id=" + id + ", status=" + status + ", type=" + type + "]";
	}
	
	
}
