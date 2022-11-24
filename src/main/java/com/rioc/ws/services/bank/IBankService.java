package com.rioc.ws.services.bank;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rioc.ws.model.dto.BankAccountDto;
import com.rioc.ws.model.dto.BankDto;

public interface IBankService {
	public BankDto postBank(BankAccountDto bankAccount);
	public List<BankDto> getBankByAccountId(int accountId);
	public void deleteAll();
	public String deleteByAccountId(int id);
	public String deleteByIban(String iban);
	public List<BankDto> getBanks();
	public List<BankDto> getBankByIban(String iban);
}
