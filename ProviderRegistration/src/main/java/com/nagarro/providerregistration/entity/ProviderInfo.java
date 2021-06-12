package com.nagarro.providerregistration.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProviderInfo {

	String name;
	@Id
	String email;
	Long contact;
	String address;
	String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ProviderInfo [name=" + name + ", email=" + email + ", contact=" + contact + ", address=" + address
				+ ", password=" + password + "]";
	}
	
	
	
	
	
}
