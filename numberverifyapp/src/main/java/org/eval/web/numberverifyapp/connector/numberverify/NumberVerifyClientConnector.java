package org.eval.web.numberverifyapp.connector.numberverify;

import static org.eval.web.numberverifyapp.connector.numberverify.NumberVerifyConnectorConstants.ACCESS_KEY;
import static org.eval.web.numberverifyapp.connector.numberverify.NumberVerifyConnectorConstants.FALSE;
import static org.eval.web.numberverifyapp.connector.numberverify.NumberVerifyConnectorConstants.NUMBER;
import static org.eval.web.numberverifyapp.connector.numberverify.NumberVerifyConnectorConstants.SUCCESS;

import java.util.Collections;

import org.eval.web.numberverifyapp.ClientConnector;
import org.eval.web.numberverifyapp.dto.ConnectorResponse;
import org.eval.web.numberverifyapp.dto.Country;
import org.eval.web.numberverifyapp.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("numberVerifyClientConnector")
@ComponentScan
public class NumberVerifyClientConnector implements ClientConnector {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${numberverifyapp.client.connector.numberverify.baseurl}")
	private String url;

	@Value("${numberverifyapp.client.connector.numberverify.accesskey}")
	private String accessKey;

	private static Logger logger = LoggerFactory.getLogger(NumberVerifyClientConnector.class);

	@Override
	public ConnectorResponse callService(String contactNumber) throws JsonProcessingException {
		ConnectorResponse connectorResponse = new ConnectorResponse();
		connectorResponse.setNumber(contactNumber);

		HttpHeaders headers = getHeaders();
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam(ACCESS_KEY, accessKey)
				.queryParam(NUMBER, contactNumber);

		logger.info("client connector request uri = {}", uriBuilder.toUriString());

		ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, httpEntity,
				String.class);

		logger.info("number verify client connector responese  = {}", response.getBody());

		NumberVerifyResponse numberVerifyResponse = parseResponse(response.getBody());

		return mapResponse(numberVerifyResponse, connectorResponse);

	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}

	private NumberVerifyResponse parseResponse(String response) throws JsonProcessingException {
		NumberVerifyResponse numberVerifyResponse = new NumberVerifyResponse();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response);
		JsonNode success = root.path(SUCCESS);
		if (FALSE.equalsIgnoreCase(success.asText())) {
			ErrorDetails error = mapper.readValue(root.path("error").asText(), ErrorDetails.class);
			numberVerifyResponse.setSuccess(Boolean.FALSE);
			numberVerifyResponse.setError(error);
		} else {
			numberVerifyResponse = mapper.readValue(response, NumberVerifyResponse.class);
		}

		logger.info(" numberVerifyResponse  = {}", numberVerifyResponse);

		return numberVerifyResponse;

	}

	private ConnectorResponse mapResponse(NumberVerifyResponse numberVerifyResponse,
			ConnectorResponse connectorResponse) {
		
		if (Boolean.FALSE.equals(numberVerifyResponse.getSuccess())) {
			connectorResponse.setValid(Boolean.FALSE);
			ErrorResponse error = new ErrorResponse();
			error.setCode(numberVerifyResponse.getError().getCode());
			error.setType(numberVerifyResponse.getError().getType());
			error.setMessage(numberVerifyResponse.getError().getInfo());
			connectorResponse.setError(error);
		}else if (Boolean.FALSE.equals(numberVerifyResponse.getValid())){
			connectorResponse.setValid(Boolean.FALSE);
		} else {
			Country country = new Country();
			country.setCode(numberVerifyResponse.getCountryCode());
			country.setName(numberVerifyResponse.getCountryName());
			country.setPrefix(numberVerifyResponse.getCountryPrefix());

			connectorResponse.setValid(Boolean.TRUE);
			connectorResponse.setCountry(country);
		}
		logger.info(" ConnectorResponse  = {}", connectorResponse);
		return connectorResponse;

	}

}
