package jframes.sinhvien;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import model.GioiTinh;
import model.SinhVien;
import service.impl.SinhVienService;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ThemSinhVienFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_MaSV;
	private JTextField textField_HoDem;
	private JButton btnLamMoi;
	private JTextField textField_Ten;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	private JRadioButton rdbtnKhac;
	private ButtonGroup btnGroup;
	private JDateChooser jDateChooser;
	private SinhVienService sinhVienService;
	private SinhVien newSv= null;

	/**
	 * Create the frame.
	 */
	public ThemSinhVienFrame(long lastIndex) {
		sinhVienService=new SinhVienService();
		setAlwaysOnTop(true);
		setTitle("Thêm sinh viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_MaSV = new JTextField();
		textField_MaSV.setEditable(false);
		textField_MaSV.setBounds(90, 11, 103, 20);
		textField_MaSV.setText("SV"+String.format("%05d",(lastIndex+1)));
		contentPane.add(textField_MaSV);
		textField_MaSV.setColumns(10);
		
		textField_HoDem = new JTextField();
		textField_HoDem.setBounds(90, 39, 193, 20);
		contentPane.add(textField_HoDem);
		textField_HoDem.setColumns(10);
		
		JButton btnThemMoiSV = new JButton("Thêm");
		btnThemMoiSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newSv= new SinhVien();
					newSv.setMaSV(textField_MaSV.getText());
					newSv.setHoDem(textField_HoDem.getText());
					newSv.setTen(textField_Ten.getText());
					if(rdbtnNam.isSelected()) {
						newSv.setGioiTinh(GioiTinh.Nam);
					}if(rdbtnNu.isSelected()) {
						newSv.setGioiTinh(GioiTinh.Nu);
					}if(rdbtnKhac.isSelected()) {
						newSv.setGioiTinh(GioiTinh.Khac);
					}
					newSv.setNgaySinh(jDateChooser.getDate());
					newSv.setTinhTrang(true);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					newSv=null;
				}
				if(newSv!=null) {
					int click=JOptionPane.showConfirmDialog(contentPane, "Xác nhận thêm?");
					if(click==JOptionPane.YES_OPTION) {
						System.out.println(newSv.getHoDem()+ " "+newSv.getTen()+" "+newSv.getGioiTinh().value()+" " +
								newSv.getNgaySinh().toString()+newSv.getStringTinhTrang());
						sinhVienService.insertNew(newSv);
						dispose();
					}
					if(click==JOptionPane.NO_OPTION) {
						dispose();
					}
					
				}
			}
		});
		btnThemMoiSV.setBounds(285, 269, 89, 23);
		contentPane.add(btnThemMoiSV);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_HoDem.setText(null);
				textField_Ten.setText(null);
			}
		});
		btnLamMoi.setBounds(10, 269, 103, 23);
		contentPane.add(btnLamMoi);
		
		JLabel lblNewLabel = new JLabel("Mã sinh viên:");
		lblNewLabel.setBounds(10, 14, 96, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTenDem = new JLabel("Họ-Tên đệm:");
		lblTenDem.setBounds(10, 42, 81, 14);
		contentPane.add(lblTenDem);
		
		JLabel lblTenSV = new JLabel("Tên:");
		lblTenSV.setBounds(10, 70, 46, 14);
		contentPane.add(lblTenSV);
		
		textField_Ten = new JTextField();
		textField_Ten.setBounds(90, 67, 193, 20);
		contentPane.add(textField_Ten);
		textField_Ten.setColumns(10);
		
		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(6, 102, 62, 23);
		contentPane.add(rdbtnNam);
		
		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(84, 102, 46, 23);
		contentPane.add(rdbtnNu);
		
		rdbtnKhac = new JRadioButton("Khác");
		rdbtnKhac.setSelected(true);
		rdbtnKhac.setBounds(168, 102, 109, 23);
		contentPane.add(rdbtnKhac);
		
		btnGroup= new ButtonGroup();
		btnGroup.add(rdbtnNam);
		btnGroup.add(rdbtnNu);
		btnGroup.add(rdbtnKhac);
		
		jDateChooser = new JDateChooser();
		jDateChooser.setDateFormatString("yyyy-MM-dd");
		Date date =new Date();
		jDateChooser.setDate(date);
		jDateChooser.setBounds(90, 132, 140, 20);
		contentPane.add(jDateChooser);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setBounds(10, 132, 70, 14);
		contentPane.add(lblNgaySinh);
	}
}
