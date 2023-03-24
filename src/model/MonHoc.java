package model;

import java.util.ArrayList;
import java.util.List;

import exceptions_handling.NullFieldException;

public class MonHoc {
	private String maMH;
	private String tenMH;
	private Boolean trangThai;
	private List<HocPhan> listHP=new ArrayList<HocPhan>();
	public MonHoc() {

	}

	public MonHoc(String tenMH) {
		this();
//		curId++;
//		maMH="MH"+String.format("%03d", curId);
		this.tenMH = tenMH;
	}

	public MonHoc(String maMH, String tenMH, Boolean trangThai) {
		super();
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.trangThai = trangThai;
	}

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) throws NullFieldException {
		if (tenMH.replaceAll(" ", "").isEmpty()) {
			throw new NullFieldException("Tên môn học không được để trống!");
		}
		this.tenMH = tenMH;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getStringTT() {
		if (this.trangThai == true) {
			return "Đang hoạt động";
		} else {
			return "Không hoạt động";
		}
	}

	public List<HocPhan> getListHP() {
		return listHP;
	}

	public void setListHP(List<HocPhan> listHP) {
		this.listHP = listHP;
	}
	@Override
	public String toString() {
		return this.tenMH;
	}
}
