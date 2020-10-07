package com.auction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;
}
