package org.eval.web.numberverifyapp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eval.web.numberverifyapp.dto.ConnectorResponse;
import org.eval.web.numberverifyapp.dto.ErrorResponse;
import org.eval.web.numberverifyapp.dto.GeneralErrorEnum;
import org.eval.web.numberverifyapp.dto.NumberDTO;
import org.eval.web.numberverifyapp.model.NumberDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class NumberVerificationService {

	@Autowired
	NumberRepository numberRepository;

	@Autowired
	@Qualifier("numberVerifyClientConnector")
	ClientConnector connector;

	private static Logger logger = LoggerFactory.getLogger(NumberVerificationService.class);

	public List<NumberDTO> getAllNumbers() {
		List<NumberDAO> numbers = numberRepository.findAll();
		return prepareNumberDTOList(numbers);
	}

	public NumberDTO findNumber(String numberStr) {

		logger.info("processing number = {}", numberStr);

		NumberDTO numberDTO;
		StringBuilder sb = new StringBuilder();
		sb.append('+').append(numberStr);
		NumberDAO number = loadNumber(sb.toString());
		if (number != null) {
			numberDTO = prepareNumberDTO(number);
		} else {
			logger.error(
					"contact number not found. Service failed with errorCode = {}, errorMessage = {} and, httpStatus = {} ",
					GeneralErrorEnum.ERR_MISSING_CONTACT_NUMBER.getCode(),
					GeneralErrorEnum.ERR_MISSING_CONTACT_NUMBER.getErrorMessage(), HttpStatus.BAD_REQUEST.name());
			throw new AppRuntimeException(GeneralErrorEnum.ERR_MISSING_CONTACT_NUMBER.getCode(),
					GeneralErrorEnum.ERR_MISSING_CONTACT_NUMBER.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}

		return numberDTO;
	}

	public List<NumberDTO> validateNumbers(List<String> numbers) {

		logger.info("requested numbers = {}", Arrays.toString(numbers.toArray()));

		List<NumberDTO> liNumbers = new ArrayList<>();
		numbers.stream().forEach(number -> liNumbers.add(validateNumber(number)));

		return liNumbers;
	}

	private NumberDTO validateNumber(String numberStr) {

		NumberDTO numberDTO;

		logger.info("processing number = {}", numberStr);

		NumberDAO number = loadNumber(numberStr);
		if (number != null) {
			logger.info("retrieved number from database");
			numberDTO = prepareNumberDTO(number);
		} else {
			numberDTO = fetchNumber(numberStr);
		}

		logger.info("validate number response object = {}", numberDTO);
		return numberDTO;
	}

	private NumberDTO fetchNumber(String numberStr) {
		NumberDTO numberDTO = null;

		try {
			ConnectorResponse response = connector.callService(numberStr);
			if (response.getError() != null) {
				numberDTO = prepareErrorResponse(response.getNumber(), response.getError());
			} else {
				saveNumber(response);
				numberDTO = prepareSuccessResponse(response);
			}
		} catch (JsonProcessingException e) {
			logger.error(
					"contact number not found. Service failed with errorCode = {}, errorMessage = {} and, httpStatus = {} ",
					GeneralErrorEnum.ERR_PARSE_CONNECTOR_RESPONSE.getCode(),
					GeneralErrorEnum.ERR_PARSE_CONNECTOR_RESPONSE.getErrorMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR.name());
			throw new AppRuntimeException(GeneralErrorEnum.ERR_PARSE_CONNECTOR_RESPONSE.getCode(),
					GeneralErrorEnum.ERR_PARSE_CONNECTOR_RESPONSE.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return numberDTO;
	}

	private void saveNumber(ConnectorResponse response) {
		Boolean status = response.getValid();
		if (Boolean.TRUE.equals(status)) {
			try {
				numberRepository.save(mapToVO(response));
				logger.info("caching response for number = {}", response.getNumber());
			} catch (DataAccessException dae) {
				logger.error("unable to save number in database, service failed with exception message = {}",
						dae.getMessage());
			}
		}
	}

	private NumberDAO loadNumber(String numberStr) {
		Optional<NumberDAO> number = Optional.empty();
		try {
			number = numberRepository.findByNumber(numberStr);
		} catch (DataAccessException dae) {
			logger.error("unable to load number from database. Loading failed with message = {}", dae.getMessage());
		}

		return number.isPresent() ? number.get() : null;

	}

	private NumberDAO mapToVO(ConnectorResponse response) {
		NumberDAO numberDAO = new NumberDAO();
		Boolean status = response.getValid();
		numberDAO.setNumber(response.getNumber());
		if (Boolean.TRUE.equals(status)) {
			numberDAO.setNumber(response.getNumber());
			numberDAO.setStatus(CommonConstants.VALID);
			numberDAO.setCountryPrefix(response.getCountry().getPrefix());
			numberDAO.setCountryCode(response.getCountry().getCode());
			numberDAO.setCountryName(response.getCountry().getName());
		} else {
			numberDAO.setStatus(CommonConstants.INVALID);
		}

		numberDAO.setCreateDate(LocalDateTime.now());
		numberDAO.setCreatedBy(CommonConstants.SYS_ADMIN);

		return numberDAO;
	}

	private NumberDTO prepareSuccessResponse(ConnectorResponse response) {
		NumberDTO numberDTO = new NumberDTO();
		if (null != response.getCountry()) {
			numberDTO.setCountryCode(response.getCountry().getCode());
			numberDTO.setCountryName(response.getCountry().getName());
		}
		numberDTO.setValid(response.getValid());
		numberDTO.setNumber(response.getNumber());

		return numberDTO;

	}

	private NumberDTO prepareErrorResponse(String numberStr, ErrorResponse error) {
		NumberDTO numberDTO = new NumberDTO();
		numberDTO.setNumber(numberStr);
		numberDTO.setError(error);
		return numberDTO;
	}

	private List<NumberDTO> prepareNumberDTOList(List<NumberDAO> numbers) {
		List<NumberDTO> liNumbers = new ArrayList<>();
		numbers.stream().forEach(number -> liNumbers.add(prepareNumberDTO(number)));
		return liNumbers;

	}

	private NumberDTO prepareNumberDTO(NumberDAO number) {

		NumberDTO numberDTO = new NumberDTO();
		numberDTO.setNumber(number.getNumber());
		numberDTO.setCountryCode(number.getCountryCode());
		numberDTO.setCountryName(number.getCountryName());
		if (number.getStatus().equalsIgnoreCase(CommonConstants.VALID)) {
			numberDTO.setValid(Boolean.TRUE);
		} else {
			numberDTO.setValid(Boolean.FALSE);
		}
		return numberDTO;
	}

}
