package org.eval.web.numberverifyapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ResponseHeaderDTO
 * 
 * @author hgoel@244
 *
 */
@JsonInclude(Include.NON_NULL)
public class ResponseHeaderDTO {
	private String transactionId;
	private String responseTime;
	private Integer responseCode;
	private ErrorDTO error;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ResponseHeaderDTO [transactionId=" + transactionId + ", responseTime=" + responseTime
				+ ", responseCode=" + responseCode + ", error=" + error + "]";
	}

}
