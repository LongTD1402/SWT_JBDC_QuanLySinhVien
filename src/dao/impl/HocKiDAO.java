package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.IHocKiDAO;
import model.HocKi;

public class HocKiDAO extends GenericDAO<HocKi> implements IHocKiDAO{
	
	@Override
	public HocKi findResultSet(ResultSet rs) {
		HocKi newHki= null;
		try {
			newHki = new HocKi(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return newHki;
	}

	@Override
	public HocKi findByMa(String ma) {
		String sql="select * from tbl_hocki where MaHK=?";
		if(query(sql, ma).isEmpty()==false) {
			return (HocKi) query(sql, ma).get(0);
		}else
			return null;
		
	}

	@Override
	public void insert(HocKi hk) {
		Connection conn= null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "insert into tbl_hocki(MaHK,TenHK,NamHoc,NgayBatDau,NgayKetThuc,seo) values(?,?,?,?,?,?)";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, hk.getMaHk());
			prstmt.setString(2, hk.getTenHk());
			prstmt.setString(3, hk.getNamHoc());
			prstmt.setString(4, hk.getNgayBatDau());
			prstmt.setString(5, hk.getNgayKetThuc());
			prstmt.setString(6, hk.getSeo());
			prstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (prstmt != null) {
					prstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("!!Lỗi:" + e.getMessage());
			}
		}
	}

	@Override
	public void update(HocKi hk) {
		Connection conn= null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "UPDATE tbl_hocki SET MaHK=?,TenHK=?,NamHoc=?,NgayBatDau=?, NgayKetThuc=? ,seo=? WHERE MaHK='"+hk.getMaHk()+"'";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, hk.getMaHk());
			prstmt.setString(2, hk.getTenHk());
			prstmt.setString(3, hk.getNamHoc());
			prstmt.setString(4, hk.getNgayBatDau());
			prstmt.setString(5, hk.getNgayKetThuc());
			prstmt.setString(6, hk.getSeo());
			prstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (prstmt != null) {
					prstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("!!Lỗi:" + e.getMessage());
			}
		}
	}

	@Override
	public void delete(HocKi hk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<HocKi> clazz() {
		// TODO Auto-generated method stub
		return HocKi.class;
	}

	@Override
	public Object[] toObjectArr(HocKi ee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HocKi> findByNamHoc(String namHoc) {
		String sql="select*from tbl_hocki where NamHoc=?";
		return query(sql, namHoc);
	}
	
}
