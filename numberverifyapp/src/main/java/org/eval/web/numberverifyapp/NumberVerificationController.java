package org.eval.web.numberverifyapp;

import java.util.Arrays;
import java.util.List;

import org.eval.web.numberverifyapp.dto.GeneralErrorEnum;
import org.eval.web.numberverifyapp.dto.NumberDTO;
import org.eval.web.numberverifyapp.dto.NumberVerificationRequestDTO;
import org.eval.web.numberverifyapp.dto.NumberVerificationResponse;
import org.eval.web.numberverifyapp.dto.NumberVerificationResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * application Controller
 * 
 * @author hgoel@244
 *
 */
@RestController
@RequestMapping(value = CommonConstants.NUMBER_VALIDATION_BASE_URL)
public class NumberVerificationController {

	@Autowired
	private NumberVerificationService numberVerificationService;

	@GetMapping
	public NumberVerificationResponse getAllNumbers() {
		long startTime = System.currentTimeMillis();
		List<NumberDTO> numbers = numberVerificationService.getAllNumbers();
		return prepareNumberVerificationResponse(numbers, startTime);

	}

	@GetMapping("/get/{number}")
	public NumberVerificationResponse findNumber(@PathVariable String number) {
		long startTime = System.currentTimeMillis();
		NumberDTO numberDTO = numberVerificationService.findNumber(number);
		return prepareNumberVerificationResponse(Arrays.asList(numberDTO), startTime);
	}

	@PostMapping("/validate")
	public NumberVerificationResponse validateNumber(
			@RequestBody NumberVerificationRequestDTO numberVerificationRequestDTO) {
		long startTime = System.currentTimeMillis();

		if (ObjectUtils.isEmpty(numberVerificationRequestDTO.getNumbers())
				|| (numberVerificationRequestDTO.getNumbers().isEmpty())) {
			throw new AppRuntimeException(GeneralErrorEnum.ERR_INVALID_REQUEST.getCode(),
					GeneralErrorEnum.ERR_INVALID_REQUEST.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}

		List<NumberDTO> numbers = numberVerificationService.validateNumbers(numberVerificationRequestDTO.getNumbers());
		return prepareNumberVerificationResponse(numbers, startTime);

	}

	private NumberVerificationResponse prepareNumberVerificationResponse(List<NumberDTO> liNumbers, long startTime) {
		NumberVerificationResponse numberVerificationResponse = new NumberVerificationResponse();
		numberVerificationResponse.setResponseBody(new NumberVerificationResponseBody());
		numberVerificationResponse.getResponseBody().setNumbers(liNumbers);
		numberVerificationResponse.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
		return numberVerificationResponse;
	}
}
