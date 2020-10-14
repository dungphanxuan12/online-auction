package com.auction.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDTO<T> {
	private Long id;
	private Date createdDate = new Date();
	private Date lastModifiedDate;
	private Date lastModifiedBy;
}
