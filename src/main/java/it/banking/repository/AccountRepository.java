package it.banking.repository;

import it.banking.rto.BalanceRto;
import it.banking.rto.MoneyTransferRto;
import it.banking.rto.TransactionRto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface AccountRepository {

    @GetMapping("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts")
    ResponseEntity<BalanceRto> getBalance(@RequestParam @NotNull Long accountId);

    @GetMapping("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions")
    ResponseEntity<List<TransactionRto>> getTransactions(@PathVariable Long accountId,
                                                         @RequestParam String fromAccountingDate,
                                                         @RequestParam String toAccountingDate);

    @PostMapping("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers")
    ResponseEntity<MoneyTransferRto> createMoneyTransfer(@PathVariable Long accountId,
                                                         @RequestBody String receiverName,
                                                         @RequestBody String description,
                                                         @RequestBody String currency,
                                                         @RequestBody String amount,
                                                         @RequestBody String executionDate);
}
