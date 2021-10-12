package com.amit.dto;

import java.util.List;

public class Friend {
	int id;
	String name;
	String img;
	List<Message> chat;
	
	public List<Message> getChat() {
		return chat;
	}
	public void setChat(List<Message> chat) {
		this.chat = chat;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	

}
