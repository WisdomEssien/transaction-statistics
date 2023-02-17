package com.seerbit.transactionstatistics.service;

import com.seerbit.transactionstatistics.dto.request.TransactionRequest;
import com.seerbit.transactionstatistics.dto.response.StatisticsResponse;
import com.seerbit.transactionstatistics.exceptionHandler.TransactionStatException;
import com.seerbit.transactionstatistics.exceptionHandler.ValidationException;
import com.seerbit.transactionstatistics.model.Transaction;
import com.seerbit.transactionstatistics.repository.TransactionStatisticsRepository;
import com.seerbit.transactionstatistics.util.AppUtil;
import com.seerbit.transactionstatistics.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionStatisticsService {

    private final TransactionStatisticsRepository transactionStatisticsRepository;

    public Transaction addTransaction(TransactionRequest request){

        if(!ValidationUtil.isTransactionRequestValid(request)){
            log.info("Validate Request");
            throw new ValidationException("Failed Validation");
        }

        Transaction transaction = transactionStatisticsRepository.save(Transaction.builder()
                .amount(new BigDecimal(request.getAmount()).setScale(2, RoundingMode.HALF_UP))
                .timestamp(AppUtil.convertToLocalDatetime(request.getTimestamp()))
                .build());

        log.info("Transaction Model :: {}", transaction);
        if(Objects.isNull(transaction)){
            throw new TransactionStatException("Could not save to the DB");
        }
        return transaction;
    }

    public StatisticsResponse getTransactionStatistics(){

        List<Transaction> allTransactionsSortedByAmountDesc = transactionStatisticsRepository.getAllTransactions().stream()
                .filter(transaction -> transaction.getTimestamp().isAfter(LocalDateTime.now().minusSeconds(30)))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        long numberOfTransactions = allTransactionsSortedByAmountDesc.stream().count();
        BigDecimal sum = allTransactionsSortedByAmountDesc.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = sum.divide(BigDecimal.valueOf(numberOfTransactions));
        StatisticsResponse statisticsResponse = StatisticsResponse.builder()
                .max(String.valueOf(allTransactionsSortedByAmountDesc.stream()
                        .map(Transaction::getAmount).max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO)))
                .min(String.valueOf(allTransactionsSortedByAmountDesc.stream()
                        .map(Transaction::getAmount).min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO)))
                .count(numberOfTransactions)
                .sum(String.valueOf(sum.setScale(2, RoundingMode.HALF_UP)))
                .avg(String.valueOf(average.setScale(2, RoundingMode.HALF_UP)))
                .build();

        log.info("Statistic ::: {}", statisticsResponse);
        return statisticsResponse;
    }

    public boolean removeTransaction(){
        log.info("Deleting all Transactions...");
        transactionStatisticsRepository.removeAllTransactions();
        log.info("All Transactions Deleted");
        return true;
    }

}
