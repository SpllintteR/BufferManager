package br.furb.db;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MemoryPage
{	
	// Nœmero da p‡gina
	private int page;
	
	// Conjunto de bytes contendo os dados da p‡gina
	private char[] data;
	
	// L—gico que informa se os dados da pagina foram alterados 
	private boolean dirty;
	
	// Contador do nœmero de vezes que a pagina foi solicitada 
	private int pinCount;
	
	// Momento (data e hora) da œltima vez que a p‡gina foi solicitada 
	private Date lastAcess;
	
	public MemoryPage(int page)
	{
		this.page = page;
		this.pinCount = 0;
		this.dirty = false;
		this.lastAcess = new Date();
		this.data = new char[DataFile.BLOCK_SIZE];
	}
	
	public void loadData() throws IOException
	{
		data = DataFile.loadData(page);
	}
	
	public void saveData() throws IOException
	{
		if (dirty) {
			DataFile.saveData(page, data);
		}
	}
	
	public void changeData(char[] data) throws Exception
	{
		dirty = true;
		
		if (data.length != this.data.length)
			throw new Exception("Size of byte array out of range");
		
		System.arraycopy(data, 0, this.data, 0, this.data.length);
	}
	
	public char[] getData()
	{
		char[] cdata = new char[data.length];
		
		System.arraycopy(data, 0, cdata, 0, data.length);
		
		//pinCount++;
		
		return cdata;
	}	
	
	public int getPage()
	{
		return page;
	}
	
	public boolean getDirty()
	{
		return dirty;
	}
	
	public int getPinCount()
	{
		return pinCount;
	}
	
	public Date getLastAcess()
	{
		return lastAcess;
	}
	
	public void print()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		writeln("");
		writeln("+ Page      : " + page);
		writeln("+ Pin-Count : " + pinCount);
		writeln("+ Dirty     : " + (dirty ? "yes" : "no"));
		writeln("+ Last-Acess: " + dateFormat.format(lastAcess));
		writeln("+ Data 	 : " + data.toString());
	}
	
	private void writeln(String text)
	{
		System.out.println(text);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + (dirty ? 1231 : 1237);
		result = prime * result
				+ ((lastAcess == null) ? 0 : lastAcess.hashCode());
		result = prime * result + page;
		result = prime * result + pinCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemoryPage other = (MemoryPage) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (dirty != other.dirty)
			return false;
		if (lastAcess == null) {
			if (other.lastAcess != null)
				return false;
		} else if (!lastAcess.equals(other.lastAcess))
			return false;
		if (page != other.page)
			return false;
		if (pinCount != other.pinCount)
			return false;
		return true;
	}

	public boolean mostRecent(MemoryPage mostRecent) {
		return this.getLastAcess().compareTo(mostRecent.getLastAcess()) > 0;
	}
}
