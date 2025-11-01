package com.example.transactions.service;

import com.example.transactions.dto.AccountRequestDto;
import com.example.transactions.dto.AccountResponseDto;
import com.example.transactions.entity.Account;
import com.example.transactions.entity.Address;
import com.example.transactions.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AccountRepository accountRepository;


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountResponseDto createAccount(AccountRequestDto accountRequest) {

        Account account = Account.builder()
                .name(accountRequest.getName())
                .balance(accountRequest.getBalance())
                .build();
        Account savedAccount = accountRepository.save(account);

        if (accountRequest.getName().equals("error")) {
            throw  new RuntimeException("Name should contain word 'error'");
        }

        Address address = Address.builder()
                .accountId(savedAccount.getId())
                .city(accountRequest.getCity())
                .build();

        Address savedAddress = addressService.saveAddressRequiredTransaction(address);

        return AccountResponseDto.builder()
                .id(savedAccount.getId())
                .name(savedAccount.getName())
                .balance(savedAccount.getBalance())
                .city(savedAddress.getCity())
                .build();
    }

    public List<AccountResponseDto> getAccounts() {
        List<Account> accountList = accountRepository.findAll();
        List<Address> addressList = addressService.getAddresses();

        List<AccountResponseDto> accountResponseDtoList = new ArrayList<>();
        Map<Long, Address> addressMap = addressList.stream()
                .collect(Collectors.toMap(Address::getAccountId, Function.identity(), (a1, a2) -> a1));

        accountList.forEach(account -> {
                                Address address = addressMap.get(account.getId());
                                AccountResponseDto accountResponseDto = AccountResponseDto.builder()
                                        .id(account.getId())
                                        .name(account.getName())
                                        .balance(account.getBalance())
                                        .addressId(address.getId())
                                        .city(address.getCity())
                                        .build();
                                accountResponseDtoList.add(accountResponseDto);

                        });
        return accountResponseDtoList;
    }
}
