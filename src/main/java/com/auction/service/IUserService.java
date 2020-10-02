package com.auction.service;

import org.springframework.stereotype.Service;

import com.auction.entity.UserEntity;

@Service
public interface IUserService {

	UserEntity findByEmail(String email);

	void save(UserEntity user);
}
