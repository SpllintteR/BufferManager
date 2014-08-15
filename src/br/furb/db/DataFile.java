package br.furb.db;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DataFile
{
	public static final String FILE_NAME = "Arquivos//Dados.txt";
	public static final int BLOCK_SIZE = 128;
	
	private DataFile() { }
	
	public static char[] loadData(int block) throws IOException
	{
		FileReader reader = new FileReader(FILE_NAME);
		try
		{
			char[] data = new char[128];
			
			int offset = block * BLOCK_SIZE;
			
			reader.skip(offset);
			reader.read(data, 0, BLOCK_SIZE);
			
			return data;
		}
		finally
		{
			reader.close();
		}		
	}
	
	public static void saveData(int block, char[] data) throws IOException
	{
		RandomAccessFile writer = new RandomAccessFile(FILE_NAME, "rws");
		try
		{
			int offset = block * BLOCK_SIZE;
			
			writer.skipBytes(offset);
			
			byte[] bytes = new byte[128];
			for(int i = 0; i < 128; i++){
				bytes[i] = (byte) data[i];
			}
			writer.write(bytes);
		}
		finally
		{
			writer.close();
		}
	}
	
	
}
