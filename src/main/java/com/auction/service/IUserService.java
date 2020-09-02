package com.auction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auction.entity.UserEntity;

@Service
public interface IUserService {
	List<UserEntity> findByEmail(String email);
}
