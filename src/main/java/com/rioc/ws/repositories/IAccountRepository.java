package com.rioc.ws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rioc.ws.model.dao.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>{
	public List<Account> findByFirstNameAndLastName(@Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName);
}
