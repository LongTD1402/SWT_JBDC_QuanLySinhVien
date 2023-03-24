package service;

import java.util.List;

import model.HocPhan;
import model.SinhVien;

public interface IHocPhanService extends IBaseService<HocPhan> {
	void insertNew(HocPhan hocPhan);
	void deleteOne(HocPhan hocPhan);
	void updateOne(HocPhan hocPhan);
	List<HocPhan> findAll();
	HocPhan findByMa(String ma);
	List<HocPhan> findByMaMH(String ma);
	List<HocPhan> findByMaHK(String ma);
	List<HocPhan> findByTenHP(String ten);
	List<SinhVien> listSV_ChuaCoDiem(HocPhan hp);
}

