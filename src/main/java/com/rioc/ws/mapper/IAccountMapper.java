package com.rioc.ws.mapper;

import org.mapstruct.Mapper;

import com.rioc.ws.model.dao.Account;
import com.rioc.ws.model.dto.AccountDto;

@Mapper(componentModel = "spring")
public interface IAccountMapper {
	AccountDto accountToAccountDto(Account account);
	Account accountDtoToAccount(AccountDto accountDto);
}
