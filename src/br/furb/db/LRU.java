package br.furb.db;

import java.util.List;

public class LRU implements ChangePage{

	@Override
	public MemoryPage changePage(List<MemoryPage> storage) {
		MemoryPage lastRecent = storage.get(0);
		int count = storage.size();
		for (int i = 0; i < count; i++) {
			MemoryPage each = storage.get(i);
			if (!each.mostRecent(lastRecent)) {
				lastRecent = each;
			}
		}
		return lastRecent;
	}

}
