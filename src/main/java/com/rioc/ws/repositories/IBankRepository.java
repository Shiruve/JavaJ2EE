package com.rioc.ws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.rioc.ws.model.dao.Account;
import com.rioc.ws.model.dao.Bank;

public interface IBankRepository extends JpaRepository<Bank, Integer>{
	public List<Bank> findByIban(@Param(value = "iban") String iban);
	public List<Bank> findByAccount(@Param(value = "account") Account account);
}
