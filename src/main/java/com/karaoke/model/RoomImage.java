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
@Table(name = "T_ROOM_IMAGE")
public class RoomImage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
    private Long id;
	
	@Column(name = "IMAGE")
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "FK_ROOM_ID")
	private Room room;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public RoomImage(Long id, String image, Room room) {
		super();
		this.id = id;
		this.image = image;
		this.room = room;
	}

	public RoomImage() {
		super();
	}
	
}
