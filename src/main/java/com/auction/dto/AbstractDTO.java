package com.auction.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDTO <T>{
	private Long id;
	private Date createdDate;
	private Date lastModifiedDate;
	private Date lastModifiedBy;
}
