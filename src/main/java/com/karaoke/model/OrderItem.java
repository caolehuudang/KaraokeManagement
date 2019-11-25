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
@Table(name = "T_ORDER_ITEM")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
    private Long id;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "FK_ID_ORDER")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "FK_ID_ITEM")
	private Item item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderItem(Long id, Order order, Item item) {
		super();
		this.id = id;
		this.order = order;
		this.item = item;
	}

	public OrderItem() {
		super();
	}
	
}
