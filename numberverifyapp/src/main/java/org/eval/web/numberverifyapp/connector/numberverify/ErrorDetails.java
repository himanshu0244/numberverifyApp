package org.eval.web.numberverifyapp.connector.numberverify;

import java.io.Serializable;

public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;
	private String type;
	private String info;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "ErrorDetailsDTO [code=" + code + ", type=" + type + ", info=" + info + "]";
	}

}
