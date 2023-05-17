package com.financial.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
}
