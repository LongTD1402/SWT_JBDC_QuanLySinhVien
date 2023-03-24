package jframes.Diem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import service.impl.HocKiService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class KetQuaHocTap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_tenSV;
	private JTextField textField_MSV;
	private JTextField textField_HocPhan;
	private JTextField textField_Trang;
	private JTable table_dsDiem;
	private DefaultTableModel dafaultTable_dsDiem;
	private JComboBox<String> comboBox_dsHocKi;
	private HocKiService hocKiService;

	/**
	 * Create the frame.
	 */
	public KetQuaHocTap() {
		hocKiService = new HocKiService();
		setTitle("Bảng điểm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_SinhVien = new JLabel("Sinh viên:");
		lbl_SinhVien.setBounds(10, 44, 59, 14);
		contentPane.add(lbl_SinhVien);

		textField_tenSV = new JTextField();
		textField_tenSV.setBounds(71, 41, 171, 20);
		contentPane.add(textField_tenSV);
		textField_tenSV.setColumns(10);

		JLabel lbl_MSV = new JLabel("MSV:");
		lbl_MSV.setBounds(274, 44, 46, 14);
		contentPane.add(lbl_MSV);

		JButton btn_chonSV = new JButton("Chọn");
		btn_chonSV.setBounds(568, 40, 85, 23);
		contentPane.add(btn_chonSV);

		textField_MSV = new JTextField();
		textField_MSV.setBounds(335, 41, 86, 20);
		contentPane.add(textField_MSV);
		textField_MSV.setColumns(10);

		JLabel lbl_HocPhan = new JLabel("Học phần:");
		lbl_HocPhan.setBounds(274, 15, 59, 14);
		contentPane.add(lbl_HocPhan);

		textField_HocPhan = new JTextField();
		textField_HocPhan.setBounds(335, 12, 220, 20);
		contentPane.add(textField_HocPhan);
		textField_HocPhan.setColumns(10);

		comboBox_dsHocKi = new JComboBox<String>();
		comboBox_dsHocKi.setBounds(70, 11, 172, 22);
		contentPane.add(comboBox_dsHocKi);

		JLabel lblHocKi = new JLabel("Học kì:");
		lblHocKi.setBounds(10, 15, 46, 14);
		contentPane.add(lblHocKi);

		JButton btnChonMsv = new JButton("Chọn");
		btnChonMsv.setBounds(568, 11, 85, 23);
		contentPane.add(btnChonMsv);

		JScrollPane scrollPane_bangDiem = new JScrollPane();
		scrollPane_bangDiem.setBounds(10, 98, 644, 291);
		contentPane.add(scrollPane_bangDiem);

		table_dsDiem = new JTable();
		scrollPane_bangDiem.setColumnHeaderView(table_dsDiem);

		JButton btn_ThemDiem = new JButton("+Thêm điểm");
		btn_ThemDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemDiemFrame(null, null).setVisible(true);
				dispose();
			}
		});
		btn_ThemDiem.setBounds(540, 74, 114, 23);
		contentPane.add(btn_ThemDiem);

		JButton btn_ChiTiet = new JButton("Chi tiết");
		btn_ChiTiet.setBounds(441, 74, 89, 23);
		contentPane.add(btn_ChiTiet);

		JButton btn_Truoc = new JButton("Trước");
		btn_Truoc.setBounds(158, 390, 89, 23);
		contentPane.add(btn_Truoc);

		textField_Trang = new JTextField();
		textField_Trang.setBounds(247, 391, 46, 20);
		contentPane.add(textField_Trang);
		textField_Trang.setColumns(10);

		JLabel lbl_TongTrang = new JLabel("/00");
		lbl_TongTrang.setBounds(294, 394, 46, 14);
		contentPane.add(lbl_TongTrang);

		JButton btn_Sau = new JButton("Sau");
		btn_Sau.setBounds(320, 390, 89, 23);
		contentPane.add(btn_Sau);

		JButton btn_DenTrang = new JButton("Đến");
		btn_DenTrang.setBounds(412, 390, 89, 23);
		contentPane.add(btn_DenTrang);

		JButton btn_TroVe = new JButton("Trở về");
		btn_TroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_TroVe.setBounds(564, 407, 89, 23);
		contentPane.add(btn_TroVe);
	}
}
