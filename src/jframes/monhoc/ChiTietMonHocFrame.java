package jframes.monhoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jframes.hocki.hocphan.ChiTietHocPhanFrame;
import model.HocKi;
import model.HocPhan;
import model.MonHoc;
import service.impl.HocKiService;
import service.impl.HocPhanService;
import service.impl.MonHocService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ChiTietMonHocFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_MaMH;
	private JLabel lblTenMH;
	private JTextField textField_TenMH;
	private JRadioButton rdbtnTTHoatDong;
	private JRadioButton rdbtnTTKhongHoatDong;
	private ButtonGroup buttonGroup;
	private JTable tableDsHP;
	private MonHocService monHocService;
	private HocPhanService hocPhanService;
	private DefaultTableModel defaultTable_DsHP;
	private JComboBox<String> comboBox_HKi;
	private HocKiService hocKiService;
	private List<HocPhan> listHp;

	/**
	 * Create the frame.
	 */
	public ChiTietMonHocFrame(final MonHoc monHoc) {
		monHocService= new MonHocService();
		hocPhanService=new HocPhanService();
		hocKiService=new HocKiService();
		setTitle("Chi tiết môn học");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaMH = new JLabel("Mã môn học:");
		lblMaMH.setBounds(10, 11, 77, 14);
		contentPane.add(lblMaMH);
		
		textField_MaMH = new JTextField();
		textField_MaMH.setEditable(false);
		textField_MaMH.setBounds(87, 8, 89, 20);
		textField_MaMH.setText(monHoc.getMaMH());
		contentPane.add(textField_MaMH);
		textField_MaMH.setColumns(10);
		
		lblTenMH = new JLabel("Tên môn học:");
		lblTenMH.setBounds(203, 11, 77, 14);
		contentPane.add(lblTenMH);
		
		textField_TenMH = new JTextField();
		textField_TenMH.setBounds(290, 8, 324, 20);
		textField_TenMH.setText(monHoc.getTenMH());
		contentPane.add(textField_TenMH);
		textField_TenMH.setColumns(10);
		
		rdbtnTTHoatDong = new JRadioButton("Đang hoạt động");
		rdbtnTTHoatDong.setBounds(202, 35, 129, 23);
		contentPane.add(rdbtnTTHoatDong);
		
		rdbtnTTKhongHoatDong = new JRadioButton("Không hoạt động");
		rdbtnTTKhongHoatDong.setBounds(343, 35, 129, 23);
		contentPane.add(rdbtnTTKhongHoatDong);
		
		buttonGroup= new ButtonGroup();
		buttonGroup.add(rdbtnTTHoatDong);
		buttonGroup.add(rdbtnTTKhongHoatDong);
		
		setRdbtTrangThai(monHoc);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					monHoc.setTenMH(textField_TenMH.getText());
					if(rdbtnTTHoatDong.isSelected()) {
						monHoc.setTrangThai(true);
					}else {
						monHoc.setTrangThai(false);
					}
					JOptionPane.showConfirmDialog(contentPane,"Xác nhận lưu?");
					monHocService.updateOne(monHoc);
				}catch (InputMismatchException immE) {
					JOptionPane.showMessageDialog(contentPane, "Nhập sai!");
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
				}
			}
		});
		btnLuu.setBounds(615, 7, 79, 23);
		contentPane.add(btnLuu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 684, 292);
		contentPane.add(scrollPane);
		
		tableDsHP = new JTable();
		scrollPane.setColumnHeaderView(tableDsHP);
		defaultTable_DsHP=new DefaultTableModel(new Object[][] {},
				new String[] { "STT","Mã HP","Tên HP","Môn học","Ngày bắt đầu","Ngày kết thúc","Trạng thái","Lịch thi"});
		listHp=monHoc.getListHP();
		setTableHocPhanData(listHp, 1);
		tableDsHP.setModel(defaultTable_DsHP);
		scrollPane.setViewportView(tableDsHP);
		
		comboBox_HKi = new JComboBox<String>();
		comboBox_HKi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				defaultTable_DsHP.setRowCount(0);
				if(comboBox_HKi.getSelectedIndex()==0) {
					listHp=monHoc.getListHP();
				}else {
					List<HocPhan> list= new ArrayList<HocPhan>();
					String split[]=comboBox_HKi.getSelectedItem().toString().split("/");
					HocKi hki=null;
					for (HocKi hk : hocKiService.findAll()) {
						if(hk.getTenHk().equals(split[0])==true&&hk.getNamHoc().equals(split[1])==true) {
							hki=hk;
						}
					}
					for (HocPhan hp : hocPhanService.findByMaHK(hki.getMaHk())) {
						if(hp.getMaMH().equals(monHoc.getMaMH())==true) {
							list.add(hp);
						}
					}
					listHp=list;
				}
				setTableHocPhanData(listHp, 1);
			}
		});
		comboBox_HKi.addItem("Tất cả học kì");
		for (HocKi hKi : hocKiService.findAll()) {
			comboBox_HKi.addItem(hKi.getTenHk()+"/"+hKi.getNamHoc());
		}
		comboBox_HKi.setSelectedIndex(0);
		comboBox_HKi.setBounds(10, 61, 129, 22);
		contentPane.add(comboBox_HKi);
		
		JLabel lblNewLabel = new JLabel("Danh sách học phần");
		lblNewLabel.setBounds(10, 94, 129, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnThemHocPhan = new JButton("+Thêm học phần");
		btnThemHocPhan.setBounds(541, 85, 152, 23);
		contentPane.add(btnThemHocPhan);
		
		JButton btnChiTiet = new JButton("Chi tiêt");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row= tableDsHP.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(contentPane, "Không có học phần nào được chọn!");
				}else {
					String ma=String.valueOf(tableDsHP.getValueAt(row, 1));
					HocPhan hp=hocPhanService.findByMa(ma);
					new ChiTietHocPhanFrame(hp).setVisible(true);
					dispose();
				}
			}
		});
		btnChiTiet.setBounds(442, 85, 89, 23);
		contentPane.add(btnChiTiet);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_TenMH.setText(monHoc.getTenMH());
				setRdbtTrangThai(monHoc);
			}
		});
		btnLamMoi.setBounds(343, 85, 89, 23);
		contentPane.add(btnLamMoi);
		
		JButton btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTroVe.setBounds(605, 416, 89, 23);
		contentPane.add(btnTroVe);
	}
	private void setRdbtTrangThai(MonHoc mh) {
		if(mh.getTrangThai()==true) {
			rdbtnTTHoatDong.setSelected(true);
		}else {
			rdbtnTTKhongHoatDong.setSelected(true);
		}
	}
	private void setTableHocPhanData(List<HocPhan> listHp, int page) {
		int count=1;
		if(listHp.isEmpty()==false) {
			for (int i = 0; i < listHp.size(); i++) {
				String tenMH=monHocService.findByMa(listHp.get(0).getMaMH()).getTenMH();
				defaultTable_DsHP.addRow(new Object[] { count, listHp.get(i).getMaHP(), listHp.get(i).getTenHP(),tenMH,
						 listHp.get(i).getNgayBatDau(),listHp.get(i).getNgayKetThuc(),listHp.get(i).getStringTrangThai(),"--"});
				count++;
			}
		}
	}
}
