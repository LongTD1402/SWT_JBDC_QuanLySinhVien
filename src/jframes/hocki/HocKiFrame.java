package jframes.hocki;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.HocKi;
import service.impl.HocKiService;
//import service.impl.HocPhanService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class HocKiFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_HocKi;
	private DefaultTableModel defaultTable_HocKi;
	private DefaultTableModel defaultTable_TTHocKi;
	private JTable tableTTHocKi;
	private JTable tableHocKi;
	//private HocPhanService hocPhanService;
	private HocKiService hocKiService;
	private List<HocKi> findAllHk;
	private HocKi hkHienTai;
	private JComboBox<String> comboBox_dsNamHoc;

	/**
	 * Create the frame.
	 */
	public HocKiFrame() {
		hocKiService = new HocKiService();
		//hocPhanService = new HocPhanService();
		findAllHk = hocKiService.findAll();
		hkHienTai = hocKiService.getHocKiHienTai();
		setTitle("Học kỳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNamHoc = new JLabel("Năm học:");
		lblNamHoc.setBounds(10, 107, 62, 14);
		contentPane.add(lblNamHoc);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(359, 103, 89, 23);
		contentPane.add(btnTimKiem);

		JLabel lblHocKi = new JLabel("Học kì:");
		lblHocKi.setBounds(203, 107, 53, 14);
		contentPane.add(lblHocKi);

		textField_HocKi = new JTextField();
		textField_HocKi.setBounds(253, 104, 86, 20);
		contentPane.add(textField_HocKi);
		textField_HocKi.setColumns(10);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable_HocKi.setRowCount(0);
				defaultTable_TTHocKi.setRowCount(0);
				textField_HocKi.setText("");
				findAllHk = hocKiService.findAll();
				comboBox_dsNamHoc.setSelectedIndex(0);
				setTableHKData(findAllHk, 0);
				setTableTTHKData(null);
			}
		});
		btnLamMoi.setBounds(599, 103, 89, 23);
		contentPane.add(btnLamMoi);

		JButton btnThemHocKi = new JButton("+Thêm học kì");
		btnThemHocKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemHocKiFrame(comboBox_dsNamHoc.getSelectedItem().toString()).setVisible(true);
			}
		});
		btnThemHocKi.setBounds(800, 103, 134, 23);
		contentPane.add(btnThemHocKi);

		JLabel lblNewLabel = new JLabel("Học kì hiện tại:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 5, 110, 28);
		contentPane.add(lblNewLabel);

		JLabel lblNamHocHienTai = new JLabel(hkHienTai.getTenHk() + " / Năm học " + hkHienTai.getNamHoc());
		lblNamHocHienTai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("xem");
			}
		});
		lblNamHocHienTai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNamHocHienTai.setBounds(108, 5, 268, 28);
		contentPane.add(lblNamHocHienTai);

		JScrollPane scrollPaneHocKi = new JScrollPane();
		scrollPaneHocKi.setBounds(20, 132, 924, 314);
		contentPane.add(scrollPaneHocKi);

		tableHocKi = new JTable();
		scrollPaneHocKi.setColumnHeaderView(tableHocKi);
		defaultTable_HocKi = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã HK", "Tên HK", "Số học phần", "Năm học", "Bắt đầu", "Kết thúc" });
		setTableHKData(findAllHk, 1);
		tableHocKi.setModel(defaultTable_HocKi);
		scrollPaneHocKi.setViewportView(tableHocKi);
		JButton btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnTroVe.setBounds(845, 467, 89, 23);
		contentPane.add(btnTroVe);

		JScrollPane scrollPane_TTHocKi = new JScrollPane();
		scrollPane_TTHocKi.setBounds(10, 44, 438, 48);
		contentPane.add(scrollPane_TTHocKi);

		tableTTHocKi = new JTable();
		scrollPane_TTHocKi.setColumnHeaderView(tableTTHocKi);
		defaultTable_TTHocKi = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã HK", "Số học phần", "Bắt đầu", "Kết thúc" });
		setTableTTHKData(null);
		tableTTHocKi.setModel(defaultTable_TTHocKi);
		scrollPane_TTHocKi.setViewportView(tableTTHocKi);

		JButton btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableHocKi.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "Không có học kì nào được chọn!");
				} else {
					String ma = String.valueOf(tableHocKi.getValueAt(row, 1));
					HocKi hk = hocKiService.findByMa(ma);
					new ChiTietHocKiFrame(hk).setVisible(true);
				}
			}
		});
		btnChiTiet.setBounds(698, 103, 89, 23);
		contentPane.add(btnChiTiet);

		JButton btnLichThi = new JButton("Lịch thi");
		btnLichThi.setBounds(698, 10, 89, 23);
		contentPane.add(btnLichThi);

		JButton btnNewButton = new JButton("+Thêm lịch thi");
		btnNewButton.setBounds(800, 10, 134, 23);
		contentPane.add(btnNewButton);

		comboBox_dsNamHoc = new JComboBox<String>();
		comboBox_dsNamHoc.setBounds(68, 103, 125, 22);
		List<HocKi> listNHFilter=new ArrayList<HocKi>();
		listNHFilter.add(hkHienTai);
		for (HocKi hocKi : findAllHk) {
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
		comboBox_dsNamHoc.addItem("Tất cả");
		comboBox_dsNamHoc.setSelectedItem("Tất cả");
		for (HocKi hocKi : listNHFilter) {
			comboBox_dsNamHoc.addItem(hocKi.getNamHoc());
		}
		comboBox_dsNamHoc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				defaultTable_HocKi.setRowCount(0);
				List<HocKi> list=null;
				if(comboBox_dsNamHoc.getSelectedItem().toString()=="Tất cả") {
					list=hocKiService.findAll();
				}else
					list=hocKiService.findByNamHoc(comboBox_dsNamHoc.getSelectedItem().toString());
				setTableHKData(list, 1);
				System.out.println(comboBox_dsNamHoc.getSelectedItem().toString());
			}
		});
		contentPane.add(comboBox_dsNamHoc);
	}

	private void setTableTTHKData(HocKi hk) {
		if (hk != null) {
			defaultTable_TTHocKi.addRow(new Object[] { hk.getMaHk(), "--", "--", "--" });
		} else {
			defaultTable_TTHocKi.addRow(new Object[] {});
		}
	}

	private void setTableHKData(List<HocKi> listHk, int page) {
		int count = 1;
		if (listHk.isEmpty() == false) {
			for (int i = 0; i < listHk.size(); i++) {
				defaultTable_HocKi.addRow(new Object[] { count, listHk.get(i).getMaHk(), listHk.get(i).getTenHk(),
						listHk.get(i).getListHp().size(), listHk.get(i).getNamHoc(), listHk.get(i).getNgayBatDau(),
						listHk.get(i).getNgayKetThuc() });
				count++;
			}
		}
	}
}
