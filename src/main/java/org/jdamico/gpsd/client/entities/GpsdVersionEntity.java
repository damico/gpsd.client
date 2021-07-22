package org.jdamico.gpsd.client.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class GpsdVersionEntity {

	@SerializedName("class")
	@Expose
	private String _class;
	@SerializedName("release")
	@Expose
	private String release;
	@SerializedName("rev")
	@Expose
	private String rev;
	@SerializedName("proto_major")
	@Expose
	private Integer protoMajor;
	@SerializedName("proto_minor")
	@Expose
	private Integer protoMinor;

	public String getClass_() {
		return _class;
	}

	public void setClass_(String _class) {
		this._class = _class;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public Integer getProtoMajor() {
		return protoMajor;
	}

	public void setProtoMajor(Integer protoMajor) {
		this.protoMajor = protoMajor;
	}

	public Integer getProtoMinor() {
		return protoMinor;
	}

	public void setProtoMinor(Integer protoMinor) {
		this.protoMinor = protoMinor;
	}

}
