package com.milinddev.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolder;
    private double balance;
}
