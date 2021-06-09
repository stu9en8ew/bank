package it.banking.repository;

import it.banking.rto.TransactionRto;
import it.banking.rto.BalanceRto;
import it.banking.rto.MoneyTransferRto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AccountRepository {

    @GetMapping("/api/gbs/banking/v4.0/accounts")
    ResponseEntity<BalanceRto> getBalance(@RequestParam @NotNull Long accountId);

    @GetMapping("/api/gbs/banking/v4.0/accounts")
    ResponseEntity<List<TransactionRto>> getTransactions(@RequestParam @NotNull Long accountId,
                                                         @RequestParam String fromAccountingDate,
                                                         @RequestParam String toAccountingDate);

    @PostMapping("/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers")
    ResponseEntity<MoneyTransferRto> createMoneyTransfer(@RequestBody @NotNull Long accountId,
                                                         @RequestBody String receiverName,
                                                         @RequestBody String description,
                                                         @RequestBody String currency,
                                                         @RequestBody String amount,
                                                         @RequestBody String executionDate);
}
