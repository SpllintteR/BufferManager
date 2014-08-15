package br.furb.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UIDataBaseManager extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public UIDataBaseManager()
	{
		super("DataBase Manager");
		
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTabbedPane tabs = new JTabbedPane();
		
		tabs.add("Memory", new UIMemoryManager());
		
		JPanel pnConteudo = new JPanel(new BorderLayout());
		
		pnConteudo.add(tabs, BorderLayout.CENTER);
		
		getContentPane().add(pnConteudo);
	}
}
