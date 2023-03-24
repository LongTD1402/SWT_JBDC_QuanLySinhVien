package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.IHocPhanDAO;
import model.HocPhan;
import model.SinhVien;

public class HocPhanDAO extends GenericDAO<HocPhan> implements IHocPhanDAO{

	@Override
	public void insert(HocPhan hp) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "insert into tbl_hocphan(MaHP,TenHP,MaMH,MaHK,SoHP,HeSo,NgayBatDau,NgayKetThuc,HinhThucThi,TrangThai)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, hp.getMaHP());
			prstmt.setString(2, hp.getTenHP());
			prstmt.setString(3, hp.getMaMH());
			prstmt.setString(4, hp.getMaHK());
			prstmt.setInt(5, hp.getSoHP());
			prstmt.setDouble(6, hp.getHeSo());
			prstmt.setString(7, hp.getNgayBatDau());
			prstmt.setString(8, hp.getNgayKetThuc());
			prstmt.setString(9, hp.getHinhThucThi());
			byte tt=-1;
			if(hp.getTrangThai()==true) {
				tt=1;
			}else {
				tt=0;
			}
			prstmt.setByte(10, tt);
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
	public void update(HocPhan hp) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql="UPDATE tbl_hocphan SET TenHP=?, NgayBatDau=?, NgayKetThuc=?, HinhThucThi=?, TrangThai=? "
				+ "WHERE MaHP='"+hp.getMaHP()+"';";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, hp.getTenHP());
			prstmt.setString(2, hp.getNgayBatDau());
			prstmt.setString(3, hp.getNgayKetThuc());
			prstmt.setString(4, hp.getHinhThucThi());
			byte tt=-1;
			if(hp.getTrangThai()==true) {
				tt=1;
			}else {
				tt=0;
			}
			prstmt.setByte(5, tt);
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
	public void delete(HocPhan hp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<HocPhan> clazz() {
		return HocPhan.class;
	}

	@Override
	public Object[] toObjectArr(HocPhan ee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HocPhan findResultSet(ResultSet rs) {
		HocPhan newHp = null;
		try {
			newHp = new HocPhan(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),
					rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),(Boolean) null);
			Boolean tt=null;
			if(rs.getByte(10)==1) {
				tt= true;
			}else {
				tt=false;
			}
			newHp.setTrangThai(tt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return newHp;
	}

	@Override
	public HocPhan findByMa(String ma) {
		String sql="select*from tbl_hocphan where MaHP=?;";
		return (HocPhan) query(sql, ma).get(0);
	}

	@Override
	public List<HocPhan> findByMaMH(String ma) {
		String sql="select*from tbl_hocphan where MaMH=?;";
		return query(sql, ma);
	}

	@Override
	public List<HocPhan> findByMaHK(String ma) {
		String sql="select*from tbl_hocphan where MaHK=?";
		return query(sql, ma);
	}

	@Override
	public List<HocPhan> findByTenHP(String ten) {
		String sql="select * from tbl_hocphan where TenHP like '%"+ten+"%' and 1=?";
		return query(sql, 1);
	}

}
