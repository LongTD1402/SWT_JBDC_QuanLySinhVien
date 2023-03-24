package service;

import java.util.List;

import model.MonHoc;
public interface IMonHocService extends IBaseService<MonHoc>{
	void insertNew(MonHoc monHoc);
	void deleteOne(MonHoc monHoc);
	void updateOne(MonHoc monHoc);
	List<MonHoc> findAll();
	MonHoc findByMa(String ma);
}
