package com.auction.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "products")
public class ProductEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "start_price")
	private Long startPrice;

	@Column(name = "current_price")
	private Long currentPrice;

	@Column(name = "reserve_price")
	private Long reservePrice;

	@Column(name = "price_method")
	private String priceMethod;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ImageEntity> images = new ArrayList<>();

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "status")
	private String status;

	@Column(name = "branch_name")
	private String branchName;

}
