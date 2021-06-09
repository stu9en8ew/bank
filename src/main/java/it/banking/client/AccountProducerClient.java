package it.banking.client;

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
                                          @PathVariable Long accountId,
                                          @RequestBody String receiverName,
                                          @RequestBody String description,
                                          @RequestBody String currency,
                                          @RequestBody String amount,
                                          @RequestBody String executionDate);
}
