package storage;

public class CachedBuffer implements Comparable<CachedBuffer> {
	
	private boolean dirt;
	private byte[] buffer;
	private int page;
	private int pinCount;
	private long timestamp;
	
	public CachedBuffer(byte[] data, int page) {
		buffer = data;
		dirt = false;
		pinCount = 0;
		timestamp = System.currentTimeMillis();
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
	
	@Override
	public int compareTo(CachedBuffer arg0) {
		if (arg0 == null) {
			throw new IllegalArgumentException("Object being compared cannot be null.");
		}
		return (int) (this.timestamp - arg0.timestamp);
	}
	
}