package com.auction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "age")
	private Integer age;

	@Column(name = "isactived")
	private boolean isActived;

	@Column(name = "activationcode")
	private String activationCode;
}
