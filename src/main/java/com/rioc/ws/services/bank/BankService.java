package com.rioc.ws.services.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mapper.IAccountMapper;
import com.rioc.ws.mapper.IBankMapper;
import com.rioc.ws.model.dao.Account;
import com.rioc.ws.model.dao.Bank;
import com.rioc.ws.model.dto.AccountDto;
import com.rioc.ws.model.dto.BankAccountDto;
import com.rioc.ws.model.dto.BankDto;
import com.rioc.ws.repositories.IAccountRepository;
import com.rioc.ws.repositories.IBankRepository;
import com.rioc.ws.services.account.IAccountService;


@Service
public class BankService implements IBankService {
	
	private IBankRepository bankRepository;
	private IAccountRepository accountRepository;
	private IAccountMapper accountMapper;
	private IBankMapper mapper;

	public BankService(IBankRepository bankRepository, IAccountRepository accountRepository,
			IAccountMapper accountMapper, IBankMapper mapper) {
		super();
		this.bankRepository = bankRepository;
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
		this.mapper = mapper;
	}

	public BankDto postBank(BankAccountDto bankAccount) {
		int accountId;
		BankDto bank = new BankDto();
		String regex = "^FR[0-9]{2}[a-zA-Z0-9]{23}$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(bankAccount.getIban().replace(" ", ""));
		if(!accountRepository.findByFirstNameAndLastName(bankAccount.getFirstName(), bankAccount.getLastName()).isEmpty()) {
			if(bankRepository.findByIban(bankAccount.getIban()).isEmpty()) {
				if(matcher.matches()) {
					bank.setBankName(bankAccount.getBankName());
					bank.setAgence(bankAccount.getAgence());
					bank.setIban(bankAccount.getIban());
					accountId = accountRepository.findByFirstNameAndLastName(bankAccount.getFirstName(), bankAccount.getLastName()).get(0).getAccountId();
					bank.setAccount(accountMapper.accountToAccountDto(accountRepository.findById(accountId).get()));
					bank = mapper.bankToBankDto(bankRepository.save(mapper.bankDtoToBank(bank)));
					return bank;
				}
				else throw new ApiException("IBAN is not valid", HttpStatus.NOT_ACCEPTABLE);
			}
			else throw new ApiException("IBAN already exists", HttpStatus.NOT_ACCEPTABLE);
		}
		else throw new ApiException("Account does not exists", HttpStatus.NOT_ACCEPTABLE);
	}
	
	public List<BankDto> getBankByAccountId(int accountId){
		Optional<Account> accountById = accountRepository.findById(accountId);
		List<BankDto> banks = new ArrayList<>();
		if(!accountById.isEmpty()) {
			List<Bank> banksByAccount = bankRepository.findByAccount(accountById.get());
			if(!banksByAccount.isEmpty()) {
				for(Bank bank : banksByAccount) {
					banks.add(mapper.bankToBankDto(bank));
				}
				return banks;
			}
			throw new ApiException("There is no bank for this account", HttpStatus.NOT_FOUND);
		}
		else {
			throw new ApiException("Account does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	public List<BankDto> getBanks() {
		List<BankDto> listBank = new ArrayList<>();
		for(Bank bank : bankRepository.findAll()) {
			listBank.add(mapper.bankToBankDto(bank));
		}
		return listBank;
	}
	
	public List<BankDto> getBankByIban(String iban){
		Bank bankByIban= bankRepository.findByIban(iban).get(0);
		List<BankDto> listBankDto = new ArrayList<>();
		if(bankByIban != null) {
			listBankDto.add(mapper.bankToBankDto(bankByIban));
		}
		return listBankDto;	
	}
	
	public void deleteAll() {
		if(!bankRepository.findAll().isEmpty()){
			for(Bank bank : bankRepository.findAll()) {
				bankRepository.delete(bank);
			}
		}
	}
	
	public String deleteByAccountId(int id) {
		Optional<Account> account = accountRepository.findById(id);
		if(!account.isEmpty()) {
			for(Bank bank : bankRepository.findAll()) {
				if(bank.getAccount().getAccountId() == id) {
					bankRepository.delete(bank);
				}
			}
			return "Banks linked with the account " + id + " have been deleted";
		}
		else {
			throw new ApiException("Account does not exists", HttpStatus.NOT_FOUND);
		}
	}
	
	public String deleteByIban(String iban) {
		List<Bank> listBank = bankRepository.findByIban(iban);
		if(listBank.isEmpty()) {
			throw new ApiException("IBAN does not exists", HttpStatus.NOT_FOUND);
		}
		else {
			for(Bank bank : listBank) {
				bankRepository.delete(bank);
			}
			return "Bank : " + iban + " deleted.";
		}
	}
	
	
	
}
