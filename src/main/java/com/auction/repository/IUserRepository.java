package com.auction.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.auction.entity.UserEntity;

@Repository
public interface IUserRepository extends GenericRepository<UserEntity> {
	List<UserEntity> findByEmail(String email);
}
