package com.example.demo.handler;


import com.example.demo.response.MessageCodes;

public class RequestNotFoundException extends Exception {

	public RequestNotFoundException() {
		super(MessageCodes.REQUESTED_NOT_SERVED_BLANK);
	}

}
