package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.IGenericDAO;
import model.SinhVien;

public abstract class GenericDAO<E> implements IGenericDAO<E> {
	
	//Lấy ra tên bảng trong database
	protected abstract Class<E> clazz();
	String tableName ="tbl_" +clazz().getSimpleName();

	//Chuyển đối tượng thành kiểu Object
	public abstract Object[] toObjectArr(E ee);
	
	//Hàm gán đối số cho câu lệnh truy vấn
	private void setParameters(PreparedStatement pstmt, Object... parameters) {
		if (parameters.length >0) {
			try {
				for (int i = 1; i <= parameters.length; i++) {
					Object parameter = parameters[i-1];
					int index = i;
					if (parameter instanceof String) {
						pstmt.setString(index, (String) parameter);
					} if (parameter instanceof Integer) {
						pstmt.setInt(index, (int) parameter);
					} if (parameter instanceof BigDecimal) {
						pstmt.setBigDecimal(index, (BigDecimal) parameter);
					} if (parameter instanceof Date) {
						pstmt.setDate(index, (Date) parameter);
					}
				}	
			} catch (Exception e) {
				System.out.println("!!Lỗi index: ");
				e.printStackTrace();
			}
		}
	}
	
	//truy vấn
	@Override
	public <E> List<E> query(String sql, Object... parameters) {
		List<E> list = new ArrayList<E>();
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			conn = DatabaseInfo.getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt, parameters);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add((E) findResultSet(rs));
			}
			
			return list;
		} catch (SQLException e) {
			System.out.println("!Lỗi: " + e.getMessage());
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("!!Lỗi:" + e.getMessage());
				return null;
			}
		}
	}
	
	//Lấy ra tất cả tên cột trong bảng
	public List<String> findAllTableColumns() {
		List<String> list = new ArrayList<String>();
		String sql = "show columns from " + tableName + " ;";
		Connection conn = DatabaseInfo.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(String.valueOf(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return list;
	}
	@Override
	public abstract E findResultSet(ResultSet rs);

	@Override
	public <E> List<E> findAll() {
		String sql = "select * from " + tableName;
		return query(sql);
	}
	
	public abstract E findByMa(String ma);

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
