package com.auction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends AbstractDTO {
	private String email;
	private String password;
	private String fullname;
	private Integer age;
	private boolean isActived;
	private String activationCode;
}
