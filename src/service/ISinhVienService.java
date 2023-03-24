package service;

import java.util.List;

import model.SinhVien;

public interface ISinhVienService extends IBaseService<SinhVien>{
	void insertNew(SinhVien hangsx);
	void deleteOne(SinhVien hangsx);
	void updateOne(SinhVien hangsx);
	List<SinhVien> findAll();
	SinhVien findByMa(String ma);
	List<SinhVien> findByMaHP(String ma);
	Double diemTB(SinhVien sv);
}
