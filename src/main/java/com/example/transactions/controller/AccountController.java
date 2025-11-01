package com.example.transactions.controller;

import com.example.transactions.dto.AccountRequestDto;
import com.example.transactions.dto.AccountResponseDto;
import com.example.transactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping
    public AccountResponseDto createAccount(@RequestBody AccountRequestDto accountRequest) {
        return accountService.createAccount(accountRequest);
    }

    @GetMapping
    public List<AccountResponseDto> getAccounts() {
        return accountService.getAccounts();
    }
}
