package com.milinddev.bankingapp.dto;

public record TransferFundDto(Long fromAccountId,Long toAccountId, double amount) {

}
