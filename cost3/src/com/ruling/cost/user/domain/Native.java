package com.ruling.cost.user.domain;

import java.io.Serializable;

public class Native implements Serializable {
	private String nativename;
	private String nativecode;

	private Welfare welfare;
	private WelfareBak welfareBak;

	public WelfareBak getWelfareBak() {
		return welfareBak;
	}

	public void setWelfareBak(WelfareBak welfareBak) {
		this.welfareBak = welfareBak;
	}

	public Welfare getWelfare() {
		return welfare;
	}

	public void setWelfare(Welfare welfare) {
		this.welfare = welfare;
	}

	public String getNativename() {
		return nativename;
	}

	public void setNativename(String nativename) {
		this.nativename = nativename;
	}

	public String getNativecode() {
		return nativecode;
	}

	public void setNativecode(String nativecode) {
		this.nativecode = nativecode;
	}
}
