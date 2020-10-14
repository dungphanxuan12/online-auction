package com.auction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.entity.ProductEntity;
import com.auction.repository.IProductRepository;
import com.auction.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public void save(ProductEntity productEntity) {
		productRepository.save(productEntity);
	}

}
