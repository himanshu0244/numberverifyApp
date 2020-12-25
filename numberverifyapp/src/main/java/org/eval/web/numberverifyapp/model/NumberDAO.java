package org.eval.web.numberverifyapp.model;

public class NumberDAO extends BaseEntity {

	private long id;
	private int activeInd;
	private String number;
	private String status;
	private String countryPrefix;
	private String countryCode;
	private String countryName;
	private String location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(int activeInd) {
		this.activeInd = activeInd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountryPrefix() {
		return countryPrefix;
	}

	public void setCountryPrefix(String countryPrefix) {
		this.countryPrefix = countryPrefix;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "NumberDAO [id=" + id + ", activeInd=" + activeInd + ", number=" + number + ", status=" + status
				+ ", countryPrefix=" + countryPrefix + ", countryCode=" + countryCode + ", countryName=" + countryName
				+ ", location=" + location + "]";
	}

}
