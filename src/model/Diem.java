package model;

public class Diem {
	private String maSV;
	private String maHP;
	private Double diem;
	public Diem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Diem(String maSV, String maHP, Double diem) {
		super();
		this.maSV = maSV;
		this.maHP = maHP;
		this.diem = diem;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getMaHP() {
		return maHP;
	}

	public void setMaHP(String maHP) {
		this.maHP = maHP;
	}

	public Double getDiem() {
		return diem;
	}

	public void setDiem(Double diem) {
		this.diem = diem;
	}

}
