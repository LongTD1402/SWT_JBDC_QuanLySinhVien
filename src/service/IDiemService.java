package service;

import java.util.List;

import model.Diem;
import model.HocPhan;
import model.SinhVien;

public interface IDiemService extends IBaseService<Diem>{
	void insertNew(Diem diem);
	void deleteOne(Diem diem);
	void updateOne(Diem diem);
	List<Diem> findByMaSV(String ma);
	List<Diem> findByMaHP(String ma);
	List<SinhVien> listSVbyMaHP(String ma);
	List<HocPhan> listHPbyMaSV(String ma);
	Diem findByMaSV_HP(String maSv,String maHp);
}
