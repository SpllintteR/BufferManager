package br.furb.db;

public interface StorageInterface
{
	char[] loadPage(int page);
	void savePage(int page);
	void changePage(int page, char[] data);
	void listPages();
}
