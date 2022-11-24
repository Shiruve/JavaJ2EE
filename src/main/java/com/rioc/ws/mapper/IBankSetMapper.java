package com.rioc.ws.mapper;

import java.util.Set;

import com.rioc.ws.model.dao.Bank;
import com.rioc.ws.model.dto.BankDto;

public interface IBankSetMapper {
	public Set<BankDto> BankSetToBankSetDto(Set<Bank> banks);
	public Set<Bank> BankSetDtoToBankSet(Set<BankDto> banksDto);
}
