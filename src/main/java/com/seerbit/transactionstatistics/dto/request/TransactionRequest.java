package com.seerbit.transactionstatistics.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {
    private String amount;
    private String timestamp;
}
