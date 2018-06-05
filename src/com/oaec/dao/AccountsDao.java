package com.oaec.dao;

import java.util.List;
import java.util.Map;

import com.oaec.pojo.Accounts;
import com.oaec.util.MyJDBCTemp;

public class AccountsDao {
	MyJDBCTemp jdbc=new MyJDBCTemp();
	/*
	 * ����û�
	 */
	public int addUser(Accounts account) {
		int num=jdbc.update("insert into accounts values(accounts_seq.nextval,?,?,?)", account.getAccount(),account.getPassword(),account.getEmail());
		
		if(num!=1) {
			return 0;
		}else return 1;
	}
	/*
	 * ��֤�û�
	 */
	public int verifyUser(String username) {
		List <Map<String,Object>> list=jdbc.queryForList("select * from accounts where account=?", username);
		for(Map<String,Object> map :list) {
			if(map.get("ACCOUNT").equals(username)) {
				return 1;
			}			
		}			
		return 0;
	}
	public Map<String,Object> validateAccount(String account) {
		return jdbc.queryForMap("select * from accounts where account=?", account);
		
		
	}
	/**
	 * �û���¼,�ж��Ƿ���Ե�¼
	 * @param username
	 * @param password
	 * @return
	 */
	public Map<String,Object> userLogin(String username, String password) {
		return jdbc.queryForMap("select * from accounts where account=? and password=?",username, password);
	}
	/**
	 * �һ�����ķ�����ͨ��username�ҵ���Ӧ�û���Ϣ������
	 * @param username
	 * @return
	 */
	public Map<String, Object> findPWD(String username,String email) {
		// TODO Auto-generated method stub
		return jdbc.queryForMap("select * from accounts where account=? and email=?",username,email);
	}

}
