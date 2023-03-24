package jframes.Diem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jframes.hocki.hocphan.ChiTietHocPhanFrame;
import jframes.sinhvien.ChiTietSinhVienFrame;
import model.Diem;
import model.HocKi;
import model.HocPhan;
import model.SinhVien;
import service.impl.DiemService;
import service.impl.HocKiService;
import service.impl.HocPhanService;
import service.impl.MonHocService;
import service.impl.SinhVienService;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ThemDiemFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_TenHP;
	private JTextField textField_ChonHP;
	private JTable table_DsHp;
	private JTextField textField_TenSV;
	private JTextField textField_ChonSV;
	private JTable table_DsSv;
	private DefaultTableModel defaultTable_HocPhan;
	private DefaultTableModel defaultTable_SinhVien;
	private JTextField textField_Diem;
	private ButtonGroup btnGroup;
	private JRadioButton rdbtn_tatCaSv;
	private JRadioButton rdbtn_ChuaCoDiem;
	private DiemService diemService;
	private Diem newDiem = null;
	private JComboBox<String> comboBox_HKi;
	private HocKiService hocKiService;
	private SinhVienService sinhVienService;
	private HocPhanService hocPhanService;
	private HocPhan hocP_chon = null;
	private List<HocPhan> listHP = null;
	private SinhVien sinhV_chon = null;
	private List<SinhVien> listSV = null;
	private MonHocService monHocService;
	private JTextField textField_trangHP;
	private JTextField textField_trangSV;
	private JLabel lbl_tongtrangHP= new JLabel("/00");;
	private JLabel lbl_tongTrangSV=new JLabel("/00");;
	private int pageSV=1;
	private int pageHP=1;

	/**
	 * Create the frame.
	 */
	public ThemDiemFrame(SinhVien sinhVien, HocPhan hocPhan) {
		diemService = new DiemService();
		hocKiService = new HocKiService();
		hocPhanService = new HocPhanService();
		monHocService = new MonHocService();
		sinhVienService=new SinhVienService();
		
// listHP và listSV khi mới tạo frame;
		
		listHP=hocKiService.findByMa(hocKiService.getHocKiHienTai().getMaHk()).getListHp();
		listSV=null;
				
		setTitle("Thêm điểm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtn_ChuaCoDiem = new JRadioButton("Chưa có điểm");
		
		rdbtn_ChuaCoDiem.setBounds(384, 30, 108, 23);
		contentPane.add(rdbtn_ChuaCoDiem);
		
		rdbtn_tatCaSv = new JRadioButton("Tất cả");
		
		rdbtn_tatCaSv.setBounds(494, 30, 61, 23);
		contentPane.add(rdbtn_tatCaSv);
		
		btnGroup= new ButtonGroup();
		btnGroup.add(rdbtn_ChuaCoDiem);
		btnGroup.add(rdbtn_tatCaSv);
		rdbtn_ChuaCoDiem.setSelected(true);
		
		rdbtn_ChuaCoDiem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtn_ChuaCoDiem.isSelected()==true) {
					if(hocP_chon!=null) {
						List<SinhVien> list=hocPhanService.listSV_ChuaCoDiem(hocP_chon);
						JOptionPane.showMessageDialog(contentPane, list.get(0).getMaSV());
						defaultTable_SinhVien.setRowCount(0);
						setTableData_SinhVien(list, pageSV);
					}else {
						JOptionPane.showMessageDialog(contentPane, "Hãy chọn 1 học phần");
					}
				}
			}
		});
		
		rdbtn_tatCaSv.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtn_tatCaSv.isSelected()==true) {
					List<SinhVien> list=sinhVienService.findAll();
					defaultTable_SinhVien.setRowCount(0);
					setTableData_SinhVien(list, pageSV);
				}
			}
		});
		

		
// text field TÊN HỌC PHẦN	khi mới tạo frame
		
		JLabel lbl_TenHP = new JLabel("Học phần:");
		lbl_TenHP.setBounds(10, 11, 61, 14);
		contentPane.add(lbl_TenHP);

		textField_TenHP = new JTextField();
		textField_TenHP.setBounds(72, 8, 189, 20);
		contentPane.add(textField_TenHP);
		textField_TenHP.setColumns(10);
		
		textField_Diem = new JTextField();
		textField_Diem.setBounds(671, 64, 63, 20);
		contentPane.add(textField_Diem);
		textField_Diem.setColumns(10);
