package com.example.receiverservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.receiverservice.model.RequestedItems;

public interface RequestedItemDao extends JpaRepository<RequestedItems, Integer> {

}