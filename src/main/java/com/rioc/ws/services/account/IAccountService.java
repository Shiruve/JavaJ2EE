package com.rioc.ws.services.account;

import java.util.List;

import com.rioc.ws.model.dao.Account;
import com.rioc.ws.model.dto.AccountAdresseDto;
import com.rioc.ws.model.dto.AccountDto;

public interface IAccountService {

	public AccountDto postAccount(AccountAdresseDto account);
	public void deleteAll();
	public List<AccountDto> getAccounts();
	public List<AccountDto> getAccountsByName(String firstName, String lastName);
	public String deleteAccountByName(String firstName, String lastName);
	public String deleteById(int id);
	public List<AccountDto> getAccountById(int id);
//	public boolean isNotPresent(Account account);
}
