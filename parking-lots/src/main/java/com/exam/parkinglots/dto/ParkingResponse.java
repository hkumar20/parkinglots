package com.exam.parkinglots.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemanth
 * @param <T>
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class ParkingResponse<T> {
	private String message;
	private T response;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
}
