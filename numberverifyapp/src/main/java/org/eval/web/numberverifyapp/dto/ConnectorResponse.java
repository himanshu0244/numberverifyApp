package org.eval.web.numberverifyapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ConnectorResponse {

	private String number;
	private Boolean valid;
	private Country country;
	private ErrorResponse error;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public ErrorResponse getError() {
		return error;
	}

	public void setError(ErrorResponse error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ConnectorResponse [number=" + number + ", valid=" + valid + ", country=" + country + ", error=" + error
				+ "]";
	}

}
