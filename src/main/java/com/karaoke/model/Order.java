package com.karaoke.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "T_ORDER")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
    private Long id;
	
	@Column(name = "START_TIME")
	private Timestamp start;
	
	@Column(name = "END_TIME")
	private Timestamp end;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NUMBER_PHONE")
	private String phone;
	
	@Column(name = "TOTAL_PRICE")
	private Double totalPrice;
	
	@Column(name = "STATUS")
	private String status;
	
	@OneToOne
	@JoinColumn(name = "FK_ID_USER")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "FK_ID_ROOM")
	private Room room;
	
	@OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	public List<OrderItem> orderItems;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Order(Long id, Timestamp start, Timestamp end, String name, Double totalPrice, String status, User user,
			Room room, List<OrderItem> orderItems) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.name = name;
		this.totalPrice = totalPrice;
		this.status = status;
		this.user = user;
		this.room = room;
		this.orderItems = orderItems;
	}

	public Order() {
		super();
	}
	
}
