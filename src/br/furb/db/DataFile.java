package br.furb.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DataFile {
	public static final int BLOCK_SIZE = 128;
	private static DataFile dataFile;
	private RandomAccessFile access;
	File file = new File("Dados.txt");

	private DataFile() {
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter dataWriter = new FileWriter(file.getAbsolutePath());
			try {
				for (int i = 0; i < 20; i++) {
					String teste = "qwertyuiopasdfghjklzxcvbnmqwerty";
					teste += teste + teste + teste;
					dataWriter.write(teste);
				}
			} finally {
				dataWriter.close();
			}
			
			access = new RandomAccessFile(file.getAbsolutePath(), "rws");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		access.close();
		access.close();
		file.delete();
	}

	public char[] loadData(int block) throws IOException {
		char[] data = new char[128];

		int offset = block * BLOCK_SIZE;

		access.seek(offset);
		
		byte[] bytes = new byte[128];
		access.read(bytes, 0, BLOCK_SIZE);

		for (int i = 0; i < 128; i++) {
			data[i] = (char) bytes[i];
		}
		
		return data;
	}

	public void saveData(int block, char[] data) throws IOException {
		int offset = block * BLOCK_SIZE;

		access.seek(offset);

		byte[] bytes = new byte[128];
		for (int i = 0; i < 128; i++) {
			bytes[i] = (byte) data[i];
		}
		access.write(bytes);
	}

	public static DataFile getInstance() {
		if (dataFile == null){
			dataFile = new DataFile();
		}
		return dataFile;
	}

}
