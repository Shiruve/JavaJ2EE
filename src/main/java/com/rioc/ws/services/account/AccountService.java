package com.rioc.ws.services.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mapper.IAccountMapper;
import com.rioc.ws.model.dao.Account;
import com.rioc.ws.model.dto.AccountAdresseDto;
import com.rioc.ws.model.dto.AccountDto;
import com.rioc.ws.repositories.IAccountRepository;
import com.rioc.ws.repositories.IBankRepository;
import com.rioc.ws.services.adresse.IAdresseService;
import com.rioc.ws.services.bank.IBankService;

@Service
public class AccountService implements IAccountService {
	
	private IAccountRepository repository;
	private IBankService bankService;
	private IAccountMapper mapper;
	private IAdresseService adresseService;
	


	public AccountService(IAccountRepository repository, IBankService bankService, IAccountMapper mapper,
			IAdresseService adresseService) {
		super();
		this.repository = repository;
		this.bankService = bankService;
		this.mapper = mapper;
		this.adresseService = adresseService;
	}

	public AccountDto postAccount(AccountAdresseDto accountAdresse) {
		AccountDto account = new AccountDto();
		if(repository.findByFirstNameAndLastName(accountAdresse.getFirstName(), accountAdresse.getLastName()).isEmpty()) {
			account.setFirstName(accountAdresse.getFirstName());
			account.setLastName(accountAdresse.getLastName());
			account.setAdresse(adresseService.getAdresse(accountAdresse.getAdresse()+" "+accountAdresse.getPostalCode()+" "+accountAdresse.getCity()));
			account = mapper.accountToAccountDto(repository.save(mapper.accountDtoToAccount(account)));
			return account;
		} else {
			return null;
		}
	}
	
	public void deleteAll() {
		if(!repository.findAll().isEmpty()){
			for(Account account : repository.findAll()) {
				repository.delete(account);
				bankService.deleteAll();
			}
		}
	}
	
	public String deleteById(int id) {
		Optional<Account> account = repository.findById(id);
		if(!account.isEmpty()) {
			bankService.deleteByAccountId(id);
			repository.deleteById(id);
			return "Account ID : " + id + " deleted.";
		}
		else {
			throw new ApiException("Account does not exists", HttpStatus.NOT_FOUND);
		}
	}
	
	public List<AccountDto> getAccountById(int id){
		Optional<Account> accountById= repository.findById(id);
		List<AccountDto> listAccountDto = new ArrayList<>();
		if(!accountById.isEmpty()) {
			listAccountDto.add(mapper.accountToAccountDto(accountById.get()));
		}
		return listAccountDto;	
	}
	
	public List<AccountDto> getAccounts() {
		List<AccountDto> listAccount = new ArrayList<>();
		for(Account account : repository.findAll()) {
			listAccount.add(mapper.accountToAccountDto(account));
		}
		return listAccount;
	}
	
	public List<AccountDto> getAccountsByName(String firstName, String lastName){
		List<AccountDto> listAccountDto = new ArrayList<>();
		List<Account> listAccount = repository.findByFirstNameAndLastName(firstName, lastName);
		for(Account account : listAccount) {
			listAccountDto.add(mapper.accountToAccountDto(account));
		}
		return listAccountDto;	
	}
	
	public String deleteAccountByName(String firstName, String lastName){
		List<AccountDto> listAccountDto = new ArrayList<>();
		List<Account> listAccount = repository.findByFirstNameAndLastName(firstName, lastName);
		if(listAccount.isEmpty()) {
			throw new ApiException("Account does not exists", HttpStatus.NOT_FOUND);
		}
		else {
			for(Account account : listAccount) {
				bankService.deleteByAccountId(account.getAccountId());
				repository.delete(account);
			}
			return "Account : " + firstName + " " + lastName + " deleted.";
		}
	}
	
}
