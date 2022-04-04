package com.phoebussoftware.technicalTest.service;

import com.phoebussoftware.technicalTest.model.AccountEntity;
import org.springframework.validation.annotation.Validated;

public interface AccountService {

    AccountEntity getAccountById(Long id);

    AccountEntity createAccount(AccountEntity account);
}
