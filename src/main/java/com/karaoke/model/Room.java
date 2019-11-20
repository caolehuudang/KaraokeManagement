package com.karaoke.model;

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
@Table(name = "T_ROOM")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
    private Long id;
	
	@Column(name = "ROOM_NAME")
	private String name;
	
	@Column(name = "CAPACITY")
	private int capacity;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "PRICE")
	private Double price;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	private List<RoomImage> roomImages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	

	public List<RoomImage> getRoomImages() {
		return roomImages;
	}

	public void setRoomImages(List<RoomImage> roomImages) {
		this.roomImages = roomImages;
	}
	

	public Room(Long id, String name, int capacity, String status, Double price, List<Order> orders,
			List<RoomImage> roomImages) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.price = price; 
		this.orders = orders;
		this.roomImages = roomImages;
	}

	public Room() {
		super();
	}
	
}
