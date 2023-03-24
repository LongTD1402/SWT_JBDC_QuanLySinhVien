package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import dao.IDiemDAO;
import model.Diem;

public class DiemDAO extends GenericDAO<Diem> implements IDiemDAO{

	@Override
	public Diem findResultSet(ResultSet rs) {
		Diem newDiem=null;
		try {
			newDiem=new Diem(rs.getString(1), rs.getString(2), rs.getDouble(3));
		}catch(Exception e) {
			
		}
		return newDiem;
	}

	@Override
	public Diem findByMa(String ma) {
		return null;
	}

	@Override
	public void insert(Diem diem) {
		String sql="insert into tbl_diem(MaSV,MaHP,diem) values(?,?,?)";
		Connection conn= null;
		java.sql.PreparedStatement prstm=null;
		try {
			conn= DatabaseInfo.getConnection();
			prstm= conn.prepareStatement(sql);
			prstm.setString(1, diem.getMaSV());
			prstm.setString(2, diem.getMaHP());
			prstm.setDouble(3, diem.getDiem());
			prstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (prstm != null) {
					prstm.close();
				}
			} catch (SQLException e) {
				System.out.println("!!Lỗi:" + e.getMessage());
			}
		}
	}

	@Override
	public void update(Diem diem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Diem diem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<Diem> clazz() {
		// TODO Auto-generated method stub
		return Diem.class;
	}

	@Override
	public Object[] toObjectArr(Diem ee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diem> findByMaSV(String ma) {
		String sql="select* from tbl_diem where MaSV=?";
		return query(sql, ma);
	}

	@Override
	public List<Diem> findByMaHP(String ma) {
		String sql="select* from tbl_diem where MaHP=?";
		return query(sql, ma);
	}

	@Override
	public Diem findBySV_HP(String maSv, String maHp) {
		String sql="select*from tbl_diem where MaSV=? and MaHP=?";
		return (Diem) query(sql, maSv,maHp).get(0);
	}


}
