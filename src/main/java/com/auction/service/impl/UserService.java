package com.auction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auction.converter.UserConverter;
import com.auction.dto.UserDTO;
import com.auction.entity.UserEntity;
import com.auction.exception.UserAlreadyExistException;
import com.auction.repository.IUserRepository;
import com.auction.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public void save(UserEntity user) {
		userRepository.save(user);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * checking user has exist in database then register the user if has no and
	 * throw UserAlreadyExistException if exist email
	 * 
	 */
	@Transactional
	@Override
	public UserEntity register(UserDTO userDTO) {
		if (isUserExist(userDTO.getEmail())) {
			throw new UserAlreadyExistException(userDTO.getEmail());
		}
		UserEntity userEntity = userConverter.convertToEntity(userDTO);

		return userRepository.save(userEntity);
	}

	public Boolean isUserExist(String email) {
		return userRepository.findByEmail(email) != null;
	}

}
