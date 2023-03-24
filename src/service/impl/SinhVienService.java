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
import service.ISinhVienService;

public class SinhVienService extends BaseService<SinhVien> implements ISinhVienService{
	private ISinhVienDAO sinhVienDao;
	private IDiemDAO diemDao;
	private IHocPhanDAO hocPhanDAO;
	
	public SinhVienService() {
		sinhVienDao=new SinhVienDAO();
		diemDao= new DiemDAO();
		hocPhanDAO= new HocPhanDAO();
	}

	@Override
	public void insertNew(SinhVien sinhVien) {
		sinhVienDao.insert(sinhVien);
	}

	@Override
	public void deleteOne(SinhVien sinhVien) {

	}

	@Override
	public void updateOne(SinhVien sinhVien) {
		sinhVienDao.update(sinhVien);
	}

	@Override
	public List<SinhVien> findAll() {
		List<SinhVien> listSv=sinhVienDao.findAll();
//		for (SinhVien sinhVien : listSv) {
//			setListHP(sinhVien);
//		}
		return listSv;
	}

	@Override
	public SinhVien findByMa(String ma) {
		SinhVien sv=sinhVienDao.findByMa(ma);
		setListHP(sv);
		return sv;
	}
	public List<SinhVien> findByName(String name){
		List<SinhVien> list= new ArrayList<SinhVien>();
		name= name.toLowerCase().trim();
		for (SinhVien sinhVien : findAll()) {
			String tenSv= sinhVien.getTen().toLowerCase().trim();
			if(name.equals(tenSv)==true) {
				list.add(sinhVien);
				System.out.println(sinhVien.getTen());
			}
		}
		return list;
	}
	public Long lastIndex(List<SinhVien> list) {
		long lastIndex=1;
		for (SinhVien sv : list) {
			long msv= Long.parseLong(sv.getMaSV().replaceAll("SV", ""));
			if(lastIndex<msv) {
				lastIndex=msv;
			}
		}
		return lastIndex;
	}

	@Override
	public List<SinhVien> findByMaHP(String ma) {
		
		return null;
	}

	@Override
	public Double diemTB(SinhVien sv) {
		Double diemTb=null;
		double tong=0;
		int count=1;
		for (HocPhan hocPhan : sv.getListHp()) {
			Double diem= null;
			for (Diem d : diemDao.findByMaSV(sv.getMaSV())) {
				if(hocPhan.getMaHP().equals(d.getMaHP())) {
					diem=d.getDiem();
				}
			}
			tong+=diem;
			diemTb=tong/count;
			if(diem!=null) {
				count++;
			}
		}
		if(diemTb!=null) {
			diemTb=(double) (Math.round(diemTb*100))/100;
		}
		return diemTb;
	}
	private void setListHP(SinhVien sv){
		List<HocPhan> listHp= new ArrayList<HocPhan>();
		for (Diem diem : diemDao.findByMaSV(sv.getMaSV())) {
			listHp.add(hocPhanDAO.findByMa(diem.getMaHP()));
		}
		sv.setListHp(listHp);
	}
}
