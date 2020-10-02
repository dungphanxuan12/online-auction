package com.auction.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.auction.dto.UserDTO;
import com.auction.entity.UserEntity;

@Component
public class UserConverter {

	public static UserDTO convertToDTO(UserEntity userEntity) {
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(userEntity, UserDTO.class);
		return userDTO;
	}

	public static UserEntity convertToEntity(UserDTO userDTO) {
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
		return userEntity;
	}
}
