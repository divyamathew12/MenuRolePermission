package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {

	private String token;

	private String type = "Bearer";

	@Setter
	private Long id;

	@Setter
	@Getter
	private String username;

	@Setter
	@Getter
	private String email;

	@Getter
	private List<String> roles;

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

}
