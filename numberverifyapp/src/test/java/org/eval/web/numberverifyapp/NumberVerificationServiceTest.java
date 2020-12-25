package org.eval.web.numberverifyapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eval.web.numberverifyapp.connector.numberverify.NumberVerifyClientConnector;
import org.eval.web.numberverifyapp.dto.ConnectorResponse;
import org.eval.web.numberverifyapp.dto.Country;
import org.eval.web.numberverifyapp.dto.NumberDTO;
import org.eval.web.numberverifyapp.model.NumberDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(MockitoJUnitRunner.class)
public class NumberVerificationServiceTest {

	@Mock
	NumberRepository numberRepositoryMock;

	@Mock
	NumberVerifyClientConnector connectorMock;

	@InjectMocks
	NumberVerificationService numberVerificationService;

	@Test
	public void testGetAllNumber() {
		List<NumberDAO> numbers = new ArrayList<>();
		numbers.add(getNumber());
		when(numberRepositoryMock.findAll()).thenReturn(numbers);
		List<NumberDTO> response = numberVerificationService.getAllNumbers();
		assertNotNull(response);
		assertEquals("+919043271156", response.get(0).getNumber());
		assertEquals(true, response.get(0).getValid());

	}

	@Test
	public void testFindNumber() {
		when(numberRepositoryMock.findByNumber(anyString())).thenReturn(Optional.of(getNumber()));
		NumberDTO response = numberVerificationService.findNumber("+9043271156");
		assertNotNull(response);
		assertEquals("+919043271156", response.getNumber());
		assertEquals(true, response.getValid());
	}

	@Test
	public void testValidateNumberFromCache() {
		List<String> liNumber = new ArrayList<>();
		liNumber.add("+9043271156");
		when(numberRepositoryMock.findByNumber(anyString())).thenReturn(Optional.of(getNumber()));
		List<NumberDTO> response = numberVerificationService.validateNumbers(liNumber);
		assertNotNull(response);
		assertEquals("+919043271156", response.get(0).getNumber());
		assertEquals(true, response.get(0).getValid());

	}

	@Test
	public void testValidateNumberIfNotInCache() throws JsonMappingException, JsonProcessingException {
		List<String> liNumber = new ArrayList<>();
		liNumber.add("+9043271156");

		ConnectorResponse connectorResponse = getNumberVerifyConnectorResponse();
		when(numberRepositoryMock.findByNumber(anyString())).thenReturn(Optional.empty());
		when(connectorMock.callService(any())).thenReturn(connectorResponse);
		List<NumberDTO> response = numberVerificationService.validateNumbers(liNumber);
		assertNotNull(response);
		assertEquals("+919043271156", response.get(0).getNumber());
		assertEquals(true, response.get(0).getValid());

	}

	private ConnectorResponse getNumberVerifyConnectorResponse() {
		ConnectorResponse connectorResponse = new ConnectorResponse();
		Country country = new Country();
		country.setCode("IN");
		country.setName("India");
		country.setPrefix("+91");
		connectorResponse.setValid(true);
		connectorResponse.setCountry(country);
		connectorResponse.setNumber("+919043271156");
		return connectorResponse;
	}

	private NumberDAO getNumber() {
		NumberDAO number = new NumberDAO();
		number.setId(1L);
		number.setNumber("+919043271156");
		number.setStatus("valid");
		number.setActiveInd(1);
		number.setCountryCode("IN");
		number.setCountryName("India");
		number.setCreateDate(LocalDateTime.now());
		return number;
	}

}
