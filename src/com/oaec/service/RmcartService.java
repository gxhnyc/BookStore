package com.oaec.service;

import com.oaec.dao.CartDao;

/**
 * ɾ��item��service
 * @author Administrator
 *
 */
public class RmcartService {

	public void rmcartitem(int account_id, int book_id) {
		CartDao cd=new CartDao();
		cd.rmcartitem(account_id,book_id);
				
		
	}
	
}
