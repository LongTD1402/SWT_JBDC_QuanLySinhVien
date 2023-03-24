package jframes.monhoc;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jframes.MainFrame;
import jframes.sinhvien.QuanLySinhVienFrame;
import model.MonHoc;
import service.impl.MonHocService;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuanLyMonHocFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearchTenMH;
	private JTable tableMonHoc;
	private DefaultTableModel defaultTableMonHoc;
	private MonHocService monHocService;
	private List<MonHoc> listMh;
	/**
	 * Create the frame.
	 */
	public QuanLyMonHocFrame() {
		monHocService= new MonHocService();
		listMh= monHocService.findAll();
		setTitle("Danh sách môn học");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		contentPane.add(menuBar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 924, 340);
		contentPane.add(scrollPane);
		
		tableMonHoc = new JTable();
		scrollPane.setColumnHeaderView(tableMonHoc);
		defaultTableMonHoc = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã môn", "Tên","Số học phần", "Trạng thái" });
		int page=1;
		setTableData(listMh, page);
		tableMonHoc.setModel(defaultTableMonHoc);
		scrollPane.setViewportView(tableMonHoc);
		
		JLabel lblSearchMonHoc = new JLabel("Tên môn học:");
		lblSearchMonHoc.setBounds(10, 27, 77, 14);
		contentPane.add(lblSearchMonHoc);
		
		textFieldSearchTenMH = new JTextField();
		textFieldSearchTenMH.setBounds(93, 24, 130, 20);
		contentPane.add(textFieldSearchTenMH);
		textFieldSearchTenMH.setColumns(10);
		
		JButton btnTimMH = new JButton("Tìm kiếm");
		btnTimMH.setBounds(226, 23, 89, 23);
		contentPane.add(btnTimMH);
		
		JButton btnThemMH = new JButton("+Thêm môn học");
		btnThemMH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long lastIndex= monHocService.lastIndex(listMh);
				new ThemMonHocFrame(lastIndex).setVisible(true);
				
			}
		});
		btnThemMH.setBounds(809, 23, 125, 23);
		contentPane.add(btnThemMH);
		
		JButton btnChiTietMH = new JButton("Chi tiết");
		btnChiTietMH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableMonHoc.getSelectedRow();
				if(row== -1) {
					JOptionPane.showMessageDialog(contentPane, "Không có môn học nào được chọn!");
				}else {
					String ma = String.valueOf(tableMonHoc.getValueAt(row, 1));
					MonHoc mh = monHocService.findByMa(ma);
//					System.out.println(mh.getListHP().get(0).getTenHP());
					new ChiTietMonHocFrame(mh).setVisible(true);
				}
			}
		});
		btnChiTietMH.setBounds(702, 23, 97, 23);
		contentPane.add(btnChiTietMH);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTableMonHoc.setRowCount(0);
				textFieldSearchTenMH.setText("");
				listMh = monHocService.findAll();
				setTableData(listMh, 1);
			}
		});
		btnLamMoi.setBounds(600, 23, 89, 23);
		contentPane.add(btnLamMoi);
	}
	private void setTableData(List<MonHoc> list, int page) {
		int count = 1;
		if (list.isEmpty() == false) {
			for (int i = 0; i < list.size(); i++) {
				defaultTableMonHoc.addRow(new Object[] { count, list.get(i).getMaMH(), list.get(i).getTenMH(),
							list.get(i).getListHP().size(),list.get(i).getStringTT() });
				count++;
			}
		} else {
			defaultTableMonHoc.addRow(new Object[] {});
		}
	}
}
