package br.furb.db;

import java.util.List;

public class MRU implements ChangePage {

	@Override
	public MemoryPage changePage(List<MemoryPage> storage) {
		int count = storage.size();
		MemoryPage mostRecent = storage.get(count - 1);
		for (int i = 0; i < count; i++) {
			MemoryPage each = storage.get(i);
			if (each.mostRecent(mostRecent)) {
				mostRecent = each;
			}
		}
		return mostRecent;
	}

}
