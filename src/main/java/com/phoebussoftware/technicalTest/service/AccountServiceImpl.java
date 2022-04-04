package com.phoebussoftware.technicalTest.service;

import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEntity createAccount(AccountEntity account) {
        return accountRepository.save(account);
    }

    @Override
    public AccountEntity getAccountById(Long id) {
        return accountRepository.findById(id).get();
    }

}
