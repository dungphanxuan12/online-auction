package com.auction.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends AbstractDTO<ProductDTO> {

	@NotNull
	@NotEmpty
	private String name;
	private String description;

	@NotNull
	@NotEmpty
	private String startTime;

	@NotNull
	@NotEmpty
	private String endTime;

	@NotNull
	@NotEmpty
	private Long reservePrice;

	@NotNull
	@NotEmpty
	private String priceMethod;

	private String image;

	private String category;
	private Boolean status = false;
	private String branchName;

}
