package com.seerbit.transactionstatistics.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
    //{ "amount":"12.3343", "timestamp":"2018-07-17T09:59:51.312Z"
    private Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}
