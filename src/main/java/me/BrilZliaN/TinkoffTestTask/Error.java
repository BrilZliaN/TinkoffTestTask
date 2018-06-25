package me.BrilZliaN.TinkoffTestTask;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

	private String errorMessage;

	public Error(@JsonProperty("ERROR_MESSAGE") String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@JsonProperty("ERROR_MESSAGE")
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
