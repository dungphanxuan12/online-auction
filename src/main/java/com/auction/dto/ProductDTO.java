package com.auction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends AbstractDTO<ProductDTO> {

	private String name;
	private String description;
	private String startTime;
	private String endTime;
	private Long reservePrice;
	private String priceMethod;
	private String image;
	private String category;
	private String status;
	private String branchName;

}
