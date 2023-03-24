package model;

public enum GioiTinh {
	Nam("Nam"), Nu("Nữ"), Khac("Khác");
	private String value;

	private GioiTinh(String value) {
		this.value = value;
	}
	//Chuyển sang string
	public String value() {
		return value;
	}
}
