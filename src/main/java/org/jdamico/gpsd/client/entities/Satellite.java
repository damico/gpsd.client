package org.jdamico.gpsd.client.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Satellite {

	@SerializedName("PRN")
	@Expose
	private Integer prn;
	@SerializedName("el")
	@Expose
	private Integer el;
	@SerializedName("az")
	@Expose
	private Integer az;
	@SerializedName("ss")
	@Expose
	private Integer ss;
	@SerializedName("used")
	@Expose
	private Boolean used;

	public Integer getPrn() {
		return prn;
	}

	public void setPrn(Integer prn) {
		this.prn = prn;
	}

	public Integer getEl() {
		return el;
	}

	public void setEl(Integer el) {
		this.el = el;
	}

	public Integer getAz() {
		return az;
	}

	public void setAz(Integer az) {
		this.az = az;
	}

	public Integer getSs() {
		return ss;
	}

	public void setSs(Integer ss) {
		this.ss = ss;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

}