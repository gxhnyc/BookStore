package com.oaec.service;

import java.util.Map;

import com.oaec.dao.AccountsDao;
import com.oaec.pojo.Accounts;


public class RegisterService {
	
	/*
	 * 添加注册用户
	 */
	public int addUserDao(Accounts user) {
		AccountsDao ud=new AccountsDao();
		
		return ud.addUser(user);
	}
	/*
	 * 验证注册用户是否已经存在
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
