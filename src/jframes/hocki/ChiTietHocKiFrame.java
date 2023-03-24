package jframes.hocki;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import jframes.hocki.hocphan.ChiTietHocPhanFrame;
import jframes.hocki.hocphan.HocPhanFrame;
import model.HocKi;
import model.HocPhan;
import model.MonHoc;
import service.impl.HocKiService;
import service.impl.HocPhanService;
import service.impl.MonHocService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ChiTietHocKiFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMaHki;
	private JTextField textFieldSearchTenHP;
	private JTable tableHocPhan;
	private DefaultTableModel defaultTable_HocPhan;
	private HocPhanService hocPhanSevice;
	private MonHocService monHocService;
	private List<HocPhan> listHp;
	private JDateChooser jdc_NgayBatDau;
	private JDateChooser jdc_NgayKetThuc;
	private JTextField txtKtThc;
	private JTextField textField_seo;
	private JButton btnLuu;
	private JLabel lblSeo;
	/**
	 * Create the frame.
	 */
	public ChiTietHocKiFrame(final HocKi hk) {
		hocPhanSevice= new HocPhanService();
		monHocService= new MonHocService();
		listHp= hk.getListHp();
		setTitle("Chi tiết học kì");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHki = new JLabel("Mã học kì:");
		lblHki.setBounds(10, 11, 70, 14);
		contentPane.add(lblHki);
		
		textFieldMaHki = new JTextField();
		textFieldMaHki.setEditable(false);
		textFieldMaHki.setBounds(70, 8, 86, 20);
		textFieldMaHki.setText(hk.getMaHk());
		contentPane.add(textFieldMaHki);
		textFieldMaHki.setColumns(10);
		
		JLabel lblTenHki = new JLabel("New label");
		lblTenHki.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenHki.setBounds(193, 5, 315, 28);
		lblTenHki.setText(hk.getTenHk()+" / "+"Năm học "+hk.getNamHoc());
		contentPane.add(lblTenHki);
		
		jdc_NgayBatDau = new JDateChooser();
		jdc_NgayBatDau.setDateFormatString("yyyy-MM-dd");
		try {
			jdc_NgayBatDau.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(hk.getNgayBatDau()));
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(contentPane, "Lỗi!");
			jdc_NgayBatDau.setDate(null);
		}
		jdc_NgayBatDau.setBounds(91, 72, 111, 20);
		contentPane.add(jdc_NgayBatDau);
		
		jdc_NgayKetThuc = new JDateChooser();
		jdc_NgayKetThuc.setDateFormatString("yyyy-MM-dd");
		try {
			jdc_NgayKetThuc.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(hk.getNgayKetThuc()));
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(contentPane, "Lỗi!");
			jdc_NgayKetThuc.setDate(null);
		}
		jdc_NgayKetThuc.setBounds(322, 72, 111, 20);
		contentPane.add(jdc_NgayKetThuc);
		
		JScrollPane scrollPaneHP = new JScrollPane();
		scrollPaneHP.setBounds(10, 103, 684, 293);
		contentPane.add(scrollPaneHP);
		
		tableHocPhan = new JTable();
		scrollPaneHP.setColumnHeaderView(tableHocPhan);
		defaultTable_HocPhan =new DefaultTableModel(new Object[][] {}, 
			new String[] {"STT","Mã HP","Tên HP","Môn học","Ngày bắt đầu","Ngày kết thúc","Trạng thái","Lịch thi"}
		);
		setTableHocPhanData(listHp, 1);
		tableHocPhan.setModel(defaultTable_HocPhan);
		scrollPaneHP.setViewportView(tableHocPhan);
		JLabel lblTimTenHP = new JLabel("Tên học phần:");
		lblTimTenHP.setBounds(10, 43, 81, 14);
		contentPane.add(lblTimTenHP);
		
		textFieldSearchTenHP = new JTextField();
		textFieldSearchTenHP.setBounds(92, 41, 86, 20);
		contentPane.add(textFieldSearchTenHP);
		textFieldSearchTenHP.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable_HocPhan.setRowCount(0);
				setTableHocPhanData(hocPhanSevice.findByTenHP(textFieldSearchTenHP.getText()), 1);
			}
		});
		btnTimKiem.setBounds(188, 40, 89, 23);
		contentPane.add(btnTimKiem);
		
		JButton btnThemHocPhan = new JButton("+Thêm học phần");
		btnThemHocPhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long lastIndex=0;
				MonHoc mh0=monHocService.findAll().get(0);
				List<HocPhan> list_byHk = hocPhanSevice.findByMaHK(hk.getMaHk());
				List<HocPhan> list=new ArrayList<>();
				if(list_byHk.isEmpty()==false) {
					for (HocPhan hocPhan : list_byHk) {
						if(hocPhan.getMaMH().equals(mh0.getMaMH())) {
							list.add(hocPhan);
						}
					}
				}
				lastIndex=hocPhanSevice.lastIndex(list, mh0, hk);
				new HocPhanFrame(lastIndex,mh0,hk).setVisible(true);
				dispose();
			}
		});
		btnThemHocPhan.setBounds(566, 40, 128, 23);
		contentPane.add(btnThemHocPhan);
		
		JButton btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=tableHocPhan.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(contentPane, "Không có học phần nào được chọn!");
				}else {
					String ma=String.valueOf(tableHocPhan.getValueAt(row, 1));
					HocPhan hp=hocPhanSevice.findByMa(ma);
					new ChiTietHocPhanFrame(hp).setVisible(true);
				}
			}
		});
		btnChiTiet.setBounds(467, 40, 89, 23);
		contentPane.add(btnChiTiet);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable_HocPhan.setRowCount(0);
				setTableHocPhanData(listHp, 1);
				textFieldSearchTenHP.setText("");
			}
		});
		btnLamMoi.setBounds(322, 40, 89, 23);
		contentPane.add(btnLamMoi);
		
		JButton btnNewButton = new JButton("Trở về");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(605, 407, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setBounds(10, 75, 81, 14);
		contentPane.add(lblNgayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setBounds(236, 75, 89, 14);
		contentPane.add(lblNgayKetThuc);
		
		txtKtThc = new JTextField();
		txtKtThc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtKtThc.setEditable(false);
		txtKtThc.setText("Đã kết thúc");
		txtKtThc.setBounds(566, 72, 128, 20);
		contentPane.add(txtKtThc);
		txtKtThc.setColumns(10);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(467, 72, 89, 23);
		contentPane.add(btnLuu);
		
		lblSeo = new JLabel("code:");
		lblSeo.setBounds(510, 11, 46, 14);
		contentPane.add(lblSeo);
		
		textField_seo = new JTextField();
		textField_seo.setBounds(566, 8, 128, 20);
		textField_seo.setText(hk.getSeo());
		contentPane.add(textField_seo);
		textField_seo.setColumns(10);
		
		
	}
	private void setTableHocPhanData(List<HocPhan> listHp, int page) {
		int count=1;
		if(listHp.isEmpty()==false) {
			for (int i = 0; i < listHp.size(); i++) {
				String tenMH= monHocService.findByMa(listHp.get(i).getMaMH()).getTenMH();
				defaultTable_HocPhan.addRow(new Object[] { count, listHp.get(i).getMaHP(), listHp.get(i).getTenHP(),tenMH,
						 listHp.get(i).getNgayBatDau(),listHp.get(i).getNgayKetThuc(),listHp.get(i).getStringTrangThai(),"--"});
				count++;
			}
		}
	}
}
