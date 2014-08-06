package storage;


public class CachedBuffer {
	
	private boolean dirt;
	private char[] buffer;
	private int page;
	private int pinCount;
	
	public CachedBuffer(char[] data, int page) {
		buffer = data;
		dirt = false;
		pinCount = 0;
	}

	public char[] getBuffer() {
		return buffer;
	}

	/**
	 * @return the dirt
	 */
	public boolean isDirt() {
		return dirt;
	}

	/**
	 * @param dirt the dirt to set
	 */
	public void dirt(char[] buffer) {
		this.dirt = true;
		this.buffer = buffer;
	}

	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	
	public void addPinCount(){
		pinCount++;
	}
	
	public int getPinCount(){
		return pinCount;
	}
	
}
