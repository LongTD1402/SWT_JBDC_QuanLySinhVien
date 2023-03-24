package jframes.sinhvien;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import jframes.Diem.ThemDiemFrame;
import model.GioiTinh;
import model.HocKi;
import model.HocPhan;
import model.SinhVien;
import service.impl.DiemService;
import service.impl.HocKiService;
import service.impl.HocPhanService;
import service.impl.SinhVienService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ChiTietSinhVienFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMaSV;
	private JTextField textField_HoTenDem;
	private JTextField textField_TenSV;
	private JRadioButton rdbtnNam ;
	private JRadioButton rdbtnNu ;
	private JRadioButton rdbtnKhac ;
	private ButtonGroup btnGroupGioiTinh;
	private ButtonGroup btnGroupTT;
	private JDateChooser jDateChooser;
	private JTable tableDiem;
	private JComboBox<String> comboBoxHocKy;
	private SinhVienService sinhVienService;
	private HocKiService hocKiService;
	private DiemService diemService;
	private HocPhanService hocPhanService;
	private DefaultTableModel defaultTable_BangDiem;
	SinhVien sinhVien=null;
	/**
	 * Create the frame.
	 */
	public ChiTietSinhVienFrame(final SinhVien sv) {
		sinhVienService= new SinhVienService();
		hocKiService=new HocKiService();
		diemService= new DiemService();
		hocPhanService=new HocPhanService();
		sinhVien= sinhVienService.findByMa(sv.getMaSV());
		
		setTitle("Chi tiêt sinh viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaSV = new JLabel("Mã sinh viên:");
		lblMaSV.setBounds(10, 11, 79, 14);
		contentPane.add(lblMaSV);
		
		textFieldMaSV = new JTextField();
		textFieldMaSV.setEditable(false);
		textFieldMaSV.setBounds(94, 8, 86, 20);
		textFieldMaSV.setText(sinhVien.getMaSV());
		contentPane.add(textFieldMaSV);
		textFieldMaSV.setColumns(10);
		
		JLabel lblHoDem = new JLabel("Họ-Tên đệm:");
		lblHoDem.setBounds(205, 11, 79, 14);
		contentPane.add(lblHoDem);
		
		textField_HoTenDem = new JTextField();
		textField_HoTenDem.setBounds(287, 8, 197, 20);
		textField_HoTenDem.setText(sinhVien.getHoDem());
		contentPane.add(textField_HoTenDem);
		textField_HoTenDem.setColumns(10);
		
		JLabel lblTenSV = new JLabel("Tên:");
		lblTenSV.setBounds(494, 10, 46, 14);
		contentPane.add(lblTenSV);
		
		textField_TenSV = new JTextField();
		textField_TenSV.setBounds(529, 8, 86, 20);
		textField_TenSV.setText(sinhVien.getTen());
		contentPane.add(textField_TenSV);
		textField_TenSV.setColumns(10);
		
		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(6, 46, 63, 23);
		contentPane.add(rdbtnNam);
		
		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(68, 46, 50, 23);
		contentPane.add(rdbtnNu);
		
		rdbtnKhac = new JRadioButton("Khác");
		rdbtnKhac.setBounds(123, 46, 57, 23);
		contentPane.add(rdbtnKhac);
		
		btnGroupGioiTinh= new ButtonGroup();
		btnGroupGioiTinh.add(rdbtnNam);
		btnGroupGioiTinh.add(rdbtnNu);
		btnGroupGioiTinh.add(rdbtnKhac);
		
		if(sinhVien.getGioiTinh().value()=="Nam") {
			rdbtnNam.setSelected(true);
		}else if(sinhVien.getGioiTinh().value()=="Nữ") {
			rdbtnNu.setSelected(true);
		}else {
			rdbtnKhac.setSelected(true);
		}
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setBounds(205, 52, 79, 14);
		contentPane.add(lblNgaySinh);
		
		jDateChooser =new JDateChooser();
		jDateChooser.setDateFormatString("yyyy-MM-dd");
		
		jDateChooser.setDate(sinhVien.getNgaySinh());
		jDateChooser.setBounds(268, 49, 120, 20);
		contentPane.add(jDateChooser);
		
		JButton btnSuaSV = new JButton("Lưu");
		btnSuaSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sinhVien.setHoDem(textField_HoTenDem.getText());
					sinhVien.setTen(textField_TenSV.getText());
					if(rdbtnNam.isSelected()) {
						sinhVien.setGioiTinh(GioiTinh.Nam);
					}if(rdbtnNu.isSelected()) {
						sinhVien.setGioiTinh(GioiTinh.Nu);
					}if(rdbtnKhac.isSelected()) {
						sinhVien.setGioiTinh(GioiTinh.Khac);
					}
					sv.setNgaySinh(jDateChooser.getDate());
					JOptionPane.showConfirmDialog(contentPane, "Xác nhận sửa?");
					sv.setTinhTrang(true);
					sinhVienService.updateOne(sv);
				}catch(Exception e3) {
					JOptionPane.showMessageDialog(contentPane, e3.getMessage());
				}
			}
		});
		btnSuaSV.setBounds(575, 80, 79, 23);
		contentPane.add(btnSuaSV);
		
		JScrollPane scrollPaneBangDiem = new JScrollPane();
		scrollPaneBangDiem.setBounds(10, 132, 644, 269);
		contentPane.add(scrollPaneBangDiem);
		
		tableDiem = new JTable();
		scrollPaneBangDiem.setColumnHeaderView(tableDiem);
		defaultTable_BangDiem = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã học phần", "Tên học phần", "Học kì", "Bắt đầu", "Kết thúc","Điểm" });
		setTableHKData(sinhVien,null, 1);
		tableDiem.setModel(defaultTable_BangDiem);
		scrollPaneBangDiem.setViewportView(tableDiem);
		JButton btnBangDiem = new JButton("Bảng điểm");
		btnBangDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBangDiem.setBounds(288, 80, 98, 23);
		contentPane.add(btnBangDiem);
		
		comboBoxHocKy = new JComboBox<String>();
		comboBoxHocKy.setBounds(10, 80, 148, 22);
		comboBoxHocKy.addItem("Tất cả");
		for (HocKi hocKi : hocKiService.findAll()) {
			comboBoxHocKy.addItem(hocKi.getTenHk()+" / "+hocKi.getNamHoc()+"/"+hocKi.getMaHk());
		}
		comboBoxHocKy.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxHocKy.getSelectedIndex()==0) {
					defaultTable_BangDiem.setRowCount(0);
					setTableHKData(sinhVien, null, 0);
				}else {
					String split[]=comboBoxHocKy.getSelectedItem().toString().split("/");
					HocKi hki=hocKiService.findByMa(split[2]);
					defaultTable_BangDiem.setRowCount(0);
					setTableHKData(sinhVien, hki, 0);
				}
			}
		});
		contentPane.add(comboBoxHocKy);
		
		JButton btnLichThi = new JButton("Lịch thi");
		btnLichThi.setBounds(187, 80, 89, 23);
		contentPane.add(btnLichThi);
		
		JButton btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTroVe.setBounds(565, 407, 89, 23);
		contentPane.add(btnTroVe);
		
		JRadioButton rdbtnTTHoatDong = new JRadioButton("Đang hoạt động");
		rdbtnTTHoatDong.setBounds(402, 46, 120, 23);
		contentPane.add(rdbtnTTHoatDong);
		
		JRadioButton rdbtnTTKhongHoatDong = new JRadioButton("Không hoạt động");
		rdbtnTTKhongHoatDong.setBounds(529, 46, 125, 23);
		contentPane.add(rdbtnTTKhongHoatDong);
		
		btnGroupTT =new ButtonGroup();
		btnGroupTT.add(rdbtnTTHoatDong);
		btnGroupTT.add(rdbtnTTKhongHoatDong);
		
		JButton btn_ThemDiem = new JButton("Chi tiết");
		btn_ThemDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_ThemDiem.setBounds(467, 80, 98, 23);
		contentPane.add(btn_ThemDiem);
		
		JLabel lbl_KQuaHTap = new JLabel("Kết quả học tập:");
		lbl_KQuaHTap.setBounds(10, 115, 148, 14);
		contentPane.add(lbl_KQuaHTap);
		
		JButton btn_CapNhatDiem = new JButton("~Cập nhật bảng điểm");
		btn_CapNhatDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row= tableDiem.getSelectedRow();
				if(row==-1) {
					new ThemDiemFrame(sv, null).setVisible(true);
				}else {
					String ma= String.valueOf(tableDiem.getValueAt(row, 1));
					HocPhan hocP=hocPhanService.findByMa(ma);
					new ThemDiemFrame(sv, hocP).setVisible(true);
				}
				dispose();
			}
		});
		btn_CapNhatDiem.setBounds(467, 108, 187, 23);
		contentPane.add(btn_CapNhatDiem);
		if (sv.getTinhTrang()==true) {
			rdbtnTTHoatDong.setSelected(true);
		}else {
			rdbtnTTKhongHoatDong.setSelected(true);
		}
	}
	private void setTableHKData(SinhVien sinhV,HocKi hocK, int page) {
		int count = 1;
		SinhVien sv= sinhVienService.findByMa(sinhV.getMaSV());
		List<HocPhan> list_hp=sv.getListHp();
		if(hocK!=null) {
			List<HocPhan> list= new ArrayList<HocPhan>();
			for (HocPhan hocPhan : list_hp) {
				if(hocPhan.getMaHK().equals(hocK.getMaHk())==true) {
					list.add(hocPhan);
				}
			}
			list_hp=list;
		}
		if(list_hp.isEmpty()==false) {
			for (HocPhan hocP : list_hp) {
				HocKi hocKi=hocKiService.findByMa(hocP.getMaHK());
				defaultTable_BangDiem.addRow(new Object[] { count, hocP.getMaHP(), hocP.getTenHP(),hocKi.getTenHk(), 
						hocKi.getNgayBatDau(),hocKi.getNgayKetThuc(), diemService.findByMaSV_HP(sinhV.getMaSV(),hocP.getMaHP()).getDiem() });
				count++;
			}
		}
	}
}
