package jframes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jframes.hocki.HocKiFrame;
import jframes.monhoc.QuanLyMonHocFrame;
import jframes.sinhvien.QuanLySinhVienFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Quản lý");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 950, 22);
		
		JMenu mnHocKi = new JMenu("Học kì");
		mnHocKi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new HocKiFrame().setVisible(true);
			}
		});
		menuBar.add(mnHocKi);
		
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
	}

}
