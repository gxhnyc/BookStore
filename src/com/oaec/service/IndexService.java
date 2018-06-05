package com.oaec.service;

import java.util.List;

import com.oaec.dao.BookDao;
import com.oaec.pojo.Books;
/**
 * 
 * @author Yechao
 *
 */
public class IndexService {

	public List<Books> loadBooks(int pagenum) {
		System.out.println("IndexService---loadBokks()");		
		List<Books> list=new BookDao().findBooks(pagenum);		
		return list;		
	}	
}
