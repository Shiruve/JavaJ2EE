package com.rioc.ws.mapper;

import org.mapstruct.Mapper;

import com.rioc.ws.model.dao.Bank;
import com.rioc.ws.model.dto.BankDto;

@Mapper(componentModel = "spring")
public interface IBankMapper {
	BankDto bankToBankDto(Bank bank);
	Bank bankDtoToBank(BankDto bankDto);
}
