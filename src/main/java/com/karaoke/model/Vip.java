package com.karaoke.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_VIP")
public class Vip implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
    private Long id;
	
	@Column(name = "LEVEL_USER")
	private String level;
	
	@Column(name = "TOTAL")
	private Double total;
	
	@Column(name = "PERCENT_DISCOUNT")
	private int percentDiscount;
	
	@Column(name = "STATUS")
	private String status;
	
	@OneToMany(mappedBy = "vip",fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private List<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(int percentDiscount) {
		this.percentDiscount = percentDiscount;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vip(Long id, String level, Double total, int percentDiscount, String status, List<User> users) {
		super();
		this.id = id;
		this.level = level;
		this.total = total;
		this.percentDiscount = percentDiscount;
		this.status = status;
		this.users = users;
	}

	public Vip() {
		super();
	}

}
