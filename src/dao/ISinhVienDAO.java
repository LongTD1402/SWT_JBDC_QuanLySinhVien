package dao;

import java.util.List;

import model.HocPhan;
import model.SinhVien;

public interface ISinhVienDAO extends IGenericDAO<SinhVien>{
	void insert(SinhVien sv);
	void update(SinhVien sv);
	void delete(SinhVien sv);
	List<SinhVien> findSinhVien_OutofListHP(HocPhan hp);
	List<SinhVien> findListSVByHP(HocPhan hp);
}
