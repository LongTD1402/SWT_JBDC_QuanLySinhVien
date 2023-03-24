package model;

import java.util.List;

import exceptions_handling.NullFieldException;

public class HocPhan {
	private String maHP;
	private String tenHP;
	private String maMH;
	private String maHK;
	private int soHP;
	private double heSo;
	private String ngayBatDau;
	private String ngayKetThuc;
	private String hinhThucThi;
	private Boolean trangThai;
	private List<SinhVien> listSV;

	public HocPhan() {
		super();
	}

	public HocPhan(String maHP, String tenHP, String maMH, String maHK, int soHp,double heSo, String ngayBatDau, String ngayKetThuc,
			String hinhThucThi, Boolean trangThai) {
		super();
		this.maHP = maHP;
		this.tenHP = tenHP;
		this.maMH = maMH;
		this.maHK = maHK;
		this.soHP = soHp;
		this.heSo = heSo;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.hinhThucThi = hinhThucThi;
		this.trangThai = trangThai;
	}

	public String getMaHP() {
		return maHP;
	}

	public void setMaHP(String maHP) {
		this.maHP = maHP;
	}

	public String getTenHP() {
		return tenHP;
	}

	public void setTenHP(String tenHP) throws NullFieldException{
		if (tenHP.replaceAll(" ", "").isEmpty()) {
			throw new NullFieldException("Tên học phần không được để trống!");
		}
		this.tenHP = tenHP;
	}

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getMaHK() {
		return maHK;
	}

	public void setMaHK(String maHK) {
		this.maHK = maHK;
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

	public String getHinhThucThi() {
		return hinhThucThi;
	}

	public void setHinhThucThi(String hinhThucThi) {
		this.hinhThucThi = hinhThucThi;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	public int getSoHP() {
		return this.soHP;
	}

	public void setSoHP(int soHP) {
		this.soHP = soHP;
	}

	public double getHeSo() {
		return this.heSo;
	}

	public void setHeSo(double heSo) {
		this.heSo = heSo;
	}
	
	public List<SinhVien> getListSV() {
		return listSV;
	}

	public void setListSV(List<SinhVien> listSV) {
		this.listSV = listSV;
	}

	public String getStringTrangThai() {
		if(this.trangThai==true) {
			return "Đang hoạt động";
		}else
			return "Không hoạt động";
	}
}
