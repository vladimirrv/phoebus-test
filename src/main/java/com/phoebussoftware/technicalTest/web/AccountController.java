package com.phoebussoftware.technicalTest.web;

import com.phoebussoftware.technicalTest.DTO.AccountDTO;
import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.repository.AccountRepository;
import com.phoebussoftware.technicalTest.service.AccountService;
import org.apache.tomcat.util.json.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    public AccountController() {}

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        AccountEntity accountEntity = accountService.getAccountById(accountId);
        return ResponseEntity.ok(convertToDto(accountEntity).builder().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AccountDTO createAccount(@Valid @RequestBody AccountDTO accountDto) throws ParseException {
        AccountEntity account = convertToEntity(accountDto);
        AccountEntity accountCreated = accountService.createAccount(account);
        return convertToDto(accountCreated);
    }

    private AccountDTO convertToDto(AccountEntity accountEntity) {
        AccountDTO accountDTO = modelMapper.map(accountEntity, AccountDTO.class);
        return accountDTO;
    }

    private AccountEntity convertToEntity(AccountDTO accountDto) throws ParseException {
        AccountEntity accountEntity = modelMapper.map(accountDto, AccountEntity.class);

        if (accountEntity.getAccountId() != null) {
            AccountEntity oldAccount = accountService.getAccountById(accountDto.getAccountId());
            accountEntity.setAccountNumber(oldAccount.getAccountNumber());
        }
        return accountEntity;
    }

}
