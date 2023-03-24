package service.impl;

import java.util.List;

import dao.IHocKiDAO;
import dao.IHocPhanDAO;
import dao.impl.HocKiDAO;
import dao.impl.HocPhanDAO;
import model.HocKi;
import service.IHocKiService;

public class HocKiService extends BaseService<HocKi> implements IHocKiService{
	private HocKi hocKiHienTai;
	IHocKiDAO hocKiDAO;
	IHocPhanDAO hocPhanDAO;
	public HocKiService() {
		hocKiDAO= new HocKiDAO();
		hocPhanDAO= new HocPhanDAO();
		hocKiHienTai=findByMa("HK22232");
	}

	@Override
	public void insertNew(HocKi hocKi) {
		hocKiDAO.insert(hocKi);
	}

	@Override
	public void deleteOne(HocKi hocKi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOne(HocKi hocKi) {
		hocKiDAO.update(hocKi);
	}

	@Override
	public List<HocKi> findAll() {
		List<HocKi> listHk=hocKiDAO.findAll();
		for (HocKi hocKi : listHk) {
			hocKi.setListHp(hocPhanDAO.findByMaHK(hocKi.getMaHk()));
		}
		return listHk;
	}
	
	@Override
	public HocKi findByMa(String ma) {
		HocKi hk= hocKiDAO.findByMa(ma);
		hk.setListHp(hocPhanDAO.findByMaHK(ma));
		return hk;
	}
	
	public long lastIndex(List<HocKi> hk) {
		long lastIndex=0;
		for (HocKi hocKi : hk) {
			long ma= Long.parseLong(hocKi.getMaHk().replaceAll("HK", ""));
			if(lastIndex<ma) {
				lastIndex=ma;
			}
		}
		return lastIndex;
	}
	public HocKi getHocKiHienTai() {
		return hocKiHienTai;
	}

	public void setHocKiHienTai(HocKi hocKiHienTai) {
		this.hocKiHienTai = hocKiHienTai ;
	}

	@Override
	public List<HocKi> findByNamHoc(String namHoc) {
		List<HocKi> listHk=hocKiDAO.findByNamHoc(namHoc);
		for (HocKi hocKi : listHk) {
			hocKi.setListHp(hocPhanDAO.findByMaHK(hocKi.getMaHk()));
		}
		return listHk;
	}

}
