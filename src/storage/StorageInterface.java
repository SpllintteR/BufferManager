package storage;

public class StorageInterface {
	
	public int pinCount;
	public int ultimoAcesso = 0;
	public CachedBuffer[] cache = new CachedBuffer[10];
	
	public char[] loadpage(int page){
		CachedBuffer cached = findCached(page);
		if (cached != null){
			cached.addPinCount();
			return cached.getBuffer();
		}
		return null;
	}

	public void savepage(int page, char[] data){
		if(ultimoAcesso == 10){
			if (cache[ultimoAcesso].isDirt()){
				//TODO: deveria salvar esse cached
			}
			//TODO: retira esse cara do cache e cria o novo de acordo com os 2 algoritmos(LRU e MRU)
		}
		cache[ultimoAcesso] = new CachedBuffer(data, page);
		ultimoAcesso++;
	}
	
	public void changepage(int page, char[] data ){
		findCached(page).dirt(data);
	}
	
	public void listaPages(){
		for(CachedBuffer cb: cache){
			System.out.println(cb.getBuffer());
		}
	}
	
	private CachedBuffer findCached(int page) {
		for(CachedBuffer cb: cache){
			if(cb.getPage() == page){
				return cb;
			}
		}
		return null;
	}
	
}