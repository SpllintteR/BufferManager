package storage;


public class CachedBuffer {
	
	private boolean dirt;
	private byte[] buffer;
	private int page;
	private int pinCount;
	
	public CachedBuffer(byte[] data, int page) {
		buffer = data;
		dirt = false;
		pinCount = 0;
	}

	public byte[] getBuffer() {
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
	public void dirt(byte[] buffer) {
		this.dirt = true;
		this.buffer = buffer;
	}

	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	public int getPinCount() {
		return pinCount;
	}
	
}
