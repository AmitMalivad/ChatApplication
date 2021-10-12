package com.amit.dto;

import java.sql.Date;

public class Message {
	String message;
	Date date;
	boolean isSended;
	boolean isReceived;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isSended() {
		return isSended;
	}
	public void setSended(boolean isSended) {
		this.isSended = isSended;
	}
	public boolean isReceived() {
		return isReceived;
	}
	public void setReceived(boolean isReceived) {
		this.isReceived = isReceived;
	}
	@Override
	public String toString() {
		return "Message [message=" + message + ", date=" + date + ", isSended=" + isSended + ", isReceived="
				+ isReceived + "]";
	}
	
	
}
