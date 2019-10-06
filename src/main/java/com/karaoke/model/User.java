package com.karaoke.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "USER_NAME")
	private String username;
	
	@Column(name = "PASSWORD")
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
