package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IDiemDAO;
import dao.IHocPhanDAO;
import dao.ISinhVienDAO;
import dao.impl.DiemDAO;
import dao.impl.HocPhanDAO;
import dao.impl.SinhVienDAO;
import model.Diem;
import model.HocPhan;
import model.SinhVien;
import service.IDiemService;

public class DiemService extends BaseService<Diem> implements IDiemService {
	IDiemDAO diemDAO;
	ISinhVienDAO sinhVienDAO;
	IHocPhanDAO hocPhanDAO;

	public DiemService() {
		diemDAO = new DiemDAO();
		sinhVienDAO = new SinhVienDAO();
		hocPhanDAO = new HocPhanDAO();
	}

	@Override
	public List<Diem> findAll() {
		return diemDAO.findAll();
	}

	@Override
	public Diem findByMa(String ma) {
		return null;
	}

	@Override
	public void insertNew(Diem diem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOne(Diem diem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOne(Diem diem) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Diem> findByMaSV(String ma) {
		return diemDAO.findByMaSV(ma);
	}

	@Override
	public List<Diem> findByMaHP(String ma) {
		return diemDAO.findByMaHP(ma);
	}

	@Override
	public List<SinhVien> listSVbyMaHP(String ma) {
		List<SinhVien> listSV= new ArrayList<SinhVien>();
		for (Diem diem : diemDAO.findByMaHP(ma)) {
			listSV.add(sinhVienDAO.findByMa(diem.getMaSV()));
		}
		return listSV;
	}

	@Override
	public List<HocPhan> listHPbyMaSV(String ma) {
		List<HocPhan> listHp= new ArrayList<HocPhan>();
		for (Diem diem : diemDAO.findByMaSV(ma)) {
			listHp.add(hocPhanDAO.findByMa(diem.getMaHP()));
		}
		return null;
	}

	@Override
	public Diem findByMaSV_HP(String maSv, String maHp) {
		return diemDAO.findBySV_HP(maSv, maHp);
	}

}
