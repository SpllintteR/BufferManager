package br.furb.gui;

import java.util.Date;

import javax.swing.table.AbstractTableModel;

import br.furb.db.MemoryPage;
import br.furb.db.MemoryStorage;

public class UIMemoryTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;

	private String[] columns = new String[] {"Page", "Pin-Count", "Dirty", "Last-Acess", "Data"};
	
	private MemoryStorage data;
	
	public UIMemoryTableModel(MemoryStorage data)
	{
		this.data = data;
	}
	
	@Override
	public int getColumnCount()
	{
		return columns.length;
	}
	
	@Override
	public Class<?> getColumnClass(int column)
	{
		switch (column)
		{
			case 0 : return Integer.class;
			case 1 : return Integer.class;			
			case 2 : return Boolean.class;			
			case 3 : return Date.class;
			
			default : return String.class;
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 4;
	}

	@Override
	public String getColumnName(int column)
	{
		return columns[column];
	}

	@Override
	public int getRowCount()
	{
		return data.size();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 4){
			data.changePage(rowIndex, ((String) aValue).toCharArray());
			fireTableCellUpdated(rowIndex, 2);
		}
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		MemoryPage page = data.getPage(row);
		
		switch (column)
		{
			case 0 : return page.getPage();
			case 1 : return page.getPinCount();
			case 2 : return page.getDirty();
			case 3 : return page.getLastAcess();
			case 4 : return new String(page.getData());
		}
		
		return null;
	}
	
	public void update()
	{
		fireTableDataChanged();	
	}

}
