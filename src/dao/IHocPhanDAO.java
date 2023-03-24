package dao;

import java.util.List;

import model.HocPhan;
import model.SinhVien;

public interface IHocPhanDAO extends IGenericDAO<HocPhan>{
	void insert(HocPhan hp);
	void update(HocPhan hp);
	void delete(HocPhan hp);
	List<HocPhan> findByMaMH(String ma);
	List<HocPhan> findByMaHK(String ma);
	List<HocPhan> findByTenHP(String ten);
}
