package com.auction.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.auction.entity.UserEntity;
import com.auction.repository.IUserRepositoryCustom;

@Repository
public class UserRepository implements IUserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	public UserEntity findByEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> user = query.from(UserEntity.class);
		return (UserEntity) query.where(user.get("email").in(email));

	}

}
