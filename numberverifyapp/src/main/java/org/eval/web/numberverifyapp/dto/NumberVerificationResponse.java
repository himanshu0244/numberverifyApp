package org.eval.web.numberverifyapp.dto;

public class NumberVerificationResponse {

	private ResponseHeaderDTO responseHeader;

	private NumberVerificationResponseBody responseBody;

	public ResponseHeaderDTO getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeaderDTO responseHeader) {
		this.responseHeader = responseHeader;
	}

	public NumberVerificationResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(NumberVerificationResponseBody responseBody) {
		this.responseBody = responseBody;
	}

	@Override
	public String toString() {
		return "NumberVerificationResponse [responseHeader=" + responseHeader + ", responseBody=" + responseBody + "]";
	}

}
