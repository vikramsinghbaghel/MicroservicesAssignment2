package com.example.providerservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Items {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int itemId;
	private String status;
	private String itemName;
	private String description;
	private String areaofOperation;
	private String contactDetails;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAreaofOperation() {
		return areaofOperation;
	}
	public void setAreaofOperation(String areaofOperation) {
		this.areaofOperation = areaofOperation;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Items(int itemId, String status, String itemName, String description, String areaofOperation,
			String contactDetails) {
		super();
		this.itemId = itemId;
		this.status = status;
		this.itemName = itemName;
		this.description = description;
		this.areaofOperation = areaofOperation;
		this.contactDetails = contactDetails;
	}
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", status=" + status + ", itemName=" + itemName + ", description="
				+ description + ", areaofOperation=" + areaofOperation + ", contactDetails=" + contactDetails + "]";
	}
	
	
	
	
	
	

}
