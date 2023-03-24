package jframes.hocki.hocphan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import jframes.Diem.ThemDiemFrame;
import jframes.hocki.ChiTietHocKiFrame;
import jframes.sinhvien.ChiTietSinhVienFrame;
import model.Diem;
import model.HocKi;
import model.HocPhan;
import model.MonHoc;
import model.SinhVien;
import service.impl.DiemService;
import service.impl.HocKiService;
import service.impl.HocPhanService;
import service.impl.MonHocService;
import service.impl.SinhVienService;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ChiTietHocPhanFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMaHP;
	private JLabel lblHocKi;
	private JTextField textField_TenHP;
	private JLabel lblNgayBatDau;
	private JLabel lblNgayKetThuc;
	private JButton btnLamMoi;
	private JDateChooser jDateChooser_NBD;
	private JDateChooser jDatechooser_NKT;
	private JButton btnLuu;
	private HocPhan newHp=null;
	private HocKiService hocKiService;
	private HocPhanService hocPhanService;
	private MonHocService monHocService;
	private JTextField textField_maMh;
	private JLabel lblMaMH;
	private JLabel lbl_tenMH;
	private JLabel lblLabel_TenMh;
	private JLabel lblHinhThucThi;
	private JComboBox<Object> comboBox_HTThi;
	private JRadioButton rdbtnDangHD;
	private JRadioButton rdbtnKhongHD;
	private ButtonGroup btnGroup_TrangThai;
	private JButton btnTruoc;
	private JLabel lblTongTrang=new JLabel("00/00");
	private JButton btnSau;
	private JTextField textField_denTrang;
	private JButton btnDenTrang;
	private JButton btnChiTiet;
	private JTable tableDSSV;
	private DefaultTableModel defaultTable_dsSV;
	private JScrollPane scrollPaneDSSV ;
	private SinhVienService sinhVienService;
	private DiemService diemService;
	private HocPhan hocPhan=null;
	private List<SinhVien> listSv=null;
	private int page=1;
	/**
	 * Create the frame.
	 */
	public ChiTietHocPhanFrame(final HocPhan hp) {
		hocKiService= new HocKiService();
		hocPhanService=new HocPhanService();
		monHocService=new MonHocService();
		sinhVienService= new SinhVienService();
		diemService=new DiemService();
		hocPhan=hp;
		setTitle("Chi tiết học phần");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaHP = new JLabel("Mã:");
		lblMaHP.setBounds(10, 11, 35, 14);
		contentPane.add(lblMaHP);
		
		textFieldMaHP = new JTextField();
		textFieldMaHP.setEditable(false);
		textFieldMaHP.setBounds(45, 8, 105, 20);
		textFieldMaHP.setText(hp.getMaHP());
		contentPane.add(textFieldMaHP);
		textFieldMaHP.setColumns(10);
		
		textField_TenHP = new JTextField();
		textField_TenHP.setBounds(155, 8, 243, 20);
		textField_TenHP.setText(hp.getTenHP());
		contentPane.add(textField_TenHP);
		textField_TenHP.setColumns(10);
		
		lblHocKi = new JLabel();
		lblHocKi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblHocKi.setText(hocKiService.findByMa(hp.getMaHK()).getTenHk()+" / Năm học"+ 
							hocKiService.findByMa(hp.getMaHK()).getNamHoc());
		lblHocKi.setBounds(10, 40, 230, 14);
		contentPane.add(lblHocKi);
		
		lblNgayBatDau = new JLabel("Bắt đầu:");
		lblNgayBatDau.setBounds(289, 40, 60, 14);
		contentPane.add(lblNgayBatDau);
		
		lblNgayKetThuc = new JLabel("Kết thúc:");
		lblNgayKetThuc.setBounds(499, 40, 60, 14);
		contentPane.add(lblNgayKetThuc);
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date dateBd=new Date();
		try {
			dateBd=sdf.parse(hp.getNgayBatDau());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi!");
		}
		jDateChooser_NBD = new JDateChooser();
		jDateChooser_NBD.setDateFormatString("yyyy-MM-dd");
		jDateChooser_NBD.setDate(dateBd);
		jDateChooser_NBD.setBounds(353, 35, 120, 20);
		contentPane.add(jDateChooser_NBD);
		
		Date dateKt=new Date();
		try {
			dateKt=sdf.parse(hp.getNgayKetThuc());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi!");
		}
		jDatechooser_NKT = new JDateChooser();
		jDatechooser_NKT.setDateFormatString("yyyy-MM-dd");
		jDatechooser_NKT.setDate(dateKt);
		jDatechooser_NKT.setBounds(569, 35, 120, 20);
		contentPane.add(jDatechooser_NKT);
		
		JButton btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTroVe.setBounds(610, 492, 89, 23);
		contentPane.add(btnTroVe);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable_dsSV.setRowCount(0);
				textField_TenHP.setText(hp.getTenHP());
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				Date dateBd=new Date();
				try {
					dateBd=sdf.parse(hp.getNgayBatDau());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Lỗi!");
				}
				jDateChooser_NBD.setDate(dateBd);
				Date dateKt=new Date();
				try {
					dateKt=sdf.parse(hp.getNgayBatDau());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Lỗi!");
				}
				jDatechooser_NKT.setDate(dateKt);
				List<SinhVien> list=hocPhan.getListSV();
				listSv=list;
				setTableData(listSv, 1);
			}
		});
		btnLamMoi.setBounds(100, 95, 89, 23);
		contentPane.add(btnLamMoi);
		
		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SimpleDateFormat sDf= new SimpleDateFormat("yyyy-MM-dd");
					newHp= new HocPhan();
					newHp.setMaHP(textFieldMaHP.getText());
					newHp.setTenHP(textField_TenHP.getText());
					newHp.setMaMH(textField_maMh.getText());
					newHp.setNgayBatDau(sDf.format(jDateChooser_NBD.getDate()));
					newHp.setNgayKetThuc(sDf.format(jDatechooser_NKT.getDate()));
					newHp.setHinhThucThi(comboBox_HTThi.getSelectedItem().toString());
					System.out.println(comboBox_HTThi.getSelectedItem().toString());
					if(rdbtnDangHD.isSelected()) {
						newHp.setTrangThai(true);
					}else {
						newHp.setTrangThai(false);
					}
					
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					newHp=null;
				}
				if(newHp!=null) {
					int click=JOptionPane.showConfirmDialog(contentPane, "Xác nhận sửa?");
					if(click==JOptionPane.YES_OPTION){
						try {
							hocPhanService.updateOne(newHp);
							new ChiTietHocKiFrame(hocKiService.findByMa(newHp.getMaHK())).setVisible(true);
							dispose();
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(contentPane, e2.getMessage());
							dispose();
						}
					}
					if(click==JOptionPane.NO_OPTION) {
						dispose();
					}
				}
				
			}
		});
		btnLuu.setBounds(605, 65, 89, 23);
		contentPane.add(btnLuu);
		
		lblMaMH = new JLabel("Mã môn:");
		lblMaMH.setBounds(577, 11, 52, 14);
		contentPane.add(lblMaMH);
		
		lbl_tenMH = new JLabel("Môn học:");
		lbl_tenMH.setBounds(405, 11, 60, 14);
		contentPane.add(lbl_tenMH);
		
		lblLabel_TenMh = new JLabel("");
		lblLabel_TenMh.setBounds(465, 11, 110, 14);
		lblLabel_TenMh.setText(monHocService.findAll().get(0).getTenMH());
		contentPane.add(lblLabel_TenMh);
		
		textField_maMh = new JTextField();
		textField_maMh.setEditable(false);
		textField_maMh.setBounds(634, 8, 57, 20);
		textField_maMh.setText(monHocService.findAll().get(0).getMaMH());
		contentPane.add(textField_maMh);
		textField_maMh.setColumns(10);
		
		lblHinhThucThi = new JLabel("Hình thức thi:");
		lblHinhThucThi.setBounds(10, 69, 86, 14);
		contentPane.add(lblHinhThucThi);
		
		comboBox_HTThi = new JComboBox<Object>();
		comboBox_HTThi.setModel(new DefaultComboBoxModel<Object>(new String[] {"Tự luận", "Thực hành", "Tiểu luận"}));
		comboBox_HTThi.setSelectedItem(hp.getHinhThucThi());
		comboBox_HTThi.setBounds(106, 65, 115, 22);
		contentPane.add(comboBox_HTThi);
		
		rdbtnDangHD = new JRadioButton("Đang hoạt động");
		rdbtnDangHD.setBounds(229, 64, 134, 23);
		contentPane.add(rdbtnDangHD);
		
		rdbtnKhongHD = new JRadioButton("Không hoạt động");
		rdbtnKhongHD.setBounds(382, 64, 149, 23);
		contentPane.add(rdbtnKhongHD);
		
		btnGroup_TrangThai= new ButtonGroup();
		btnGroup_TrangThai.add(rdbtnDangHD);
		btnGroup_TrangThai.add(rdbtnKhongHD);
		rdbtnDangHD.setSelected(true);
		
		if(hp.getTrangThai()==true) {
			rdbtnDangHD.setSelected(true);
		}else {
			rdbtnKhongHD.setSelected(true);
		}
		JButton btnLichThi = new JButton("Lịch thi");
		btnLichThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLichThi.setBounds(10, 95, 89, 23);
		contentPane.add(btnLichThi);
		
		JButton btnBangDiem = new JButton("Bảng điểm");
		btnBangDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBangDiem.setBounds(430, 95, 105, 23);
		contentPane.add(btnBangDiem);
		
		JButton btnThemDsSv = new JButton("~ Cập nhật bảng điểm");
		btnThemDsSv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=tableDSSV.getSelectedRow();
				if(row==-1) {
					new ThemDiemFrame(null, hp).setVisible(true);;
					dispose();
				}else {
					String ma=String.valueOf(tableDSSV.getValueAt(row, 1));
					SinhVien sv= sinhVienService.findByMa(ma);
					new ThemDiemFrame(sv,hp).setVisible(true);
					dispose();
				}
			}
		});
		btnThemDsSv.setBounds(544, 95, 150, 23);
		contentPane.add(btnThemDsSv);
		
		btnTruoc = new JButton("Trước");
		btnTruoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page>1) {
					defaultTable_dsSV.setRowCount(0);
					page=page-1;
					setTableData(listSv, page);
				}
			}
		});
		btnTruoc.setBounds(10, 465, 89, 23);
		contentPane.add(btnTruoc);
		
		lblTongTrang = new JLabel("00/00");
		lblTongTrang.setBounds(100, 467, 60, 14);
		contentPane.add(lblTongTrang);
		
		btnSau = new JButton("Sau");
		btnSau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page<=listSv.size()/20) {
					defaultTable_dsSV.setRowCount(0);
					page=page+1;
					setTableData(listSv, page);
				}
			}
		});
		btnSau.setBounds(159, 465, 89, 23);
		contentPane.add(btnSau);
		
		textField_denTrang = new JTextField();
		textField_denTrang.setBounds(279, 466, 60, 20);
		contentPane.add(textField_denTrang);
		textField_denTrang.setColumns(10);
		
		btnDenTrang = new JButton("Đến");
		btnDenTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int trang=-1;
				try {
					trang=Integer.parseInt(textField_denTrang.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Nhập sai!");
					textField_denTrang.setText("");
				}
				if(trang>=1&&trang<=(listSv.size()/20+1)) {
					defaultTable_dsSV.setRowCount(0);
					page=trang;
					setTableData(listSv, page);
				}else {
					JOptionPane.showMessageDialog(contentPane, "Nhập sai!");
				}
			}
		});
		btnDenTrang.setBounds(343, 465, 89, 23);
		contentPane.add(btnDenTrang);
		
		btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row= tableDSSV.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(contentPane, "Không có sinh viên nào được chọn");
				}else {
					String ma = String.valueOf(tableDSSV.getValueAt(row, 1));
					SinhVien sv = sinhVienService.findByMa(ma);
					new ChiTietSinhVienFrame(sv).setVisible(true);
				}
			}
		});
		btnChiTiet.setBounds(330, 95, 89, 23);
		contentPane.add(btnChiTiet);
		
		scrollPaneDSSV = new JScrollPane();
		scrollPaneDSSV.setBounds(10, 118, 684, 345);
		contentPane.add(scrollPaneDSSV);
		
		tableDSSV = new JTable();
		scrollPaneDSSV.setColumnHeaderView(tableDSSV);
		defaultTable_dsSV = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã sinh viên", "Họ tên đệm","Tên","Điếm","Lịch thi", "Trạng thái" });
		listSv=hocPhan.getListSV();
		setTableData(listSv, page);
		tableDSSV.setModel(defaultTable_dsSV);
		scrollPaneDSSV.setViewportView(tableDSSV);
	}
	private void setTableData(List<SinhVien> listSV, int page) {
		int count = 1;
		int tongTrang=listSv.size()/20+1;
		lblTongTrang.setText(page+"/"+tongTrang);
		if (listSV.isEmpty() == false) {
			for (int i = (page-1)*20; i < page*20; i++) {
				SinhVien sinhVien=sinhVienService.findByMa(listSV.get(i).getMaSV());
				String score=null;
				for (Diem diem : diemService.findByMaHP(hocPhan.getMaHP())) {
					if(diem.getMaSV().equals(sinhVien.getMaSV())==true) {
						score=diem.getDiem()+"";
					}
				}
				defaultTable_dsSV.addRow(new Object[] { count, sinhVien.getMaSV(), sinhVien.getHoDem(),
						sinhVien.getTen(),score,"--","--" });
				count++;
				if(i==(listSV.size()-1)){
					break;
				}
			}
		}
	}
}
