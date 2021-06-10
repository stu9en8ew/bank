package it.banking.client;

import it.banking.dto.MoneyTransferDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


public interface AccountProducerClient {

    ResponseEntity<?> getBalance(@RequestHeader("Content-Type") String contentType,
                                 @RequestHeader("Api-Key") String apiKey,
                                 @RequestHeader("Auth-Schema") String authSchema,
                                 @RequestParam @NotNull Long accountId);

    ResponseEntity<?> getTransactions(@RequestHeader("Content-Type") String contentType,
                                      @RequestHeader("Api-Key") String apiKey,
                                      @RequestHeader("Auth-Schema") String authSchema,
                                      @PathVariable Long accountId,
                                      @RequestParam String fromAccountingDate,
                                      @RequestParam String toAccountingDate);

    ResponseEntity<?> createMoneyTransfer(@RequestHeader("Content-Type") String contentType,
                                          @RequestHeader("Api-Key") String apiKey,
                                          @RequestHeader("Auth-Schema") String authSchema,
                                          @RequestBody MoneyTransferDto moneyTransferDto);
}
