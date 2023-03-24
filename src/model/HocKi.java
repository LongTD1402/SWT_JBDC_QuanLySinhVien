package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import exceptions_handling.NameStringException;
import exceptions_handling.NullFieldException;

public class HocKi {
	private String maHk;
	private String tenHk;
	private String NamHoc;
	private String ngayBatDau;
	private String ngayKetThuc;
	private List<HocPhan> listHp=new ArrayList<HocPhan>();
	private String seo;
	public HocKi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HocKi(String maHk, String tenHk, String namHoc, String ngayBatDau, String ngayKetThuc,String seo) {
		super();
		this.maHk = maHk;
		this.tenHk = tenHk;
		this.NamHoc = namHoc;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.seo=seo;
	}

	public String getMaHk() {
		return maHk;
	}

	public void setMaHk(String maHk) {
		this.maHk = maHk;
	}

	public String getTenHk() {
		return tenHk;
	}

	public void setTenHk(String tenHk) {
		this.tenHk = tenHk;
	}

	public String getNamHoc() {
		return NamHoc;
	}

	public void setNamHoc(String namHoc) throws NameStringException,NullFieldException{
		String regex="20[0-9][0-9]-20[0-9][0-9]";
		if(Pattern.matches(regex, namHoc)==false) {
			throw new NameStringException("Năm học không đúng định dạng (vd:2022-2023)");
		}
		if(namHoc.replaceAll(" ", "").isEmpty()) {
			throw new NullFieldException("Năm học không được để trống!");
		}
		NamHoc = namHoc;
	}

	public String getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(String ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public String getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public List<HocPhan> getListHp() {
		return listHp;
	}

	public void setListHp(List<HocPhan> listHp) {
		this.listHp = listHp;
	}
	
	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	@Override
	public String toString() {
		return this.tenHk+" / "+this.NamHoc;
	}
	public boolean soSanhNamHoc(HocKi hk) {
		if(this.NamHoc.equals(hk.NamHoc)==true) {
			return true;
		}else
			return false;
	}
}
