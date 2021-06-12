package com.example.providerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.providerservice.model.Items;

public interface ItemDao extends JpaRepository<Items, Integer> {

}
