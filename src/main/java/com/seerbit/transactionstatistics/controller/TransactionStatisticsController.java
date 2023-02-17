package com.seerbit.transactionstatistics.controller;

import com.seerbit.transactionstatistics.dto.request.TransactionRequest;
import com.seerbit.transactionstatistics.dto.response.StatisticsResponse;
import com.seerbit.transactionstatistics.model.Transaction;
import com.seerbit.transactionstatistics.service.TransactionStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static com.seerbit.transactionstatistics.constant.AppConstant.API_VERSION_1;

@RestController
@RequiredArgsConstructor
@RequestMapping("/" + API_VERSION_1)
public class TransactionStatisticsController extends ResponseEntityExceptionHandler {

    private final TransactionStatisticsService transactionStatisticsService;

    @PostMapping("/transactions")
    public ResponseEntity createTransaction(@RequestBody TransactionRequest transactionRequest){
        Transaction transaction = transactionStatisticsService.addTransaction(transactionRequest);
        if(transaction.getTimestamp().isBefore(LocalDateTime.now().minusSeconds(30))) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getTransactionStatistics(){
        return new ResponseEntity<>(transactionStatisticsService.getTransactionStatistics(), HttpStatus.OK);
    }

    @DeleteMapping("/transactions")
    public ResponseEntity deleteTransactions(){
        transactionStatisticsService.removeTransaction();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
