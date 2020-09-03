package com.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>, IUserRepositoryCustom {
	UserEntity findByEmail(String email);
}
