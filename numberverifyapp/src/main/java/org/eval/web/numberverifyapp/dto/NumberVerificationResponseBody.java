package org.eval.web.numberverifyapp.dto;

import java.util.List;

public class NumberVerificationResponseBody {

	List<NumberDTO> numbers;

	public List<NumberDTO> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<NumberDTO> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "NumberVerificationResponseBodyDTO [numbers=" + numbers + "]";
	}
}
