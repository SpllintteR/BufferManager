package br.furb.db;

import java.util.List;

public interface ChangePage {
	
	/**
	 * Retorna a página que deve ser retirada da mémoria.
	 * 
	 * @param storage
	 * @return
	 */
	MemoryPage changePage(List<MemoryPage> storage);

}
