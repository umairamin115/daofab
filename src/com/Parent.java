package com;


public class Parent {

	
	public Parent() {
		
	}
	
	
	
	private long id;
	private String sender;
	private String receiver;
	private long totalAmount;
	private long totalPaidAmount;
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public long getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(long totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}
	
	
	
	
}
