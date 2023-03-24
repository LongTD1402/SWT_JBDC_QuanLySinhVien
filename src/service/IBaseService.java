package service;

import java.util.List;

public interface IBaseService<E> {
	void insertNew(E e);
	void deleteOne(E e) throws Exception;
	void updateOne(E e);
	List<E> findAll();
	E findByMa(String ma);
}
