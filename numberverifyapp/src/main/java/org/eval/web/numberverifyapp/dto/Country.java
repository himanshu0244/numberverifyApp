package org.eval.web.numberverifyapp.dto;

public class Country {

	private String name;
	private String code;
	private String prefix;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", code=" + code + ", prefix=" + prefix + "]";
	}

}
