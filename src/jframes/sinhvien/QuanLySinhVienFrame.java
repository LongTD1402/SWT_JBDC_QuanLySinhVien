package jframes.sinhvien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.impl.DatabaseInfo;
import jframes.MainFrame;
import jframes.monhoc.QuanLyMonHocFrame;
import model.GioiTinh;
import model.SinhVien;
import service.impl.SinhVienService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class QuanLySinhVienFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneSinhVien;
	private JTextField textFieldSearchTenSV;
	private JTable tableSinhVien;
	private DefaultTableModel defaultTable;
	private DefaultTableModel defaultTableThongKe;
	private JTextField textField_denTrang;
	private JScrollPane scrollPane;
	private JTable tableThongKe;
	private JLabel lblTongTrang = new JLabel("00/00");;
	List<SinhVien> listSv;
	SinhVienService sinhVienService;
	int page=1;

	public QuanLySinhVienFrame() {
		listSv = new ArrayList<SinhVien>();
		sinhVienService = new SinhVienService();
		listSv = sinhVienService.findAll();
		System.out.println(listSv.get(1).getMaSV());
		setTitle("Danh sách sinh viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		contentPaneSinhVien = new JPanel();
		contentPaneSinhVien.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneSinhVien);
		contentPaneSinhVien.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 950, 22);

		JMenu mntmBack = new JMenu("Trở về");
		menuBar.add(mntmBack);

		JMenuItem mntmBackTrangChinh = new JMenuItem("Trang chính");
		mntmBackTrangChinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		mntmBack.add(mntmBackTrangChinh);
		
		JMenuItem mntmThoat = new JMenuItem("Thoát");
		mntmThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmBack.add(mntmThoat);
		
		JMenu mnDanhSach = new JMenu("Danh sách");
		menuBar.add(mnDanhSach);

		JMenuItem mntmMenuItem_SinhVien = new JMenuItem("Sinh viên");
		mntmMenuItem_SinhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLySinhVienFrame().setVisible(true);
				dispose();
			}
		});
		mnDanhSach.add(mntmMenuItem_SinhVien);

		JMenuItem mntmMenuItem_MonHoc = new JMenuItem("Môn học");
		mntmMenuItem_MonHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyMonHocFrame().setVisible(true);
				dispose();
			}
		});
		mntmMenuItem_SinhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyMonHocFrame().setVisible(true);
				dispose();
			}
		});
		mnDanhSach.add(mntmMenuItem_MonHoc);

		JMenu mnBangDiem = new JMenu("Bảng điểm");
		menuBar.add(mnBangDiem);

		JMenuItem mntmMenuItem_PhongThi = new JMenuItem("Phòng thi");
		mnBangDiem.add(mntmMenuItem_PhongThi);

		JMenuItem mntmMenuItem_TatCa = new JMenuItem("Tất cả ");
		mnBangDiem.add(mntmMenuItem_TatCa);

		JMenu mnThiCu = new JMenu("Thi cử");
		menuBar.add(mnThiCu);

		JMenu mnBienBan = new JMenu("Biên bản");
		menuBar.add(mnBienBan);
		contentPaneSinhVien.add(menuBar);

		JLabel lblNewLabel = new JLabel("Tên sinh viên:");
		lblNewLabel.setBounds(10, 33, 95, 14);
		contentPaneSinhVien.add(lblNewLabel);

		textFieldSearchTenSV = new JTextField();
		textFieldSearchTenSV.setBounds(95, 30, 183, 20);
		contentPaneSinhVien.add(textFieldSearchTenSV);
		textFieldSearchTenSV.setColumns(10);

		JButton btnTimSinhVien = new JButton("Tìm kiếm");
		btnTimSinhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldSearchTenSV.getText();
				listSv = sinhVienService.findByName(name);
				defaultTable.setRowCount(0);
				setTableData(listSv, 1);
			}
		});
		btnTimSinhVien.setBounds(288, 29, 89, 23);
		contentPaneSinhVien.add(btnTimSinhVien);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 924, 344);
		contentPaneSinhVien.add(scrollPane);

		tableSinhVien = new JTable();
		tableSinhVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setColumnHeaderView(tableSinhVien);
		defaultTable = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã SV", "Họ đệm", "Tên",
				"Ngày sinh", "Giới tính", "Tình trạng", "Số học phần", "Điểm trung bình" });
		
		setTableData(listSv, page);
		tableSinhVien.setModel(defaultTable);
		scrollPane.setViewportView(tableSinhVien);

		// -------------
		int row = tableSinhVien.getSelectedRow();

		// -------------

		JButton btnSapXep = new JButton("Sắp xếp");
		btnSapXep.setBounds(845, 407, 89, 23);
		contentPaneSinhVien.add(btnSapXep);

		JButton btnTrangTruoc = new JButton("Trang trước");
		btnTrangTruoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page>1) {
					defaultTable.setRowCount(0);
					page=page-1;
					setTableData(listSv, page);
				}
			}
		});
		btnTrangTruoc.setBounds(10, 407, 108, 23);
		contentPaneSinhVien.add(btnTrangTruoc);

		textField_denTrang = new JTextField();
		textField_denTrang.setBounds(358, 408, 55, 20);
		contentPaneSinhVien.add(textField_denTrang);
		textField_denTrang.setColumns(10);

		JButton btnTrangSau = new JButton("Trang sau");
		btnTrangSau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page<=listSv.size()/20) {
					defaultTable.setRowCount(0);
					page=page+1;
					setTableData(listSv, page);
				}
			}
		});
		btnTrangSau.setBounds(187, 407, 101, 23);
		contentPaneSinhVien.add(btnTrangSau);

		JButton btnDenTrang = new JButton("Đến");
		btnDenTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int trang=-1;
				try {
					trang=Integer.parseInt(textField_denTrang.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPaneSinhVien, "Nhập sai!");
					textField_denTrang.setText("");
				}
				if(trang>=1&&trang<=(listSv.size()/20+1)) {
					defaultTable.setRowCount(0);
					page=trang;
					setTableData(listSv, page);
				}else {
					JOptionPane.showMessageDialog(contentPaneSinhVien, "Nhập sai!");
				}
			}
		});
		btnDenTrang.setBounds(423, 407, 89, 23);
		contentPaneSinhVien.add(btnDenTrang);

		lblTongTrang.setBounds(128, 408, 49, 14);
		contentPaneSinhVien.add(lblTongTrang);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 438, 502, 52);
		contentPaneSinhVien.add(scrollPane_1);

		tableThongKe = new JTable();
		scrollPane_1.setRowHeaderView(tableThongKe);
		defaultTableThongKe = new DefaultTableModel(new Object[][] {},
				new String[] { "Tổng", "Giỏi", "Khá", "Trung bình", "Yếu" });
		tableThongKe.setModel(defaultTableThongKe);
		scrollPane_1.setViewportView(tableThongKe);

		JButton btnThemSV = new JButton("+Thêm sinh viên");
		btnThemSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long lastIndex = sinhVienService.lastIndex(listSv);
				new ThemSinhVienFrame(lastIndex).setVisible(true);
			}
		});
		btnThemSV.setBounds(794, 29, 140, 23);
		contentPaneSinhVien.add(btnThemSV);

		JComboBox comboBoxSapXepAll = new JComboBox();
		comboBoxSapXepAll.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Trang" }));
		comboBoxSapXepAll.setBounds(534, 407, 101, 22);
		contentPaneSinhVien.add(comboBoxSapXepAll);

		JButton btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableSinhVien.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPaneSinhVien, "Không có sinh viên nào được chọn");
				} else {
					String ma = String.valueOf(tableSinhVien.getValueAt(row, 1));
					new ChiTietSinhVienFrame(sinhVienService.findByMa(ma)).setVisible(true);
				}
			}
		});
		btnChiTiet.setBounds(676, 29, 109, 23);
		contentPaneSinhVien.add(btnChiTiet);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable.setRowCount(0);
				textFieldSearchTenSV.setText("");
				listSv = sinhVienService.findAll();
				setTableData(listSv, 1);
			}
		});
		btnLamMoi.setBounds(573, 29, 89, 23);
		contentPaneSinhVien.add(btnLamMoi);
	}

	private void setTableData(List<SinhVien> list, int page) {
		int count = 1;
		int tongTrang=list.size()/20+1;
		lblTongTrang.setText(page+"/"+tongTrang);
		if (list.isEmpty() == false) {
			for (int i = (page-1)*20; i < page*20; i++) {
				SinhVien sv= sinhVienService.findByMa(list.get(i).getMaSV());
				defaultTable.addRow(new Object[] { count, sv.getMaSV(), sv.getHoDem(),sv.getTen(), sv.getNgaySinh(),
						sv.getGioiTinh().value(),sv.getStringTinhTrang(),sv.getListHp().size(),sinhVienService.diemTB(sv)});
				if(i==(list.size()-1)){
					break;
				}
				count++;
			}
		} else {
			defaultTable.addRow(new Object[] {});
		}
	}
}
