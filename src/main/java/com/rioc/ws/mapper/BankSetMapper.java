package com.rioc.ws.mapper;

import java.util.HashSet;
import java.util.Set;

import com.rioc.ws.model.dao.Bank;
import com.rioc.ws.model.dto.BankDto;

public class BankSetMapper implements IBankSetMapper{
	
		private IBankMapper mapper;
	
		public BankSetMapper(IBankMapper mapper) {
			super();
			this.mapper = mapper;
		}

		public Set<Bank> BankSetDtoToBankSet(Set<BankDto> banksDto) {
			Set<Bank> banks = new HashSet<>();
			for(BankDto bankDto : banksDto) {
				banks.add(mapper.bankDtoToBank(bankDto));	
			}
			return banks;
		}
		
		public Set<BankDto> BankSetToBankSetDto(Set<Bank> banks) {
			Set<BankDto> banksDto = new HashSet<>();
			for(Bank bank : banks) {
				banksDto.add(mapper.bankToBankDto(bank));	
			}
			return banksDto;
		}
}
