package jframes.hocki.hocphan;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import jframes.hocki.ChiTietHocKiFrame;
import model.HocKi;
import model.HocPhan;
import model.MonHoc;
import service.impl.HocKiService;
import service.impl.HocPhanService;
import service.impl.MonHocService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class HocPhanFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMaHP;
	private JTextField textField_MaHK;
	private JLabel lblTenHP;
	private JLabel lblTenHK;
	private JLabel lblHocKi;
	private JTextField textField_TenHP;
	private JLabel lblNgayBatDau;
	private JLabel lblNgayKetThuc;
	private JButton btnLamMoi;
	private JDateChooser jDateChooser_NBD;
	private JDateChooser jDatechooser_NKT;
	private JComboBox<HocKi> comboBox_HocKi;
	private JButton btnLuu;
	private HocPhan newHp=null;
	private HocKiService hocKiService;
	private HocPhanService hocPhanService;
	private MonHocService monHocService;
	private JTextField textField_maMh;
	private JComboBox<MonHoc> comboBox_dsMonHoc;
	private JLabel lblMaMH;
	private JLabel lbl_tenMH;
	private JLabel lblLabel_TenMh;
	private long lastInd;
	private JLabel lblHinhThucThi;
	private JComboBox<Object> comboBox_HTThi;
	private JRadioButton rdbtnDangHD;
	private JRadioButton rdbtnKhongHD;
	private ButtonGroup btnGroup_TrangThai;
	private JTextField textField_SoHP;
	private JTextField textField_HeSo;
	/**
	 * Create the frame.
	 */
	public HocPhanFrame( long lastIndex,MonHoc mh,HocKi hk) {
		hocKiService= new HocKiService();
		hocPhanService=new HocPhanService();
		monHocService=new MonHocService();
		setTitle("Học phần");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaHP = new JLabel("Mã học phần:");
		lblMaHP.setBounds(10, 11, 78, 14);
		contentPane.add(lblMaHP);
		
		textFieldMaHP = new JTextField();
		textFieldMaHP.setEditable(false);
		textFieldMaHP.setBounds(98, 8, 109, 20);
		String maMhSeo=Long.parseLong(mh.getMaMH().replaceAll("MH", ""))+"";
		textFieldMaHP.setText("HP"+hk.getSeo()+maMhSeo+String.format("%03d",(lastIndex+1)));
		contentPane.add(textFieldMaHP);
		textFieldMaHP.setColumns(10);
		
		lblTenHP = new JLabel("Tên học phần:");
		lblTenHP.setBounds(10, 39, 93, 14);
		contentPane.add(lblTenHP);
		JLabel lblMaHK = new JLabel("Mã học kì:");
		lblMaHK.setBounds(154, 164, 73, 14);
		contentPane.add(lblMaHK);
		
		textField_MaHK = new JTextField();
		textField_MaHK.setEditable(false);
		textField_MaHK.setBounds(250, 160, 86, 20);
		textField_MaHK.setText(hk.getMaHk());
		contentPane.add(textField_MaHK);
		textField_MaHK.setColumns(10);
		
		lblTenHK = new JLabel("Tên học kì:");
		lblTenHK.setBounds(154, 189, 78, 14);
		contentPane.add(lblTenHK);
		
		lblHocKi = new JLabel("");
		lblHocKi.setBounds(250, 188, 264, 14);
		lblHocKi.setText(hk.toString());
		contentPane.add(lblHocKi);
		comboBox_HocKi = new JComboBox<HocKi>();
		for (HocKi hki : hocKiService.findAll()) {
			comboBox_HocKi.addItem(hki);
		}
		comboBox_HocKi.setSelectedItem(hk);
		comboBox_HocKi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				HocKi hocKi= (HocKi) comboBox_HocKi.getSelectedItem();
				textField_MaHK.setText(hocKi.getMaHk());
				lblHocKi.setText(hocKi.toString());
				List<HocPhan> list_byHk = hocPhanService.findByMaHK(textField_MaHK.getText());
				List<HocPhan> list=new ArrayList<>();
				if(list_byHk.isEmpty()==false) {
					for (HocPhan hocPhan : list_byHk) {
						if(hocPhan.getMaMH().equals(textField_maMh.getText())) {
							list.add(hocPhan);
						}
					}
				}
				lastInd=hocPhanService.lastIndex(list, monHocService.findByMa(textField_maMh.getText()), 
						hocKiService.findByMa(textField_MaHK.getText()));
				textFieldMaHP.setText("HP"+textField_MaHK.getText().replaceAll("HK", "")+
						Long.parseLong(textField_maMh.getText().replaceAll("MH", ""))+String.format("%03d",(lastInd+1)));
			}
		});
		comboBox_HocKi.setBounds(10, 159, 134, 22);
		
		textField_TenHP = new JTextField();
		textField_TenHP.setBounds(96, 36, 418, 20);
		contentPane.add(textField_TenHP);
		textField_TenHP.setColumns(10);
		
		contentPane.add(comboBox_HocKi);
		
		lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setBounds(10, 266, 78, 14);
		contentPane.add(lblNgayBatDau);
		
		lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setBounds(267, 266, 97, 14);
		contentPane.add(lblNgayKetThuc);
		
		jDateChooser_NBD = new JDateChooser();
		jDateChooser_NBD.setDateFormatString("yyyy-MM-dd");
		Date dateBD =new Date();
		jDateChooser_NBD.setDate(dateBD);
		jDateChooser_NBD.setBounds(90, 261, 140, 20);
		contentPane.add(jDateChooser_NBD);
		
		jDatechooser_NKT = new JDateChooser();
		jDatechooser_NKT.setDateFormatString("yyyy-MM-dd");
		Date dateKT =new Date();
		jDatechooser_NKT.setDate(dateKT);
		jDatechooser_NKT.setBounds(365, 261, 140, 20);
		contentPane.add(jDatechooser_NKT);
		
		JButton btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTroVe.setBounds(425, 407, 89, 23);
		contentPane.add(btnTroVe);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_TenHP.setText("");
				comboBox_dsMonHoc.setSelectedIndex(0);
				comboBox_HocKi.setSelectedIndex(0);
				jDateChooser_NBD.setDate(new Date());
				jDatechooser_NKT.setDate(new Date());
			}
		});
		btnLamMoi.setBounds(10, 407, 89, 23);
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
					newHp.setMaHK(textField_MaHK.getText());
					newHp.setNgayBatDau(sDf.format(jDateChooser_NBD.getDate()));
					newHp.setNgayKetThuc(sDf.format(jDatechooser_NKT.getDate()));
					newHp.setHinhThucThi(comboBox_HTThi.getSelectedItem().toString());
					newHp.setHeSo(Double.parseDouble(textField_HeSo.getText()));
					newHp.setSoHP(Integer.parseInt(textField_SoHP.getText()));
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
					int click=JOptionPane.showConfirmDialog(contentPane, "Xác nhận thêm ["+newHp.getMaHP()+"]:"+newHp.getTenHP());
					if(click==JOptionPane.YES_OPTION){
						try {
							hocPhanService.insertNew(newHp);
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
		btnLuu.setBounds(326, 407, 89, 23);
		contentPane.add(btnLuu);
		
		comboBox_dsMonHoc = new JComboBox<MonHoc>();
		comboBox_dsMonHoc.setBounds(10, 75, 134, 22);
		for (MonHoc monHoc : monHocService.findAll()) {
			comboBox_dsMonHoc.addItem(monHoc);
		}
		comboBox_dsMonHoc.setSelectedIndex(0);
		comboBox_dsMonHoc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				MonHoc monHoc= (MonHoc) comboBox_dsMonHoc.getSelectedItem();
				textField_maMh.setText(monHoc.getMaMH());
				lblLabel_TenMh.setText(monHoc.getTenMH());
				List<HocPhan> list_byHk = hocPhanService.findByMaHK(textField_MaHK.getText());
				List<HocPhan> list=new ArrayList<>();
				if(list_byHk.isEmpty()==false) {
					for (HocPhan hocPhan : list_byHk) {
						if(hocPhan.getMaMH().equals(textField_maMh.getText())) {
							list.add(hocPhan);
						}
					}
				}
				lastInd=hocPhanService.lastIndex(list, monHocService.findByMa(textField_maMh.getText()), 
						hocKiService.findByMa(textField_MaHK.getText()));
				textFieldMaHP.setText("HP"+textField_MaHK.getText().replaceAll("HK", "")+
						Long.parseLong(textField_maMh.getText().replaceAll("MH", ""))+String.format("%03d",(lastInd+1)));
			}
		});
		contentPane.add(comboBox_dsMonHoc);
		
		lblMaMH = new JLabel("Mã môn học:");
		lblMaMH.setBounds(154, 80, 73, 14);
		contentPane.add(lblMaMH);
		
		lbl_tenMH = new JLabel("Tên môn học:");
		lbl_tenMH.setBounds(154, 105, 78, 14);
		contentPane.add(lbl_tenMH);
		
		lblLabel_TenMh = new JLabel("");
		lblLabel_TenMh.setBounds(250, 104, 264, 14);
		lblLabel_TenMh.setText(monHocService.findAll().get(0).getTenMH());
		contentPane.add(lblLabel_TenMh);
		
		textField_maMh = new JTextField();
		textField_maMh.setEditable(false);
		textField_maMh.setBounds(250, 76, 86, 20);
		textField_maMh.setText(monHocService.findAll().get(0).getMaMH());
		contentPane.add(textField_maMh);
		textField_maMh.setColumns(10);
		
		JTextArea textArea_ttHP = new JTextArea();
		textArea_ttHP.setBounds(10, 321, 504, 76);
		contentPane.add(textArea_ttHP);
		
		lblHinhThucThi = new JLabel("Hình thức thi:");
		lblHinhThucThi.setBounds(154, 214, 86, 14);
		contentPane.add(lblHinhThucThi);
		
		comboBox_HTThi = new JComboBox<Object>();
		comboBox_HTThi.setModel(new DefaultComboBoxModel<Object>(new String[] {"Tự luận", "Thực hành", "Tiểu luận"}));
		comboBox_HTThi.setBounds(250, 209, 115, 22);
		contentPane.add(comboBox_HTThi);
		
		rdbtnDangHD = new JRadioButton("Đang hoạt động");
		rdbtnDangHD.setBounds(215, 7, 134, 23);
		contentPane.add(rdbtnDangHD);
		
		rdbtnKhongHD = new JRadioButton("Không hoạt động");
		rdbtnKhongHD.setBounds(365, 7, 149, 23);
		contentPane.add(rdbtnKhongHD);
		
		btnGroup_TrangThai= new ButtonGroup();
		btnGroup_TrangThai.add(rdbtnDangHD);
		btnGroup_TrangThai.add(rdbtnKhongHD);
		rdbtnDangHD.setSelected(true);
		
		JLabel lblSoHP = new JLabel("Số học phần:");
		lblSoHP.setBounds(10, 291, 78, 14);
		contentPane.add(lblSoHP);
		
		textField_SoHP = new JTextField();
		textField_SoHP.setBounds(90, 290, 86, 20);
		contentPane.add(textField_SoHP);
		textField_SoHP.setColumns(10);
		
		JLabel lblHeSo = new JLabel("Hệ số:");
		lblHeSo.setBounds(267, 291, 46, 14);
		contentPane.add(lblHeSo);
		
		textField_HeSo = new JTextField();
		textField_HeSo.setBounds(365, 290, 86, 20);
		contentPane.add(textField_HeSo);
		textField_HeSo.setColumns(10);
		
	}
}
