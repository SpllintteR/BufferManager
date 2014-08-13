package storage;

public class LRU extends StorageInterface {

	@Override
	CachedBuffer escolheItemQueVaiRetirar(StorageInterface storage) {
		CachedBuffer lastRecent = storage.getPage(0);
		int count = storage.getSize();
		for (int i = 0; i < count; i++) {
			CachedBuffer each = storage.getPage(i);
			if (each.compareTo(lastRecent) == -1) {
				lastRecent = each;
			}
		}
		return lastRecent;
	}

}