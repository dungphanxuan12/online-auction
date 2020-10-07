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
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

	@Column(name = "user_Id")
	private Long userId;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "content")
	private String content;
}
