package com.example.providerservice.service;

import java.util.List;

import com.example.providerservice.model.Items;

public interface ItemService {

	public List<Items> getItems();

	public Items getItem(int itemId);

	public Items addItem(Items item);

	public void removeItem(int itemId);

	public Items updateItem(Items item);

}