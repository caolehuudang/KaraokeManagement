package com.mobile.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class User extends BaseAbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "UUID")
	private String uuid;
	
	@Column(name= "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	@JsonIgnore
	private String password;

	@Column(name = "EMAIL")
	private String email;
	
	@Column(name="FULL_NAME")
	private String fullName;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="address")
	private String address;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name = "IMAGE")
	private String image;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "code")
	private String code;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<UserRole> userRoles;
	
	@OneToOne(mappedBy = "user")
	private UserPositionStore userPositionStore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserPositionStore getUserPositionStore() {
		return userPositionStore;
	}

	public void setUserPositionStore(UserPositionStore userPositionStore) {
		this.userPositionStore = userPositionStore;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}