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

@Entity
@Table(name = "T_CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
    private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "IMAGE")
	private String image;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Item> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	public Category(Long id, String name, String status, String image, List<Item> items) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.image = image;
		this.items = items;
	}

	public Category() {
		super();
	}
	
}
