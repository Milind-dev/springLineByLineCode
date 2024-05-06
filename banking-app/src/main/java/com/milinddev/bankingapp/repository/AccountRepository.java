package com.milinddev.bankingapp.repository;

import com.milinddev.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account,Long> {

}
