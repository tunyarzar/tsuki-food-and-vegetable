package com.hostmdy.model;

import java.time.LocalDate;

public class Fruit {

	private Integer id;
	private String name;
	private Integer qty;
	private LocalDate date;
	private Double price;
	private String cm;
	public Fruit(String name, Integer qty, LocalDate date, Double price, String cm) {
		super();
		this.name = name;
		this.qty = qty;
		this.date = date;
		this.price = price;
		this.cm = cm;
	}
	public Fruit(Integer id, String name, Integer qty, LocalDate date, Double price, String cm) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.date = date;
		this.price = price;
		this.cm = cm;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCm() {
		return cm;
	}
	public void setCm(String cm) {
		this.cm = cm;
	}
	
	
	
}
