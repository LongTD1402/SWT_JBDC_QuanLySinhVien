package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IHocPhanDAO;
import dao.IMonHocDAO;
import dao.impl.HocPhanDAO;
import dao.impl.MonHocDAO;
import model.HocPhan;
import model.MonHoc;
import service.IMonHocService;

public class MonHocService extends BaseService<MonHoc> implements IMonHocService{
	private IMonHocDAO monHocDAO;
	private IHocPhanDAO hocPhanDAO;
	public MonHocService() {
		monHocDAO=new MonHocDAO();
		hocPhanDAO= new HocPhanDAO();
	}

	@Override
	public void insertNew(MonHoc monHoc) {
		monHocDAO.insert(monHoc);
	}

	@Override
	public void deleteOne(MonHoc monHoc) {
	}

	@Override
	public void updateOne(MonHoc monHoc) {
		monHocDAO.update(monHoc);
	}

	@Override
	public List<MonHoc> findAll() {
		List<MonHoc> listMH=monHocDAO.findAll();
		for (MonHoc monHoc : listMH) {
			monHoc.setListHP(hocPhanDAO.findByMaMH(monHoc.getMaMH()));
		}
		return listMH;
	}

	@Override
	public MonHoc findByMa(String ma) {
		MonHoc mh=monHocDAO.findByMa(ma);
		setLispHP(mh);
		return mh;
	}
	public Long lastIndex(List<MonHoc> list) {
		long lastIndex=1;
		for (MonHoc sv : list) {
			long mmh= Long.parseLong(sv.getMaMH().replaceAll("MH", ""));
			if(lastIndex<mmh) {
				lastIndex=mmh;
			}
		}
		return lastIndex;
	}
	private void setLispHP(MonHoc mh) {
		List<HocPhan> listHp= hocPhanDAO.findByMaMH(mh.getMaMH());
		mh.setListHP(listHp);
	}
}
