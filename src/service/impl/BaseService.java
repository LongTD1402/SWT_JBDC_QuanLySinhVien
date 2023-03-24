package service.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.impl.GenericDAO;
import service.IBaseService;

public class BaseService<E>{
	
	public <E> JTable createTable(GenericDAO<E> dao) {
		List<String> list = dao.findAllTableColumns();
		JTable table = new JTable();
		table = new JTable();
		table.setBounds(60, 48, 1, 1);
		DefaultTableModel defaultTable = new DefaultTableModel();
		table.setModel(defaultTable);
		for (String string : list) {
			defaultTable.addColumn(string);
		}
		for (int i = 0; i < dao.findAll().size(); i++) {
			E ee = (E) dao.findAll().get(i);
			defaultTable.addRow(dao.toObjectArr(ee));
			System.out.println(ee.toString());
		}
		return table;
	}

}
