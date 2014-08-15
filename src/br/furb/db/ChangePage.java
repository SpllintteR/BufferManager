package br.furb.db;

import java.util.List;

public interface ChangePage {
	
	/**
	 * Retorna a p�gina que deve ser retirada da m�moria.
	 * 
	 * @param storage
	 * @return
	 */
	MemoryPage changePage(List<MemoryPage> storage);

}
