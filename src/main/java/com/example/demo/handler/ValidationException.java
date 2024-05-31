package com.example.demo.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ValidationException extends RuntimeException {

	public ValidationException(String message) {
		super(message);
	}

}