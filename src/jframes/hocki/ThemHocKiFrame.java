package jframes.hocki;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import model.HocKi;
import service.impl.HocKiService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class ThemHocKiFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_MaHK;
	private JTextField textField_seo;
	private JTextField textField_TenHK;
	private JTextField textField_NhapNH;
	private JComboBox<String> comboBox_dsNamHoc;
	private JButton btn_LamMoi;
	private JButton btnTroVe;
	private HocKi newHki = null;
	private HocKiService hocKiService;
	private JDateChooser jdc_NgayBatDau;
	private JDateChooser jdc_NgayKetThuc;

	/**
	 * Create the frame.
	 */
	public ThemHocKiFrame( final String namHoc) {
		hocKiService=new HocKiService();
		setTitle("Thêm học kì");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaHK = new JLabel("Mã học kì:");
		lblMaHK.setBounds(10, 11, 69, 14);
		contentPane.add(lblMaHK);
		
		textField_NhapNH = new JTextField();
		textField_NhapNH.setBounds(305, 74, 119, 20);
		contentPane.add(textField_NhapNH);
		textField_NhapNH.setColumns(10);
		
		comboBox_dsNamHoc = new JComboBox<String>();
		comboBox_dsNamHoc.setBounds(78, 73, 124, 22);
		List<HocKi> listNHFilter=new ArrayList<HocKi>();
		listNHFilter.add(hocKiService.getHocKiHienTai());
		for (HocKi hocKi : hocKiService.findAll()) {
			boolean check=true;
			for (HocKi hKi : listNHFilter) {
				if(hKi.soSanhNamHoc(hocKi)==true) {
					check=false;
					break;
				}
			}
			if(check==true) {
				listNHFilter.add(hocKi);
				check=true;
			}else {
				continue;
			}
		}	
		for (HocKi hocKi : listNHFilter) {
			comboBox_dsNamHoc.addItem(hocKi.getNamHoc());
		}
		
		contentPane.add(comboBox_dsNamHoc);
		
		textField_MaHK = new JTextField();
		textField_MaHK.setEditable(false);
		textField_MaHK.setBounds(78, 8, 86, 20);
		
		textField_seo = new JTextField();
		textField_seo.setBounds(305, 8, 119, 20);
		contentPane.add(textField_seo);
		textField_seo.setColumns(10);
		
		String seoNH="";
		if(namHoc!="Tất cả") {
			seoNH=namHoc.replaceAll("20", "").replaceAll("-", "");
			textField_MaHK.setText("HK"+seoNH+(hocKiService.findByNamHoc(namHoc).size()+1));
			comboBox_dsNamHoc.setSelectedItem(namHoc);
			textField_NhapNH.setText(comboBox_dsNamHoc.getSelectedItem().toString());
			textField_seo.setText(seoNH+(hocKiService.findByNamHoc(namHoc).size()+1));
		}else {
			seoNH=hocKiService.getHocKiHienTai().getNamHoc().replaceAll("20", "").replaceAll("-", "");
			textField_MaHK.setText("HK"+seoNH+
					(hocKiService.findByNamHoc(hocKiService.getHocKiHienTai().getNamHoc()).size()+1));
			comboBox_dsNamHoc.setSelectedItem(hocKiService.getHocKiHienTai().getNamHoc());
			textField_NhapNH.setText(comboBox_dsNamHoc.getSelectedItem().toString());
			textField_seo.setText(""+seoNH+(hocKiService.findByNamHoc(hocKiService.getHocKiHienTai().getNamHoc()).size()+1));
		}
		contentPane.add(textField_MaHK);
		textField_MaHK.setColumns(10);
		
		comboBox_dsNamHoc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String nH=comboBox_dsNamHoc.getSelectedItem().toString();
				textField_NhapNH.setText(nH);
				String seoNh=nH.replaceAll("20", "").replaceAll("-", "");
				textField_MaHK.setText("HK"+seoNh+(hocKiService.findByNamHoc(nH).size()+1));
				textField_seo.setText(""+seoNh+(hocKiService.findByNamHoc(nH).size()+1));
			}
		});
		
		JLabel lbl_Seo = new JLabel("Code:");
		lbl_Seo.setBounds(259, 11, 36, 14);
		contentPane.add(lbl_Seo);
		
		JLabel lbl_TenHK = new JLabel("Tên học kì:");
		lbl_TenHK.setBounds(10, 38, 69, 14);
		contentPane.add(lbl_TenHK);
		
		textField_TenHK = new JTextField();
		textField_TenHK.setBounds(78, 36, 181, 20);
		contentPane.add(textField_TenHK);
		textField_TenHK.setColumns(10);
		
		JLabel lblNamHoc = new JLabel("Năm học:");
		lblNamHoc.setBounds(10, 77, 69, 14);
		contentPane.add(lblNamHoc);
		
		JLabel lblNhap = new JLabel("Nhập:");
		lblNhap.setBounds(259, 77, 46, 14);
		contentPane.add(lblNhap);
		
		JLabel lblNgayBatDau = new JLabel("Bắt đầu:");
		lblNgayBatDau.setBounds(10, 117, 69, 14);
		contentPane.add(lblNgayBatDau);
		
		JLabel lblKetthuc = new JLabel("Kết thúc:");
		lblKetthuc.setBounds(199, 117, 59, 14);
		contentPane.add(lblKetthuc);
		
		jdc_NgayBatDau=new JDateChooser();
		jdc_NgayBatDau.setDateFormatString("yyyy-MM-dd");
		jdc_NgayBatDau.setDate(new Date());
		jdc_NgayBatDau.setBounds(78, 111, 103, 20);
		contentPane.add(jdc_NgayBatDau);
		
		jdc_NgayKetThuc=new JDateChooser();
		jdc_NgayKetThuc.setDateFormatString("yyyy-MM-dd");
		jdc_NgayKetThuc.setDate(new Date());
		jdc_NgayKetThuc.setBounds(259, 111, 103, 20);
		contentPane.add(jdc_NgayKetThuc);
		
		JButton btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newHki=new HocKi();
					newHki.setMaHk(textField_MaHK.getText());
					newHki.setTenHk(textField_TenHK.getText());
					newHki.setNamHoc(textField_NhapNH.getText());
					newHki.setNgayBatDau(new SimpleDateFormat("yyyy-MM-dd").format(jdc_NgayBatDau.getDate()));
					newHki.setNgayKetThuc(new SimpleDateFormat("yyyy-MM-dd").format(jdc_NgayKetThuc.getDate()));
					newHki.setSeo(textField_seo.getText());
					int click=JOptionPane.showConfirmDialog(contentPane, "Xác nhận thêm "+
									newHki.getTenHk()+" / "+newHki.getNamHoc()+" ["+newHki.getMaHk()+"]");
					if(click==JOptionPane.YES_OPTION) {
						hocKiService.insertNew(newHki);
						dispose();
					}
					if(click==JOptionPane.NO_OPTION) {
						dispose();
					}
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
					newHki=null;
				}
			}
		});
		btn_Them.setBounds(236, 227, 89, 23);
		contentPane.add(btn_Them);
		
		btn_LamMoi = new JButton("Làm mới");
		btn_LamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_seo.setText("");
				textField_TenHK.setText("");
				textField_TenHK.setText("");
			}
		});
		btn_LamMoi.setBounds(10, 227, 89, 23);
		contentPane.add(btn_LamMoi);
		
		btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTroVe.setBounds(335, 227, 89, 23);
		contentPane.add(btnTroVe);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 142, 414, 72);
		contentPane.add(textArea);
		
		JButton btn_rfMaHK = new JButton(">>");
		btn_rfMaHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nH=textField_NhapNH.getText();
				try {
					new HocKi().setNamHoc(nH);
					String seoNh=nH.replaceAll("20", "").replaceAll("-", "");
					textField_MaHK.setText("HK"+seoNh+(hocKiService.findByNamHoc(nH).size()+1));
					textField_seo.setText(""+seoNh+(hocKiService.findByNamHoc(nH).size()+1));
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					String NH=comboBox_dsNamHoc.getSelectedItem().toString();
					textField_NhapNH.setText(NH);
					String seoNHoc=NH.replaceAll("20", "").replaceAll("-", "");
					textField_seo.setText(""+seoNHoc+(hocKiService.findByNamHoc(NH).size()+1));
				}
			}
		});
		btn_rfMaHK.setBounds(170, 7, 59, 23);
		contentPane.add(btn_rfMaHK);
	}
}
