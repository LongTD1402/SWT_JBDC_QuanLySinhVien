package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.ISinhVienDAO;
import model.HocPhan;
import model.SinhVien;

public class SinhVienDAO extends GenericDAO<SinhVien> implements ISinhVienDAO{

	@Override
	public SinhVien findResultSet(ResultSet rs) {
		SinhVien newSV=null;
		try {
				newSV= new SinhVien(rs.getString(1), rs.getString(2), rs.getString(3),
				rs.getDate(4), SinhVien.toEnumGioiTinh(rs.getString(5)), null);
				//System.out.println(rs.getByte(6));
				if(rs.getByte(6)==1) {
					newSV.setTinhTrang(true);
				}else {
					newSV.setTinhTrang(false);
				}
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return newSV;
	}

	@Override
	public SinhVien findByMa(String ma) {
		String sql="select * from tbl_sinhvien where MaSV=?";
		return (SinhVien) query(sql, ma).get(0);
	}

	@Override
	public void insert(SinhVien sv) {
		// TODO Auto-generated method stub
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "insert into tbl_sinhvien(MaSV,HoDem,Ten,NgaySinh,GioiTinh,TinhTrang) values(?,?,?,?,?,?)";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, sv.getMaSV());
			prstmt.setString(2, sv.getHoDem());
			prstmt.setString(3, sv.getTen());
			prstmt.setString(4, new SimpleDateFormat("yyyy/MM/dd").format(sv.getNgaySinh()));
			prstmt.setString(5, sv.getGioiTinh().value());
			byte tt=-1;
			if(sv.getTinhTrang()==true) {
				tt=1;
			}else {
				tt=0;
			}
			prstmt.setByte(6, tt);
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
	public void update(SinhVien sv) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "UPDATE tbl_sinhvien SET HoDem =?,Ten=?,NgaySinh=?,GioiTinh=?,TinhTrang=? WHERE MaSV = '"+sv.getMaSV()+"';";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, sv.getHoDem());
			prstmt.setString(2, sv.getTen());
			prstmt.setString(3, new SimpleDateFormat("yyyy/MM/dd").format(sv.getNgaySinh()));
			prstmt.setString(4, sv.getGioiTinh().value());
			byte tt=-1;
			if(sv.getTinhTrang()==true) {
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
	public void delete(SinhVien sv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<SinhVien> clazz() {
		// TODO Auto-generated method stub
		return SinhVien.class;
	}

	@Override
	public Object[] toObjectArr(SinhVien sv) {
		Object[] obj= new Object[] { sv.getMaSV(),sv.getHoDem(),sv.getTen()
				
		};
		return obj;
	}

	@Override
	public List<SinhVien> findSinhVien_OutofListHP(HocPhan hp) {
		String sql="select*from tbl_sinhvien where MaSV not in(select MaSV from tbl_diem where MaHP=?);";
		return query(sql,hp.getMaHP());
	}

	@Override
	public List<SinhVien> findListSVByHP(HocPhan hp) {
		String sql="select*from tbl_sinhvien where MaSV in(select MaSV from tbl_diem where MaHP=?);";
		return query(sql, hp.getMaHP());
	}

}
