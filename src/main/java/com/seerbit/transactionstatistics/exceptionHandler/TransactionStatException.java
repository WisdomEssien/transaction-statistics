package com.seerbit.transactionstatistics.exceptionHandler;

public class TransactionStatException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public TransactionStatException(String message) {
        super(message);
    }
}
