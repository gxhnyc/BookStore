package com.oaec.service;

import java.util.Map;

import com.oaec.dao.AccountsDao;
import com.oaec.pojo.Accounts;


public class RegisterService {
	
	/*
	 * ���ע���û�
	 */
	public int addUserDao(Accounts user) {
		AccountsDao ud=new AccountsDao();
		
		return ud.addUser(user);
	}
	/*
	 * ��֤ע���û��Ƿ��Ѿ�����
	 */
	public int verify(String username) {
		AccountsDao ud=new AccountsDao();
		return ud.verifyUser(username);
	}
	public Map<String,Object> validateAccount(String account) {
		AccountsDao ud=new AccountsDao();
		return ud.validateAccount(account);
		
	}
}
