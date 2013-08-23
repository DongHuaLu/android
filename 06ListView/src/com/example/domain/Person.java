package com.example.domain;

public class Person {
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", money=" + money
				+ ", tel=" + tel + "]";
	}
	private int id;
	private String name;
	private int money;
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	private String tel;

}
