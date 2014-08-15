package br.furb.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.furb.db.LRU;
import br.furb.db.MRU;
import br.furb.db.MemoryStorage;

public class UIMemoryManager extends JPanel
{	
	private static final long serialVersionUID = 1L;

	private MemoryStorage data;
	
	private UIMemoryNavigator dataNavigator;
	
	private JPanel comandos;
	private JButton btnLerPagina;
	private ButtonGroup algoritmos;
	private JRadioButton lru;
	private JRadioButton mru;
	
	public UIMemoryManager()
	{
		this.setLayout(new BorderLayout());
		
		this.data = new MemoryStorage();
		
		dataNavigator = new UIMemoryNavigator(this.data);
		
		btnLerPagina = new JButton("Ler página");
		
		lru = new JRadioButton("LRU");
		lru.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				data.setAlgorithm(new LRU());
			}
		});
		mru = new JRadioButton("MRU");
		mru.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				data.setAlgorithm(new MRU());
			}
		});
		
		algoritmos = new ButtonGroup();
		algoritmos.add(lru);
		algoritmos.add(mru);
		algoritmos.setSelected(lru.getModel(), true);
		
		comandos = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
		comandos.add(btnLerPagina);
		comandos.add(lru);
		comandos.add(mru);
		
		add(comandos, BorderLayout.NORTH);
		add(dataNavigator, BorderLayout.CENTER);
		
		btnLerPagina.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Integer pagina = -1;
				String sPagina = JOptionPane.showInputDialog("Informe um número de página:");
				try
				{
					pagina = Integer.parseInt(sPagina);
				}
				catch (NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Numero de página inválido");
				}
				
				if (pagina > -1 && pagina < 20)
				{
					data.loadPage(pagina);
					dataNavigator.update();
				} else {
					JOptionPane.showMessageDialog(null, "Numero de página inválido");
				}
			}
		});
	}
}
