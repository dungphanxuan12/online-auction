package com.auction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "images")
public class ImageEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@Column(name = "image")
	private String image;

}
