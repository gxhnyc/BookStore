package com.oaec.service;

import java.util.List;

import com.oaec.dao.BookDao;
import com.oaec.pojo.Books;

public class NextService {

	public List<Books> loadBooks(int pagenum) {
		//System.out.println("NextService---loadBooks()");
		List<Books> list = new BookDao().findBooksByPage(pagenum);
		return list;
	}

}
