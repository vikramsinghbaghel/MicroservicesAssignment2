package com.example.receiverservice.service;

import java.util.List;

import com.example.receiverservice.model.RequestedItems;



public interface RequestedItemService {


	public List<RequestedItems> getItems();

	public RequestedItems getItem(int itemId);

	public RequestedItems addItem(RequestedItems item);

	public void removeItem(int itemId);

	public RequestedItems updateItem(RequestedItems item);

}
