package br.furb.db;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MemoryStorage implements StorageInterface
{
	private List<MemoryPage> pages;
	private ChangePage algorithm;

	public MemoryStorage()
	{
		pages = new LinkedList<MemoryPage>();
		algorithm = new LRU();
	}
	
	public ChangePage getAlgorithm() {
		return algorithm;
	}
	
	public void setAlgorithm(ChangePage algorithm) {
		this.algorithm = algorithm;
	}
	
	@Override
	public char[] loadPage(int page)
	{		
		try
		{
			MemoryPage mp = findPage(page);
			
			if (mp == null)
			{		
				mp = new MemoryPage(page);
				mp.loadData();
				
				if (pages.size() == 10) {
					MemoryPage memoryPage = algorithm.changePage(pages);
					savePage(memoryPage.getPage());
					pages.remove(memoryPage);
				}
				pages.add(mp);
			}
			
			return mp.getData();
		}
		catch (IOException io)
		{
			io.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void savePage(int page)
	{
		MemoryPage mp = findPage(page);
		try
		{
			mp.saveData();
		}
		catch (IOException io)
		{
			io.printStackTrace();
		}
	}

	@Override
	public void changePage(int page, char[] data)
	{
		MemoryPage mp = getPage(page);
		try
		{
			mp.changeData(data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void listPages()
	{
		writeln(" ----------------");
		writeln("{ Memory Storage }");
		writeln(" ----------------");
		
		for (MemoryPage p : pages)
			p.print();
	}
	
	private MemoryPage findPage(int page)
	{
		for (MemoryPage p : pages)
			if (p.getPage() == page)
				return p;
		
		return null;
	}
	
	private void writeln(String text)
	{
		System.out.println(text);
	}
	
	public int size()
	{
		return pages.size();
	}
	
	public MemoryPage getPage(int index)
	{
		return pages.get(index);
	}
	
}
