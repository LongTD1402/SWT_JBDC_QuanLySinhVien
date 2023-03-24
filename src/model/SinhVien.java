package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import exceptions_handling.NameStringException;
import exceptions_handling.NullFieldException;

public class SinhVien {
	private String MaSV;
	private String hoDem;
	private String ten;
	private Date ngaySinh;
	private GioiTinh gioiTinh;
	private Boolean tinhTrang;
	private List<HocPhan> listHp=new ArrayList<HocPhan>();

	public SinhVien() {
		this.tinhTrang=true;
	}

	public SinhVien(String hoDem, String ten, Date ngaySinh, GioiTinh gioiTinh) {
		this.hoDem = hoDem;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.tinhTrang=true;
	}

	public SinhVien(String maSV, String hoDem, String ten, Date ngaySinh, GioiTinh gioiTinh, Boolean tinhTrang) {
		MaSV = maSV;
		this.hoDem = hoDem;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.tinhTrang = tinhTrang;
		this.tinhTrang=tinhTrang;
	}

	public String getMaSV() {
		return MaSV;
	}

	public void setMaSV(String maSV) {
		MaSV = maSV;
	}

	public String getHoDem() {
		return hoDem;
	}

	public void setHoDem(String hoDem) throws NullFieldException,NameStringException{
		if (hoDem.replaceAll(" ","").isEmpty()) {
			throw new NullFieldException("Họ đệm không được để trống!");
		}
		if(Pattern.matches(UnicodeRegex.regex_vn, hoDem.replaceAll(" ",""))==false) {
			throw new NameStringException("Họ đệm không được chứa số hoặc ký tự đặc biệt!");
		}
		this.hoDem = hoDem.trim();
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) throws NullFieldException,NameStringException{
		if (ten.replaceAll(" ","").isEmpty()) {
			throw new NullFieldException("Tên không được để trống!");
		}
		if(Pattern.matches(UnicodeRegex.regex_vn, ten.replaceAll(" ",""))==false) {
			throw new NameStringException("Tên không được chứa số hoặc ký tự đặc biệt!");
		}
		this.ten = ten.trim();
	}

	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	public Boolean getTinhTrang() {
		return tinhTrang;
	}
	public String getStringTinhTrang() {
		if(this.tinhTrang==true) {
			return "Đang hoạt động";
		}return "Không hoạt động";
	}
	public void setTinhTrang(Boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	public List<HocPhan> getListHp() {
		return listHp;
	}

	public void setListHp(List<HocPhan> listHp) {
		this.listHp = listHp;
	}

	public static GioiTinh toEnumGioiTinh(String str) {
		GioiTinh[] arr= GioiTinh.values();
		GioiTinh gt=null;
		for (GioiTinh gioiTinh : arr) {
			if(str.equals(gioiTinh.value())==true) {
				gt= gioiTinh;
			}
		}
		return gt;
	}
	public String getHovaTen() {
		return (this.hoDem+" "+this.ten);
	}
}
