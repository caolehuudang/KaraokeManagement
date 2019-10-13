package com.karaoke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Long id;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PASSWORD")
	@JsonIgnore
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "IMAGE")
	private String image;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "ROLE")
	private String role;

	@ManyToOne
	@JoinColumn(name = "FK_VIP_ID")
	private Vip vip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Vip getVip() {
		return vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}

	public User(Long id, String username, String password, String email, String image, String status, String role,
			Vip vip) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.image = image;
		this.status = status;
		this.role = role;
		this.vip = vip;
	}

	public User() {
		super();
	}
	
	

}