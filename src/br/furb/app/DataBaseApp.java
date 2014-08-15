package br.furb.app;

import javax.swing.SwingUtilities;

import br.furb.gui.UIDataBaseManager;

public class DataBaseApp
{	
	public static void main(String[] args) 
	{	
		SwingUtilities.invokeLater
		(
			new Runnable() 
			{
				@Override
				public void run() 
				{		
					new UIDataBaseManager().setVisible(true);
				}
			}
		);
	}
}
