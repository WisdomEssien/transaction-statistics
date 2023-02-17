package com.seerbit.transactionstatistics.repository;

import com.seerbit.transactionstatistics.model.Transaction;
import com.seerbit.transactionstatistics.util.AppUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class TransactionStatisticsRepository {

    private final Database<Long, Transaction> database;

    public TransactionStatisticsRepository(@Qualifier("transactionStatisticsDatabase") Database<Long, Transaction> database){
        this.database = database;
    }

    public Transaction save(Transaction transaction){
        if(Objects.nonNull(transaction)){
            if(Objects.isNull(transaction.getId())){
                transaction.setId(AppUtil.generateId());
            }
            return database.save(transaction.getId(), transaction);
        }
        return null;
    }

    public Transaction getTransaction(Long transactionId){
        return database.read(transactionId);
    }

    public List<Transaction> getTransactions(List<Long> transactionIds){
        return database.read(transactionIds);
    }

    public List<Transaction> getAllTransactions(){
        return database.readAll();
    }

    public void removeTransaction(Long transactionId){
        database.delete(transactionId);
    }

    public void removeAllTransactions(){
        database.delete();
    }

}
