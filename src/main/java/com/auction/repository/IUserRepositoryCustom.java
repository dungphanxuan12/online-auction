package com.auction.repository;

import org.springframework.stereotype.Repository;

import com.auction.entity.UserEntity;

@Repository
public interface IUserRepositoryCustom {
	UserEntity findByEmail(String email);
}
