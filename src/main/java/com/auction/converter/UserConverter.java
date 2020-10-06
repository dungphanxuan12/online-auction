package com.auction.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.auction.dto.UserDTO;
import com.auction.entity.UserEntity;

@Component
public class UserConverter {

	public UserDTO convertToDTO(UserEntity userEntity) {
		return new ModelMapper().map(userEntity, UserDTO.class);
	}

	public UserEntity convertToEntity(UserDTO userDTO) {
		return new ModelMapper().map(userDTO, UserEntity.class);
	}
}
