package com.mobile.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseAbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdDate", nullable = false)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedDate", nullable = false)
	private Date updatedDate;
	
	@PrePersist
	protected void onCreate() {
		createdDate = updatedDate = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}
	
}
