package storage;

public class MRU extends StorageInterface {

	@Override
	public CachedBuffer escolheItemQueVaiRetirar(StorageInterface storage) {
		int count = storage.getSize();
		CachedBuffer mostRecent = storage.getPage(count - 1);
		for (int i = 0; i < count; i++) {
			CachedBuffer each = storage.getPage(i);
			if (each.compareTo(mostRecent) == 1) {
				mostRecent = each;
			}
		}
		return mostRecent;
	}

}