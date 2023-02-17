package com.seerbit.transactionstatistics.util;

import com.seerbit.transactionstatistics.dto.request.TransactionRequest;
import com.seerbit.transactionstatistics.exceptionHandler.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Slf4j
public class ValidationUtil {

    public static boolean isTransactionRequestValid(TransactionRequest transactionRequest) {
        try {
            LocalDateTime parsedTimestamp = AppUtil.convertToLocalDatetime(transactionRequest.getTimestamp());
            if (parsedTimestamp.isAfter(LocalDateTime.now())) {
                log.info("Future date");
                return false;
            }
            new BigDecimal(transactionRequest.getAmount());
        } catch (NumberFormatException | DateTimeParseException ex){
            log.error(" Failed Validation ::: {}", ex);
            throw new ValidationException("Failed Validation");
        }
        return true;
    }
}
