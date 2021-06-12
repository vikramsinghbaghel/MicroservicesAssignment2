package com.example.providerservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.providerservice.dao.ItemDao;
import com.example.providerservice.model.Items;

@Service
public class ItemServiceImpl implements ItemService {

	Logger logger = org.slf4j.LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemDao itemDao;

	public ItemServiceImpl() {

	}

	@Override
	public List<Items> getItems() {
		logger.info("GetTickets Method executed ");
		return itemDao.findAll();
	}

	@Override
	@Cacheable(cacheNames = "ticket", key = "#ticketId")
	public Items getItem(int ItemId) {
		if (itemDao.existsById(ItemId)) {
			logger.info("get ticket by ticket Id");
			logger.info("fatching data from the database ");
			return itemDao.getOne(ItemId);
		} else {
			return null;
		}
	}

	@Override
	public Items addItem(Items item) {
		logger.info("Adding new ticket to the database ");
		return itemDao.save(item);
	}

	@Override
	@CacheEvict(cacheNames = "ticket", key = "#itemId")
	public void removeItem(int itemId) {
		logger.info("remove ticket method executed ..");
		logger.info("removing the data from the database...");
		itemDao.deleteById(itemId);

	}

	@Override
	public Items updateItem(Items item) {
		logger.info("update ticket Method executed");

		itemDao.save(item);
		return item;

	}	
}
