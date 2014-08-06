package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		StorageInterface storage = new StorageInterface();
		if (!file.exists()) throw new FileNotFoundException("Arquivo n�o encontrado, insira um arquivo v�lido");
		Scanner scanner = new Scanner(file);
		try {
			int i = 0;
			while (scanner.hasNext()){
				String next = scanner.next();
				if (next.getBytes().length > 128){
					throw new RuntimeException("Tentando ler bloco com mais de 128 bytes");
				}
				char[] chars = next.toCharArray();
				storage.savepage(i, chars);
				i++;
			}
		} finally {
			scanner.close();
		}		
	}
}
