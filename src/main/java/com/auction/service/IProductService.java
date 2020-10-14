package com.auction.service;

import org.springframework.stereotype.Service;

import com.auction.entity.ProductEntity;

@Service
public interface IProductService {
	void save(ProductEntity productEntity);
}
