package org.eval.web.numberverifyapp.connector.numberverify;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * NumberVerifyResponse
 * 
 * @author hgoel@244
 *
 */

public class NumberVerifyResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean valid;
	private String number;

	@JsonProperty("local_format")
	private String localFormat;

	@JsonProperty("international_format")
	private String internationalFormat;

	@JsonProperty("country_prefix")
	private String countryPrefix;

	@JsonProperty("country_code")
	private String countryCode;

	@JsonProperty("country_name")
	private String countryName;

	private String location;
	private String carrier;

	@JsonProperty("line_type")
	private String lineType;
	
	private Boolean success;
	
	private ErrorDetails error;


	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLocalFormat() {
		return localFormat;
	}

	public void setLocalFormat(String localFormat) {
		this.localFormat = localFormat;
	}

	public String getInternationalFormat() {
		return internationalFormat;
	}

	public void setInternationalFormat(String internationalFormat) {
		this.internationalFormat = internationalFormat;
	}

	public String getCountryPrefix() {
		return countryPrefix;
	}

	public void setCountryPrefix(String countryPrefix) {
		this.countryPrefix = countryPrefix;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "NumberVerifyResponse [valid=" + valid + ", number=" + number + ", localFormat=" + localFormat
				+ ", internationalFormat=" + internationalFormat + ", countryPrefix=" + countryPrefix + ", countryCode="
				+ countryCode + ", countryName=" + countryName + ", location=" + location + ", carrier=" + carrier
				+ ", lineType=" + lineType + ", success=" + success + ", error=" + error + "]";
	}

}
