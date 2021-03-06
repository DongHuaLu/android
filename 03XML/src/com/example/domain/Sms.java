package com.example.domain;

public class Sms {
	private String address;

	private String content;

	private long date;

	private int id;
	private int type;

	public Sms() {

	}

	public Sms(int type, String content, String address, long date, int id) {
		this.type = type;
		this.content = content;
		this.address = address;
		this.date = date;
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public String getContent() {
		return content;
	}

	public long getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

}
