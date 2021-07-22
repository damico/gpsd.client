package org.jdamico.gpsd.client.entities;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GpsSkyEntity {

	@SerializedName("class")
	@Expose
	private String _class;
	@SerializedName("device")
	@Expose
	private String device;
	@SerializedName("vdop")
	@Expose
	private Double vdop;
	@SerializedName("hdop")
	@Expose
	private Double hdop;
	@SerializedName("pdop")
	@Expose
	private Double pdop;
	
	@SerializedName("gdop")
	@Expose
	private Double gdop;
	
	@SerializedName("satellites")
	@Expose
	private List<Satellite> satellites = null;

	
	
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

	public Double getVdop() {
		return vdop;
	}

	public void setVdop(Double vdop) {
		this.vdop = vdop;
	}

	public Double getHdop() {
		return hdop;
	}

	public void setHdop(Double hdop) {
		this.hdop = hdop;
	}

	public Double getPdop() {
		return pdop;
	}

	public void setPdop(Double pdop) {
		this.pdop = pdop;
	}

	public List<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites) {
		this.satellites = satellites;
	}

	public Double getGdop() {
		return gdop;
	}

	public void setGdop(Double gdop) {
		this.gdop = gdop;
	}

}