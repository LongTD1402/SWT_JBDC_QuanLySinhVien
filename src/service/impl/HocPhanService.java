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
import model.HocKi;
import model.HocPhan;
import model.MonHoc;
import model.SinhVien;
import service.IHocPhanService;

public class HocPhanService extends BaseService<HocPhan> implements IHocPhanService {
	IHocPhanDAO hocPhanDAO;
	IDiemDAO diemDAO;
	ISinhVienDAO sinhVienDAO;

	public HocPhanService() {
		hocPhanDAO = new HocPhanDAO();
		diemDAO= new DiemDAO();
		sinhVienDAO= new SinhVienDAO();
	}

	@Override
	public void insertNew(HocPhan hocPhan) {
		hocPhanDAO.insert(hocPhan);
	}

	@Override
	public void deleteOne(HocPhan hocPhan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOne(HocPhan hocPhan) {
		hocPhanDAO.update(hocPhan);
	}

	@Override
	public List<HocPhan> findAll() {
		List<HocPhan> listHp=hocPhanDAO.findAll();
		for (HocPhan hocPhan : listHp) {
			hocPhan.setListSV(sinhVienDAO.findListSVByHP(hocPhan));
		}
		return listHp;
	}

	@Override
	public HocPhan findByMa(String ma) {
		HocPhan hp=hocPhanDAO.findByMa(ma);
		hp.setListSV(sinhVienDAO.findListSVByHP(hp));
		return hp;
	}

	@Override
	public List<HocPhan> findByMaMH(String ma) {
		return hocPhanDAO.findByMaMH(ma);
	}

	@Override
	public List<HocPhan> findByMaHK(String ma) {
		return hocPhanDAO.findByMaHK(ma);
	}

	@Override
	public List<HocPhan> findByTenHP(String ten) {
		return hocPhanDAO.findByTenHP(ten);
	}

	public long lastIndex(List<HocPhan> list,MonHoc mh,HocKi hk) {
		long lastIndex = 0;
		if(list.isEmpty()==false) {
			for (HocPhan hP : list) {
				String maHpStr = hP.getMaHP().replaceAll("HP", "");
				String maMhSeo=Long.parseLong(mh.getMaMH().replaceAll("MH", ""))+"";
				String mHhKSeo=hk.getSeo()+maMhSeo;
				long maL= Long.parseLong(maHpStr.replaceAll(mHhKSeo, ""));
				if (lastIndex <= maL) {
					lastIndex = maL;
				}
			}
		}
		return lastIndex;
	}

	@Override
	public List<SinhVien> listSV_ChuaCoDiem(HocPhan hp) {
		List<SinhVien> list= sinhVienDAO.findSinhVien_OutofListHP(hp);
		return list;
	}
	
}
