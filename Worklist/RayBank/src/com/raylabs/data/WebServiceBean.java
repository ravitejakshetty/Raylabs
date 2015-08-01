package com.raylabs.data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "convertor", eager = true)
@SessionScoped
public class WebServiceBean {

	private long dollar;
	private long rupee;

	public long getDollar() {
		return dollar;
	}

	public void setDollar(long dollar) {
		this.dollar = dollar;
	}

	public String show() {

		return "";
	}

	public long getRupee() {
		this.rupee=this.dollar*49;
		return rupee;
	}

	public void setRupee(long rupee) {
		this.rupee = rupee;
	}

}
