package org.jdamico.gpsd.client.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GpsTpvEntity {

	@SerializedName("class")
	@Expose
	private String _class;
	@SerializedName("device")
	@Expose
	private String device;
	@SerializedName("time")
	@Expose
	private String time;
	@SerializedName("lat")
	@Expose
	private Double lat;
	@SerializedName("lon")
	@Expose
	private Double lon;
	@SerializedName("alt")
	@Expose
	private Double alt;

	public String getClass_() {
		return _class;
	}

	public void setClass_(String _class) {
		this._class = _class;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getAlt() {
		return alt;
	}

	public void setAlt(Double alt) {
		this.alt = alt;
	}

}