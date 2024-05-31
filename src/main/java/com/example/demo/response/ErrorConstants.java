package com.example.demo.response;

public class ErrorConstants {

	public static final String TOKEN_EXPIRED = "The Token has expired";

	public static final String TOKEN_PARTS_NOT_MATCHING = "The token was expected to have 3 parts";

	public static final String TOKEN_NOT_VALID_JSON_FORMAT = "valid JSON format.";

	public static final String TOKEN_SIGNATURE_NOT_VALID = "The Token's Signature resulted invalid";

	public static final String TOKEN_INPUT_NOT_VALID = "The input is not a valid";

	public static final String AUTH_HEADER_NOT_BLANK = "Authorization Token must not be blank";

	public static final String AUTH_HEADER_NOT_PRESENT = "Authorization Token is not present";

	public static final String JSON_PARSE_ERROR = "Please provide a valid JSON in the request body";

	public static final String REQUEST_BODY_MISSING = "Required request body is missing";

	public static final String BAD_CREDENTIALS = "Bad credentials";

	public static final String ACCESS_DENIED = "Access is denied";

	public static final String INVALID_AUTHORIZATION = "Invalid Authorization";

}
