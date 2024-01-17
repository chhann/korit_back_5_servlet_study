package com.study.servlet_study.repository;

import java.util.ArrayList;
import java.util.List;
import com.study.servlet_study.entity.Account;


public class AccountRepository {
	private static AccountRepository instance;
	private List<Account> accountList;
	
	private AccountRepository() {
		accountList = new ArrayList<>();
	}
	
	public static AccountRepository getInstance() {
		if(instance == null) {
			instance = new AccountRepository();
		}
		return instance;
	}
	
	public int saveAccount (Account account) {
		accountList.add(account);
		return 1;
	}
	
	public Account findAccountByUsername(String username) {
		Account findaccount = null;
		
		for(Account account : accountList) {
			if(account.getUsername().equals(username)) {
				findaccount = account;
				break;
			}
		}
		return findaccount;
		
	}
	
	
	
	
	
	
	
	
}
