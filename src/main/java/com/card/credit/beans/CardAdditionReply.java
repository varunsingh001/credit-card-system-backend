package com.card.credit.beans;

import org.springframework.http.HttpStatus;

public class CardAdditionReply {
	
	private String message;
	private HttpStatus responseCode;	
	
	public HttpStatus getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}

	public void setMessage(String string) {
		message = string;
	}

	public String getMessage() {
		return message;
	}

}
