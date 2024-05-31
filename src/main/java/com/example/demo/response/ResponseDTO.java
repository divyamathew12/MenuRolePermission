package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

	private String message;

	private T data;

	private String code;

	private String encryptData;

}
