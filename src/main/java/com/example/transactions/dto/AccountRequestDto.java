package com.example.transactions.dto;

import lombok.Data;

@Data
public class AccountRequestDto {

    private String name;
    private double balance;
    private String city;
}
