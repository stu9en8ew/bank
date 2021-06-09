package it.banking.service;

import it.banking.repository.AccountRepository;
import it.banking.rto.TransactionRto;
import it.banking.rto.BalanceRto;
import it.banking.rto.MoneyTransferRto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<BalanceRto> getBalance(Long accountId){

        return accountRepository.getBalance(accountId);
    }

    public ResponseEntity<List<TransactionRto>> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate){
        return accountRepository.getTransactions(accountId, fromAccountingDate, toAccountingDate);
    }

    public ResponseEntity<MoneyTransferRto> createMoneyTransfer(Long accountId, String receiverName, String description, String currency, String amount, String executionDate){
        return accountRepository.createMoneyTransfer(accountId, receiverName, description, currency, amount, executionDate);
    }


}
