package com.milinddev.bankingapp.service.impl;

import com.milinddev.bankingapp.dto.AccountDto;
import com.milinddev.bankingapp.entity.Account;
import com.milinddev.bankingapp.mapper.AccountMapper;
import com.milinddev.bankingapp.repository.AccountRepository;
import com.milinddev.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpls implements AccountService {

    private AccountRepository accountRepository;

//    @Autowired
    public AccountServiceImpls(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
           Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        if(account.getBalance()< amount){
            throw new RuntimeException("insuffient amount");
        }
        double total = account.getBalance() +  amount;
        account.setBalance(total);
        Account savedBalance = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedBalance);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() ->new RuntimeException());
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        accountRepository.deleteById(id);
    }
}
