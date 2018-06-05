package com.oaec.service;

import java.util.Map;

import com.oaec.dao.BookDao;

/**
 * 
 * @author Yechao
 *
 */
public class BookDetailService {
	
	public Map<String ,Object> findBookByID(String id){
		System.out.println("--bookdetialservice-----");
		Map <String,Object> map=new BookDao().findBookByID(id);
		
		
		return map;		
	}
}
