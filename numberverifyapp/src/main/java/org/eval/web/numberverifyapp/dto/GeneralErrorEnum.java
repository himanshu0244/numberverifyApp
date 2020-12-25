package org.eval.web.numberverifyapp.dto;

/**
 * GeneralErrorEnum
 * 
 * @author hgoel@244
 *
 */
public enum GeneralErrorEnum {

	SUCCESS(200, "SUCCESS", "Success"), FAILED(101, "FAILED", "Failed"),
	ERR_INVALID_REQUEST(102, "ERR_INVALID_REQUEST", "Invalid Request"),
	ERR_PARSE_CONNECTOR_RESPONSE(103, "ERR_PARSE_CONNECTOR_RESPONSE", "Unable to parse connector response"),
	ERR_INVALID_CONNECTOR_RESPONSE(104, "ERR_INVALID_RESPONSE", "Invalid Connector Response"),
	ERR_MISSING_CONTACT_NUMBER(105, "ERR_MISSING_CONTACT_NUMBER", "Contact number not found "),
	UNKNOWN(000, "UNKNOWN", "Unknown");

	private int code;
	private String errorCode;
	private String errorMessage;

	GeneralErrorEnum(int code, String errorCode, String errorMessage) {
		this.code = code;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getCode() {
		return code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
