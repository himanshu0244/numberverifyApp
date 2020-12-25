package org.eval.web.numberverifyapp.dto;

import java.util.List;

public class NumberVerificationRequestDTO {

	private List<String> numbers;

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "NumberVerificationRequestDTO [numbers=" + numbers + "]";
	}

}
