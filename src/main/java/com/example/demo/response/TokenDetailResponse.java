package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDetailResponse {

	private String username;

	private String password;

	private String email;

	private String role;

}
