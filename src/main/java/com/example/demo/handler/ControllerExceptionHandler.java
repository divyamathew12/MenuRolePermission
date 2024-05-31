package com.example.demo.handler;

import com.example.demo.response.BaseResponse;
import com.example.demo.response.ErrorConstants;
import com.example.demo.response.MessageCodes;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> globalExceptionHandler(Exception ex, WebRequest request) {
		BaseResponse message = new BaseResponse();
		String headContentType = request.getHeader("Content-Type");
		String errMsg = ex.getMessage().split(":")[0];
		if (errMsg.equals(ErrorConstants.JSON_PARSE_ERROR)) {
			message.setMessage(MessageCodes.JSON_ISSUE_MESSAGE);
			message.setCode(MessageCodes.JSON_ISSUE_CODE);
		}
		else if (errMsg.equals(ErrorConstants.REQUEST_BODY_MISSING)) {
			message.setMessage(MessageCodes.REQUEST_BODY_MISSING_MESSAGE);
			message.setCode(MessageCodes.REQUEST_BODY_MISSING_CODE);
		}

		else if (headContentType == null) {
			message.setMessage("Content type should be specified");
			message.setCode(MessageCodes.TECH_ISSUE_CODE);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		else {
			message.setMessage(errMsg);
			message.setCode(MessageCodes.TECH_ISSUE_CODE);
		}
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<BaseResponse> handleDataAccessException(DataAccessException ex) {
		BaseResponse message = new BaseResponse();
		message.setMessage(MessageCodes.ACCESS_DENIED_ISSUE_MESSAGE);
		message.setCode(MessageCodes.ACCESS_DENIED_ISSUE_CODE);
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TimeoutException.class)
	public ResponseEntity<BaseResponse> handleTimeoutException(TimeoutException ex) {
		BaseResponse message = new BaseResponse();
		message.setMessage(MessageCodes.TIMEOUT_ISSUE_MESSAGE);
		return new ResponseEntity<>(message, HttpStatus.REQUEST_TIMEOUT);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<BaseResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		BaseResponse message = new BaseResponse();
		message.setMessage(ex.getMessage());
		message.setCode(MessageCodes.TECH_ISSUE_CODE);
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<BaseResponse> handleValidationException(ValidationException ex) {
		BaseResponse message = new BaseResponse();
		message.setMessage(ex.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<BaseResponse> globalRuntimeExceptionHandler(Exception ex, WebRequest request) {
		String headContentType = request.getHeader("Content-Type");
		HttpHeaders httpHeaders = new HttpHeaders();
		BaseResponse message = new BaseResponse();
		HttpStatus status;

		String errMsg = ex.getMessage().split(":")[0];
		if (errMsg.equals(ErrorConstants.BAD_CREDENTIALS)) {
			message.setMessage(MessageCodes.AUTHENTICATION_FAILED);
			message.setCode(MessageCodes.AUTH_ISSUE_CODE);
			status = HttpStatus.UNAUTHORIZED;
		}
		else if (ex instanceof MalformedJwtException) {
			message.setMessage(MessageCodes.TOKEN_INVALID_ISSUE_MESSAGE);
			message.setCode(MessageCodes.AUTH_ISSUE_CODE);
			status = HttpStatus.UNAUTHORIZED;
		}
		else if (ex instanceof SignatureException || errMsg.equals(ErrorConstants.INVALID_AUTHORIZATION)) {
			message.setMessage(errMsg);
			message.setCode(MessageCodes.AUTH_ISSUE_CODE);
			status = HttpStatus.UNAUTHORIZED;
		}
		else if (errMsg.equals(ErrorConstants.JSON_PARSE_ERROR) || ex.getMessage().contains("index")
				|| ex.getMessage().contains("JSON parse error")) {
			message.setMessage(ErrorConstants.JSON_PARSE_ERROR);
			message.setCode(MessageCodes.JSON_ISSUE_CODE);
			status = HttpStatus.BAD_REQUEST;
		}
		else if (errMsg.equals(ErrorConstants.ACCESS_DENIED)) {
			message.setMessage(ErrorConstants.ACCESS_DENIED);
			message.setCode(MessageCodes.ACCESS_DENIED_CODE);
			status = HttpStatus.FORBIDDEN;
		}
		else if (errMsg.equals(ErrorConstants.REQUEST_BODY_MISSING)) {
			message.setMessage(MessageCodes.REQUEST_BODY_MISSING_MESSAGE);
			message.setCode(MessageCodes.REQUEST_BODY_MISSING_CODE);
			status = HttpStatus.BAD_REQUEST;
		}
		else if (ex.getCause() instanceof CustomException) {
			message.setMessage(ex.getCause().getMessage().split("CustomException")[0]);
			message.setCode(MessageCodes.ERROR_RESPONSE_MESSAGE_CODE);
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			message.setMessage(errMsg);
			message.setCode(MessageCodes.TECH_ISSUE_CODE);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		if (headContentType != null && headContentType.equals(MediaType.APPLICATION_XML_VALUE)) {
			httpHeaders.setContentType(MediaType.APPLICATION_XML);
		}
		return new ResponseEntity<>(message, httpHeaders, status);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<BaseResponse> globalExceptionHandler(HttpRequestMethodNotSupportedException ex) {
		BaseResponse message = new BaseResponse();
		String errMsg = ex.getMessage().split(":")[0];
		message.setMessage(errMsg);
		message.setCode(MessageCodes.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<BaseResponse> globalExceptionHandler(MissingServletRequestParameterException ex) {
		BaseResponse message = new BaseResponse();
		String errMsg = Objects.requireNonNull(ex.getMessage()).split(":")[0];
		message.setMessage(errMsg);
		message.setCode(MessageCodes.ERROR_RESPONSE_MESSAGE_CODE);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResponse> globalExceptionHandler(MethodArgumentNotValidException ex) {
		BaseResponse message = new BaseResponse();
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		String errMsg = errors.values().toString();

		message.setMessage(errMsg);
		message.setCode(MessageCodes.ERROR_RESPONSE_MESSAGE_CODE);

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<BaseResponse> globalExceptionHandler(ExpiredJwtException ex) {
		BaseResponse message = new BaseResponse();
		String errMsg = Objects.requireNonNull(ex.getMessage()).split(":")[0];
		message.setMessage(errMsg);
		message.setCode(MessageCodes.AUTH_ISSUE_CODE);
		return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
	}

}
