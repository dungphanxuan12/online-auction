package com.auction.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.auction.dto.ProductDTO;
import com.auction.entity.ProductEntity;

@Component
public class ProductConverter {

	public ProductDTO convertToDTO(ProductEntity productEntity) {
		return new ModelMapper().map(productEntity, ProductDTO.class);
	}

	public ProductEntity convertToEntity(ProductDTO productDTO) {
		return new ModelMapper().map(productDTO, ProductEntity.class);
	}

}
