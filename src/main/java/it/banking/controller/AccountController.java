package it.banking.controller;

import it.banking.rto.TransactionRto;
import it.banking.rto.BalanceRto;
import it.banking.rto.MoneyTransferRto;
import it.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/banking/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/balance")
    public ResponseEntity<BalanceRto> getBalance(@RequestParam(required = true) Long accountId){

        try {
            return accountService.getBalance(accountId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Balance not found.", e);
        }

    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionRto>> getTransactions(
            @RequestParam(required = true) Long accountId,
            @RequestParam(required = true) String fromAccountingDate,
            @RequestParam(required = true) String toAccountingDate){

        try {
            return accountService.getTransactions(accountId, fromAccountingDate, toAccountingDate);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transactions not found.", e);
        }

    }


    @PostMapping("/moneyTransfer")
    public ResponseEntity<MoneyTransferRto> createMoneyTransfer(
            @RequestBody Long accountId,
            @RequestBody String receiverName,
            @RequestBody String description,
            @RequestBody String currency,
            @RequestBody String amount,
            @RequestBody String executionDate){


        try {
            return accountService.createMoneyTransfer(accountId, receiverName, description, currency, amount, executionDate);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "MoneyTransfer not created", e);
        }

    }

}
