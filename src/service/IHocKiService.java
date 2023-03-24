package service;

import java.util.List;

import model.HocKi;

public interface IHocKiService extends IBaseService<HocKi>{
	void insertNew(HocKi hocKi);
	void deleteOne(HocKi hocKi);
	void updateOne(HocKi hocKi);
	List<HocKi> findAll();
	HocKi findByMa(String ma);
	List<HocKi> findByNamHoc(String namHoc);
}
