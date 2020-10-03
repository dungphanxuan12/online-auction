package com.auction.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends AbstractDTO<UserDTO> {

	@NotNull
	@NotEmpty
	@Email(message = "Something wrong with email address", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String email;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
	private String password;

	@NotNull
	@NotEmpty
	private String fullname;

	@NotNull
	@NotEmpty
	private Integer age;

	private boolean isActived;

	private String activationCode;

	private String jwtToken;
}
