package com.auction.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.auction.entity.UserEntity;
import com.auction.repository.IUserRepository;

@Component
public class UserDetailServiceCustom implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	/**
	 * checking if exist email then client can login to their account
	 * 
	 * @param { String } email
	 * @return { UserDetails } User
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}

		return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
	}

}
