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
import com.auction.exception.UserNotFoundException;
import com.auction.exception.UserVerifyException;
import com.auction.repository.IUserRepository;
import com.auction.service.IUserService;
import com.auction.utils.IEmailService;
import com.auction.validation.impl.DateFormatValidator;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private IEmailService mail;

	/**
	 * save user into database
	 */
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
	 */
	@Transactional
	@Override
	public UserEntity register(@Valid UserDTO userDTO) {
		userRegisterValidation(userDTO);
		String subject = "AUCTION - VERIFY YOUR ACCOUNT";
		String message = "http://localhost:8080/verify?token=" + userDTO.getActivationCode();
		Boolean isSendMailSuccess = mail.sendMail(userDTO.getEmail(), subject, message);
		if (!isSendMailSuccess) {
			System.out.println("Send mail error");
		}
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

	/**
	 * verify user by token
	 * 
	 * @param {String} token
	 */
	@Override
	public void active(String email, String token) {
		UserEntity userEntity = findByEmail(email);
		if (userEntity == null) {
			throw new UserNotFoundException(email);
		}
		if (userEntity.getActivationCode() == token) {
			userEntity.setActived(true);
			save(userEntity);
			return;
		}
		throw new UserVerifyException("Something went wrong during verify User with mail" + email);
	}

}
