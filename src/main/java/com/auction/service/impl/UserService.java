package com.auction.service.impl;

import java.time.DateTimeException;
import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auction.converter.UserConverter;
import com.auction.dto.UserDTO;
import com.auction.entity.UserEntity;
import com.auction.exception.UserAlreadyExistException;
import com.auction.repository.IUserRepository;
import com.auction.service.IUserService;
import com.auction.validation.impl.DateFormatValidator;

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
	public UserEntity register(@Valid UserDTO userDTO) {
		userRegisterValidation(userDTO);
		UserEntity userEntity = userConverter.convertToEntity(userDTO);

		return userRepository.save(userEntity);
	}

	/**
	 * 
	 * @param userDTO
	 */
	private void userRegisterValidation(UserDTO userDTO) {
		if (userRepository.findByEmail(userDTO.getEmail()) != null) {
			throw new UserAlreadyExistException(userDTO.getEmail());
		}
		userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
		userDTO.setCreatedDate(new Date());
		userDTO.setActivationCode(UUID.randomUUID().toString());
		if (!new DateFormatValidator("yyyy-MM-dd").isValid(userDTO.getDob()) && userDTO.getDob() != null) {
			throw new DateTimeException(
					"Error! Invalid date. Make sure that you enter the date in the format \"yyyy-MM-dd\".");
		}
	}

}