// text field TÊN SINH VIÊN khi mới tạo frame	
		
		JLabel lbl_TenSV = new JLabel("Sinh viên:");
		lbl_TenSV.setBounds(384, 11, 61, 14);
		contentPane.add(lbl_TenSV);

		textField_TenSV = new JTextField();
		textField_TenSV.setColumns(10);
		textField_TenSV.setBounds(449, 8, 193, 20);
		contentPane.add(textField_TenSV);
		
//combobox HỌC KÌ khi mới tạo frame		
		
		comboBox_HKi = new JComboBox<String>();
		for (HocKi hocKi : hocKiService.findAll()) {
			comboBox_HKi.addItem(hocKi.getTenHk() + "/" + hocKi.getNamHoc()+"/"+hocKi.getMaHk());
		}
		if (hocPhan != null) {
			HocKi selectedHocKi = hocKiService.findByMa(hocPhan.getMaHK());
			comboBox_HKi.setSelectedItem(selectedHocKi.getTenHk() + "/" + selectedHocKi.getNamHoc()+"/"+selectedHocKi.getMaHk());
		}
		comboBox_HKi.setSelectedItem(hocKiService.getHocKiHienTai().getTenHk()+"/"+hocKiService.getHocKiHienTai().getNamHoc()+
				"/"+hocKiService.getHocKiHienTai().getMaHk());
		comboBox_HKi.setBounds(10, 31, 175, 22);
		
		//---------------------------------
		
		if (hocPhan != null && sinhVien==null) {
			hocP_chon = hocPhanService.findByMa(hocPhan.getMaHP());
			listSV = hocP_chon.getListSV();
			rdbtn_ChuaCoDiem.setSelected(false);
			List<HocPhan> list = new ArrayList<HocPhan>();
			list.add(hocP_chon);
			listHP = list;
			textField_TenHP.setText(hocP_chon.getTenHP());		//gán giá trị textfield tên học phần
		}
		if (sinhVien != null && hocPhan==null) {
			sinhV_chon = sinhVienService.findByMa(sinhVien.getMaSV());
			listHP = sinhV_chon.getListHp();
			rdbtn_ChuaCoDiem.setSelected(false);
			List<SinhVien> list = new ArrayList<SinhVien>();
			list.add(sinhV_chon);
			listSV = list;
			textField_TenSV.setText(sinhV_chon.getHovaTen());	//gán giá trị textfield tên sinh viên
		}
		if (sinhVien != null && hocPhan != null) {
			hocP_chon = hocPhanService.findByMa(hocPhan.getMaHP());
			sinhV_chon = sinhVienService.findByMa(sinhVien.getMaSV());
			List<HocPhan> list_hp = new ArrayList<HocPhan>();
			list_hp.add(hocP_chon);
			List<SinhVien> list_sv = new ArrayList<SinhVien>();
			list_sv.add(sinhV_chon);
			listSV = list_sv;
			listHP = list_hp;
			if (diemService.findByMaSV_HP(sinhVien.getMaSV(), hocPhan.getMaHP()).getDiem() != null) {
				rdbtn_ChuaCoDiem.setSelected(false);
				newDiem = diemService.findByMaSV_HP(sinhVien.getMaSV(), hocPhan.getMaHP());
					textField_Diem.setText("" + newDiem.getDiem());
			}
		}
		JScrollPane scrollPane_DsHp = new JScrollPane();
		scrollPane_DsHp.setBounds(10, 88, 350, 345);
		contentPane.add(scrollPane_DsHp);
		

		comboBox_HKi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String split[]=comboBox_HKi.getSelectedItem().toString().split("/");
				List<HocPhan> list= new ArrayList<HocPhan>();
				for (HocPhan hocPhan1 : hocKiService.findByMa(split[2]).getListHp()) {
					for (HocPhan hocPhan2 : hocPhanService.findByTenHP(textField_TenHP.getText())) {
						if(hocPhan1.getMaHP().equals(hocPhan2.getMaHP())==true) {
							list.add(hocPhan2);
						}
					}
				}
				listHP=list;
				defaultTable_HocPhan.setRowCount(0);
				setTableData_HocPhan(listHP, 1);
			}
		});
		contentPane.add(comboBox_HKi);

		JButton btn_TimKiemHP = new JButton("Tìm kiếm");
		btn_TimKiemHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String split[]=comboBox_HKi.getSelectedItem().toString().split("/");
				List<HocPhan> list= new ArrayList<HocPhan>();
				for (HocPhan hocPhan1 : hocKiService.findByMa(split[2]).getListHp()) {
					for (HocPhan hocPhan2 : hocPhanService.findByTenHP(textField_TenHP.getText())) {
						if(hocPhan1.getMaHP().equals(hocPhan2.getMaHP())==true) {
							list.add(hocPhan2);
						}
					}
				}
				listHP=list;
				defaultTable_HocPhan.setRowCount(0);
				setTableData_HocPhan(listHP, 1);
			}
		});
		btn_TimKiemHP.setBounds(271, 7, 89, 23);
		contentPane.add(btn_TimKiemHP);

		JButton btn_ChonHP = new JButton("Chọn");
		btn_ChonHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row= table_DsHp.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(contentPane, "Không có học phần nào được chọn");
				}else {
					String ma=String.valueOf(table_DsHp.getValueAt(row, 0));
					hocP_chon=hocPhanService.findByMa(ma);
					List<HocPhan> list= new ArrayList<HocPhan>();
					list.add(hocP_chon);
					listHP=list;
					defaultTable_HocPhan.setRowCount(0);
					setTableData_HocPhan(listHP, 1);
					textField_ChonHP.setText(hocP_chon.getMaHP()+":"+hocP_chon.getTenHP());
				}
			}
		});
		btn_ChonHP.setBounds(191, 31, 70, 23);
		contentPane.add(btn_ChonHP);

		JButton btn_LamMoiHP = new JButton("Làm mới");
		btn_LamMoiHP.setBounds(270, 31, 90, 23);
		contentPane.add(btn_LamMoiHP);

		textField_ChonHP = new JTextField();
		textField_ChonHP.setEditable(false);
		if(hocP_chon!=null) {
			textField_ChonHP.setText(hocP_chon.getMaHP()+":"+hocP_chon.getTenHP());
		}
		textField_ChonHP.setBounds(66, 64, 245, 20);
		contentPane.add(textField_ChonHP);
		textField_ChonHP.setColumns(10);

		JButton btn_TimKiemSV = new JButton("Tìm kiếm");
		btn_TimKiemSV.setBounds(645, 7, 89, 23);
		contentPane.add(btn_TimKiemSV);

		JButton btn_LamMoi_SV = new JButton("Làm mới");
		btn_LamMoi_SV.setBounds(645, 31, 90, 23);
		contentPane.add(btn_LamMoi_SV);

		textField_ChonSV = new JTextField();
		textField_ChonSV.setEditable(false);
		if(sinhV_chon!=null) {
			textField_ChonSV.setText(sinhV_chon.getMaSV()+":"+sinhV_chon.getHovaTen());
		}
		textField_ChonSV.setColumns(10);
		textField_ChonSV.setBounds(384, 64, 246, 20);
		contentPane.add(textField_ChonSV);

		JButton btn_Chon_SV = new JButton("Chọn");
		btn_Chon_SV.setBounds(566, 31, 76, 23);
		contentPane.add(btn_Chon_SV);

		JScrollPane scrollPane_DsSv = new JScrollPane();
		scrollPane_DsSv.setBounds(384, 88, 350, 345);
		contentPane.add(scrollPane_DsSv);		

		table_DsHp = new JTable();
		scrollPane_DsHp.setColumnHeaderView(table_DsHp);
		defaultTable_HocPhan = new DefaultTableModel(new Object[][] {},
				new String[] {"Mã HP", "Tên học phần", "Môn học" });
		setTableData_HocPhan(listHP, pageHP);
		table_DsHp.setModel(defaultTable_HocPhan);
		scrollPane_DsHp.setViewportView(table_DsHp);

		table_DsSv = new JTable();
		scrollPane_DsSv.setColumnHeaderView(table_DsSv);
		defaultTable_SinhVien= new DefaultTableModel(new Object[][] {},
				new String[] {"Mã SV", "Họ và tên", "Tình trạng" });
		setTableData_SinhVien(listSV, pageSV);
		table_DsSv.setModel(defaultTable_SinhVien);
		scrollPane_DsSv.setViewportView(table_DsSv);
		JLabel lbl_Diem = new JLabel("Điểm:");
		lbl_Diem.setBounds(636, 67, 98, 14);
		contentPane.add(lbl_Diem);

		JButton btn_TroVe = new JButton("Trở về");
		btn_TroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (hocP_chon != null) {
					new ChiTietHocPhanFrame(hocP_chon).setVisible(true);
				}
				if (hocP_chon == null && sinhV_chon != null) {
					new ChiTietSinhVienFrame(sinhV_chon);
				}
			}
		});
		btn_TroVe.setBounds(645, 487, 89, 23);
		contentPane.add(btn_TroVe);

		JButton btn_Luu = new JButton("Lưu");
		btn_Luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Luu.setBounds(553, 487, 89, 23);
		contentPane.add(btn_Luu);

		JLabel lbl_chonHP = new JLabel("Chọn HP:");
		lbl_chonHP.setBounds(10, 69, 61, 14);
		contentPane.add(lbl_chonHP);

		JLabel lbl_ChonSV = new JLabel("Chọn SV:");
		lbl_ChonSV.setBounds(320, 67, 53, 14);
		contentPane.add(lbl_ChonSV);
		
		JButton btnHpTruoc = new JButton("Trước");
		btnHpTruoc.setBounds(10, 444, 89, 23);
		contentPane.add(btnHpTruoc);
		
		JButton btnHpSau = new JButton("Sau");
		btnHpSau.setBounds(191, 444, 89, 23);
		contentPane.add(btnHpSau);
		
		textField_trangHP = new JTextField();
		textField_trangHP.setBounds(105, 445, 40, 20);
		textField_trangHP.setText(""+pageHP);
		contentPane.add(textField_trangHP);
		textField_trangHP.setColumns(10);
		
		
		lbl_tongtrangHP.setBounds(149, 450, 40, 14);
		contentPane.add(lbl_tongtrangHP);
		
		JButton btnDenTrangHP = new JButton("Đến");
		btnDenTrangHP.setBounds(284, 444, 76, 23);
		contentPane.add(btnDenTrangHP);
		
		JButton btnSVTruoc = new JButton("Trước");
		btnSVTruoc.setBounds(384, 444, 89, 23);
		contentPane.add(btnSVTruoc);
		
		textField_trangSV = new JTextField();
		textField_trangSV.setColumns(10);
		textField_trangHP.setText(""+pageSV);
		textField_trangSV.setBounds(483, 445, 36, 20);
		contentPane.add(textField_trangSV);
		
		lbl_tongTrangSV.setBounds(523, 450, 40, 14);
		contentPane.add(lbl_tongTrangSV);
		
		JButton btnSvSau = new JButton("Sau");
		btnSvSau.setBounds(565, 444, 89, 23);
		contentPane.add(btnSvSau);
		
		JButton btnDenTrangSV = new JButton("Đến");
		btnDenTrangSV.setBounds(658, 444, 76, 23);
		contentPane.add(btnDenTrangSV);
	}

	private void setTableData_HocPhan(List<HocPhan> hp, int page) {
		if (hp.isEmpty() == false) {
			int tongTrang=hp.size()/20+1;
			lbl_tongtrangHP.setText("/"+tongTrang);
			for (int i = (page-1)*20; i < page*20; i++) {
				defaultTable_HocPhan.addRow(new Object[] {hp.get(i).getMaHP(), hp.get(i).getTenHP(),
						monHocService.findByMa(hp.get(i).getMaMH()) });
				if(i==(hp.size()-1)){
					break;
				}
			}
		}

	}

	private void setTableData_SinhVien(List<SinhVien> sv, int page) {
		if (sv.isEmpty() == false) {
			int tongTrang=sv.size()/20+1;
			lbl_tongTrangSV.setText("/"+tongTrang);
			for (int i = (page-1)*20; i < page*20; i++) {
				defaultTable_SinhVien.addRow(new Object[] {sv.get(i).getMaSV(), sv.get(i).getHovaTen(),
						sv.get(i).getStringTinhTrang() });
				if(i==(sv.size()-1)){
					break;
				}
			}
		}

	}
}
