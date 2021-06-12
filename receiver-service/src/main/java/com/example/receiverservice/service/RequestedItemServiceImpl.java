package com.example.receiverservice.service;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.receiverservice.dao.RequestedItemDao;
import com.example.receiverservice.model.RequestedItems;

@Service
public class RequestedItemServiceImpl implements RequestedItemService {

	Logger logger = org.slf4j.LoggerFactory.getLogger(RequestedItemServiceImpl.class);

	@Autowired
	private RequestedItemDao reduestedItemDao;

	public RequestedItemServiceImpl() {

	}
	@Override
	public List<RequestedItems> getItems() {
		logger.info("GetTickets Method executed ");
		return reduestedItemDao.findAll();
	}
	
	@Override
	@Cacheable(cacheNames = "ticket", key = "#ticketId")
	public RequestedItems getItem(int ItemId) {
		if (reduestedItemDao.existsById(ItemId)) {
			logger.info("get ticket by ticket Id");
			logger.info("fatching data from the database ");
			return reduestedItemDao.getOne(ItemId);
		} else {
			return null;
		}
	}
	@Override
	public RequestedItems addItem(RequestedItems item) {
		logger.info("Adding new ticket to the database ");
		return reduestedItemDao.save(item);
	}

	@Override
	@CacheEvict(cacheNames = "ticket", key = "#itemId")
	public void removeItem(int itemId) {
		logger.info("remove ticket method executed ..");
		logger.info("removing the data from the database...");
		reduestedItemDao.deleteById(itemId);

	}

	@Override
	public RequestedItems updateItem(RequestedItems item) {
		logger.info("update ticket Method executed");

		reduestedItemDao.save(item);
		return item;

	}
	
}

