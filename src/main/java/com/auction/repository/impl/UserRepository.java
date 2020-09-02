package com.auction.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.auction.entity.UserEntity;
import com.auction.repository.IUserRepository;

@Repository
public class UserRepository extends BaseRepository<UserEntity> implements IUserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserEntity> findByEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(UserEntity.class);
		Root<UserEntity> user = query.from(UserEntity.class);
		return (List<UserEntity>) query.where(user.get("email").in(email));

	}

}
