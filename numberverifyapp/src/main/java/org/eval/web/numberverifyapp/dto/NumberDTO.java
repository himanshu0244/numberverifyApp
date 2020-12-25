package org.eval.web.numberverifyapp.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class NumberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String number;
	private Boolean valid;
	private String countryCode;
	private String countryName;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public ErrorResponse getError() {
		return error;
	}

	public void setError(ErrorResponse error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "NumberDTO [number=" + number + ", valid=" + valid + ", countryCode=" + countryCode + ", countryName="
				+ countryName + ", error=" + error + "]";
	}

}
