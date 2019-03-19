package com.daqsoft.pojo;

import com.daqsoft.config.ConstantWords;

public class Info {
	private int code = ConstantWords.HTTP_SESSFUL_STATUS;
	private String connet ;
	private String error ;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getConnet() {
		return connet;
	}
	public void setConnet(String connet) {
		this.connet = connet;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Info{" +
				"code=" + code +
				", connet='" + connet + '\'' +
				", error='" + error + '\'' +
				'}';
	}
}
