package dao;

import model.MonHoc;

public interface IMonHocDAO extends IGenericDAO<MonHoc>{
	void insert(MonHoc mh);
	void update(MonHoc mh);
	void delete(MonHoc mh);
}
