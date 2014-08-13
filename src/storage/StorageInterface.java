package storage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public abstract class StorageInterface {
	
	public List<CachedBuffer> cache = new ArrayList<>();
	RandomAccessFile file;
	private int size;
	
	public StorageInterface() {
		try{
			size = 10;
			file = new RandomAccessFile(new File("input.txt"), "rws");
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	
	public byte[] loadpage(int page) throws IOException{
		CachedBuffer cached = procuraNoCache(page);
		if (cached != null){
			return cached.getBuffer();
		}
		byte[] newCached = findInFile(page);
		addInCache(newCached, page);
		return newCached;
	}
	
	abstract CachedBuffer escolheItemQueVaiRetirar(StorageInterface storage);

	private void addInCache(byte[] data, int page) throws IOException {
		if(cache.size() == 10){
			int pageRemover = escolheItemQueVaiRetirar(this).getPage();
			savepage(pageRemover, new byte[]{}); //TODO: verificar o new byte
			cache.remove(pageRemover);
		}
		cache.add(new CachedBuffer(data, page));
	}

	private byte[] findInFile(int page) throws IOException {
		byte[] result = new byte[128];
		file.read(result, page*128, 128);
		return result;
	}

	public void savepage(int page, byte[] data) throws IOException{
		CachedBuffer findCached = procuraNoCache(page);
		if (findCached.getPinCount() > 0){
			throw new RuntimeException("deu zica");
		}
		if (findCached.isDirt()){
			saveToFile(page, findCached.getBuffer());
		}
	}
	
	private void saveToFile(int page, byte[] buffer) throws IOException {
		file.write(buffer, page*128, 128);
	}

	public void changepage(int page, byte[] data ){
		CachedBuffer findCached = procuraNoCache(page);
		if (findCached == null){
			//TODO: - Verificar se nesse caso vai carregar do disco ou o que vai fazer
		}
		if(findCached.getPinCount() > 0){
			throw new RuntimeException("deu zica");
		}
		findCached.dirt(data);
	}
	
	public void listaPages(){
		for(CachedBuffer cb: cache){
			System.out.println(cb.getBuffer());
		}
	}
	
	private CachedBuffer procuraNoCache(int page) {
		for(CachedBuffer cb: cache){
			if(cb.getPage() == page){
				return cb;
			}
		}
		return null;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public CachedBuffer getPage(int indice){
		return cache.get(indice);
	}
	
}