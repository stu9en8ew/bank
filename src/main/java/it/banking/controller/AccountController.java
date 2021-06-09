package it.banking.controller;

import it.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/banking/account", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("Api-Key") String apiKey,
            @RequestHeader("Auth-Schema") String authSchema,
            @RequestParam(required = true) Long accountId){

        try {
            return accountService.getBalance(contentType, apiKey, authSchema, accountId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Balance not found.", e);
        }

    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions(
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("Api-Key") String apiKey,
            @RequestHeader("Auth-Schema") String authSchema,
            @RequestParam(required = true) Long accountId,
            @RequestParam(required = true) String fromAccountingDate,
            @RequestParam(required = true) String toAccountingDate){

        try {
            return accountService.getTransactions(contentType, apiKey, authSchema, accountId, fromAccountingDate, toAccountingDate);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transactions not found.", e);
        }

    }


    @PostMapping("/moneyTransfer")
    public ResponseEntity<?> createMoneyTransfer(
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("Api-Key") String apiKey,
            @RequestHeader("Auth-Schema") String authSchema,
            @RequestBody Long accountId,
            @RequestBody String receiverName,
            @RequestBody String description,
            @RequestBody String currency,
            @RequestBody String amount,
            @RequestBody String executionDate){


        try {
            return accountService.createMoneyTransfer(contentType, apiKey, authSchema, accountId, receiverName, description, currency, amount, executionDate);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "MoneyTransfer not created", e);
        }

    }

}
