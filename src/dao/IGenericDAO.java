package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface IGenericDAO<E> {
	@SuppressWarnings("hiding")
	<E> List<E> query(String sql, Object... parameters);// multi parameter

	@SuppressWarnings("hiding")
	E findResultSet(ResultSet rs);

	@SuppressWarnings("hiding")
	<E> List<E> findAll();

	E findByMa(String ma);
}
