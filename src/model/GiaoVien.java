package model;

public class GiaoVien {
	private static int curId;
	private String hoDem;
	private String ten;
	
	public static int getCurId() {
		return curId;
	}
	public static void setCurId(int curId) {
		GiaoVien.curId = curId;
	}
	public String getHoDem() {
		return hoDem;
	}
	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
}
