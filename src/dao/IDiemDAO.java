package dao;

import java.util.List;

import model.Diem;

public interface IDiemDAO extends IGenericDAO<Diem> {
	void insert(Diem diem);
	void update(Diem diem);
	void delete(Diem diem);
	List<Diem> findByMaSV(String ma);
	List<Diem> findByMaHP(String ma);
	Diem findBySV_HP(String maSv,String maHp);
}
