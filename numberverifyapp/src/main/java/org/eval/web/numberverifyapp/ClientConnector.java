package org.eval.web.numberverifyapp;

import org.eval.web.numberverifyapp.dto.ConnectorResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ClientConnector {

	public ConnectorResponse callService(String contactNumber) throws JsonProcessingException;

}
