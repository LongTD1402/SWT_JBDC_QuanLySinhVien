package jframes.monhoc;

import java.util.Date;
import java.util.InputMismatchException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import model.MonHoc;
import service.impl.MonHocService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ThemMonHocFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMaMH;
	private JTextField textTenMH;
	private JDateChooser jDateChooser;
	private JButton btnLamMoi;
	private JButton btnThem;
	private MonHoc monHoc;
	private MonHocService monHocService;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public ThemMonHocFrame(long lastIndex) {
		monHoc=null;
		monHocService= new MonHocService();
		setAlwaysOnTop(true);
		setTitle("Thêm môn học");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaMH = new JLabel("Mã môn học:");
		lblMaMH.setBounds(10, 14, 79, 14);
		contentPane.add(lblMaMH);
		
		textFieldMaMH = new JTextField();
		textFieldMaMH.setEditable(false);
		textFieldMaMH.setBounds(88, 11, 99, 20);
		textFieldMaMH.setText("MH"+String.format("%05d",(lastIndex+1)));
		contentPane.add(textFieldMaMH);
		textFieldMaMH.setColumns(10);
		
		JLabel lblTenMH = new JLabel("Tên môn học:");
		lblTenMH.setBounds(10, 55, 79, 14);
		contentPane.add(lblTenMH);
		
		textTenMH = new JTextField();
		textTenMH.setBounds(90, 52, 284, 20);
		contentPane.add(textTenMH);
		textTenMH.setColumns(10);
		
		JLabel lblStartTime = new JLabel("Thời gian mở:");
		lblStartTime.setBounds(10, 144, 86, 14);
		contentPane.add(lblStartTime);
		
		jDateChooser = new JDateChooser();
		jDateChooser.setDateFormatString("yyyy-MM-dd");
		Date date =new Date();
		jDateChooser.setDate(date);
		jDateChooser.setBounds(94, 138, 140, 20);
		contentPane.add(jDateChooser);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textTenMH.setText("");
			}
		});
		btnLamMoi.setBounds(10, 287, 89, 23);
		contentPane.add(btnLamMoi);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					monHoc= new MonHoc();
					monHoc.setMaMH(textFieldMaMH.getText());
					monHoc.setTenMH(textTenMH.getText());
					monHoc.setTrangThai(true);
				}catch (InputMismatchException immE) {
					JOptionPane.showMessageDialog(contentPane, "Nhập sai!");
					monHoc=null;
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
					monHoc=null;
				}
				if(monHoc != null) {
					int click=JOptionPane.showConfirmDialog(null,"Xác nhận thêm?");
					if(click==JOptionPane.YES_OPTION) {
						monHocService.insertNew(monHoc);
						dispose();
					}
					if(click==JOptionPane.NO_OPTION) {
						dispose();
					}
					
				}
			}
		});
		btnThem.setBounds(285, 287, 89, 23);
		contentPane.add(btnThem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 169, 364, 113);
		contentPane.add(scrollPane);
		
		JButton btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTroVe.setBounds(185, 287, 89, 23);
		contentPane.add(btnTroVe);
		
		JRadioButton rdbtnDangHD = new JRadioButton("Đang hoạt động");
		buttonGroup.add(rdbtnDangHD);
		rdbtnDangHD.setBounds(6, 91, 140, 23);
		contentPane.add(rdbtnDangHD);

		JRadioButton rdbtnKhongHD = new JRadioButton("Không hoạt động");
		buttonGroup.add(rdbtnKhongHD);
		rdbtnKhongHD.setBounds(165, 91, 140, 23);
		contentPane.add(rdbtnKhongHD);
		
		rdbtnDangHD.setSelected(true);
	}
}
