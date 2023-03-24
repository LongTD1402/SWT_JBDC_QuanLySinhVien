package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.IMonHocDAO;
import model.MonHoc;

public class MonHocDAO extends GenericDAO<MonHoc> implements IMonHocDAO {

	@Override
	public MonHoc findResultSet(ResultSet rs) {
		MonHoc newMh = null;
		try {
			newMh = new MonHoc(rs.getString(1), rs.getString(2), null);
			Boolean tt=null;
			if(rs.getByte(3)==1) {
				tt= true;
			}else {
				tt=false;
			}
			newMh.setTrangThai(tt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return newMh;
	}

	@Override
	public MonHoc findByMa(String ma) {
		String sql="select * from tbl_monhoc where MaMH=?";
		return (MonHoc) query(sql, ma).get(0);
	}

	@Override
	public void insert(MonHoc mh) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "insert into tbl_monhoc(MaMH,TenMH,TrangThai) values(?,?,?)";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, mh.getMaMH());
			prstmt.setString(2, mh.getTenMH());
			byte tt=-1;
			if(mh.getTrangThai()==true) {
				tt=1;
			}else {
				tt=0;
			}
			prstmt.setByte(3, tt);
			prstmt.executeUpdate();
		} catch (SQLException e) {
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
	public void update(MonHoc mh) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "UPDATE tbl_monhoc SET TenMH =?,TrangThai=? WHERE MaMH = '"+mh.getMaMH()+"';";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, mh.getTenMH());
			byte tt=-1;
			if(mh.getTrangThai()==true) {
				tt=1;
			}else {
				tt=0;
			}
			prstmt.setByte(2, tt);
			prstmt.executeUpdate();
		} catch (SQLException e) {
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
	public void delete(MonHoc mh) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Class<MonHoc> clazz() {
		// TODO Auto-generated method stub
		return MonHoc.class;
	}

	@Override
	public Object[] toObjectArr(MonHoc ee) {
		// TODO Auto-generated method stub
		return null;
	}
}
