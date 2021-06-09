package it.banking.service;

import it.banking.client.AccountProducerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountProducerClient accountProducerClient;


    public ResponseEntity<?> getBalance(String contentType, String apiKey, String authSchema, Long accountId){

        return accountProducerClient.getBalance(contentType, apiKey, authSchema, accountId);
    }

    public ResponseEntity<?> getTransactions(String contentType, String apiKey, String authSchema, Long accountId, String fromAccountingDate, String toAccountingDate){
        return accountProducerClient.getTransactions(contentType, apiKey, authSchema, accountId, fromAccountingDate, toAccountingDate);
    }

    public ResponseEntity<?> createMoneyTransfer(String contentType, String apiKey, String authSchema, Long accountId, String receiverName, String description, String currency, String amount, String executionDate){
        return accountProducerClient.createMoneyTransfer(contentType, apiKey, authSchema, accountId, receiverName, description, currency, amount, executionDate);
    }


}
