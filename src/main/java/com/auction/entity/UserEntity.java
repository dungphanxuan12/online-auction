package com.auction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", indexes = { @Index(name = "idx_user", columnList = "email") })
public class UserEntity extends BaseEntity {

	@NotNull
	@Email(message = "Something wrong with email address")
	@Column(name = "email", unique = true)
	private String email;

	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "fullname")
	private String fullname;

	@Column(name = "age")
	private Integer age;

	@Column(name = "isactived")
	private boolean isActived;

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "activationcode")
	private String activationCode;
}
