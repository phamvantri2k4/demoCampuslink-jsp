package com.campuslinkjsp.model;

import java.sql.Date;

public class TotNghiep{

	private String maTruong;
	private String maNganh;
	private Date ngayTN;
	private String soCMND;

	public Date getNgayTN() {
		return ngayTN;
	}
	public void setNgayTN(Date ngayTN) {
		this.ngayTN = ngayTN;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public String getMaTruong() {
		return maTruong;
	}
	public void setMaTruong(String maTruong) {
		this.maTruong = maTruong;
	}
	public String getMaNganh() {
		return maNganh;
	}
	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

}