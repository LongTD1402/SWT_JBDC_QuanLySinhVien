package dao;

import java.util.List;

import model.HocKi;

public interface IHocKiDAO extends IGenericDAO<HocKi>{
	void insert(HocKi hk);
	void update(HocKi hk);
	void delete(HocKi hk);
	List<HocKi> findByNamHoc(String namHoc);
}
