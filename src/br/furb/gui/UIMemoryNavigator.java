package br.furb.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.furb.db.MemoryStorage;

public class UIMemoryNavigator extends JScrollPane
{
	private static final long serialVersionUID = 1L;

	private JTable tabela;
	private UIMemoryTableModel modelo; 
	
	public UIMemoryNavigator(MemoryStorage data)
	{	
		modelo = new UIMemoryTableModel(data);
		
		tabela = new JTable(modelo);
		tabela.setRowSelectionAllowed(true);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		setViewportView(tabela);
	}
	
	public void update()
	{
		modelo.update();
	}

}
