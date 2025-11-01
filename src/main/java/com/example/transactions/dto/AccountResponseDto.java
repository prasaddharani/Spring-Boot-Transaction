package com.example.transactions.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {

    private Long id;
    private String name;
    private double balance;
    private Long addressId;
    private String city;
}
