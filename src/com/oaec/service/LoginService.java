package com.oaec.service;

import java.util.Map;

import com.oaec.dao.AccountsDao;

public class LoginService {
	
	/*
	 * 验证注册用户是否存在
	 */
	public Map<String,Object> userLogin(String username,String password) {
		AccountsDao ud=new AccountsDao();
		return ud.userLogin(username,password);
	}
	public Map<String,Object> validateAccount(String account) {
		AccountsDao ud=new AccountsDao();
		return ud.validateAccount(account);
		
	}
	/**
	 * 找回密码
	 * @param username
	 * @param email
	 * @return
	 */
	public Map<String, Object> findPWD(String username,String email) {
		AccountsDao ud=new AccountsDao();
		return ud.findPWD(username,email);
	}
}
