package it.banking.client;

import it.banking.dto.MoneyTransferDto;
import it.banking.util.AccountUtil;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountProducerClientImp implements AccountProducerClient {


    @Override
    public ResponseEntity<?> getBalance(String contentType, String apiKey, String authSchema, @NotNull Long accountId) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = AccountUtil.buildHeaders(contentType, apiKey, authSchema);
        String requestJson = AccountUtil.getRequestJson(accountId);

        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<?> responseEntity;
        responseEntity = restTemplate.exchange(AccountUtil.urlBalance(), HttpMethod.GET, request, Object.class);

        return responseEntity;
    }

    @Override
    public ResponseEntity<?> getTransactions(String contentType, String apiKey, String authSchema, Long accountId, String fromAccountingDate, String toAccountingDate) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = AccountUtil.buildHeaders(contentType, apiKey, authSchema);
        String requestJson = AccountUtil.getRequestJson(fromAccountingDate, toAccountingDate);

        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<?> responseEntity;
        responseEntity = restTemplate.exchange(AccountUtil.urlTransactions(accountId), HttpMethod.GET, request, Object.class, fromAccountingDate, toAccountingDate);

        return responseEntity;
    }


    @Override
    public ResponseEntity<?> createMoneyTransfer(String contentType, String apiKey, String authSchema, MoneyTransferDto moneyTransferDto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = AccountUtil.buildHeaders(contentType, apiKey, authSchema);

        JSONObject test = new JSONObject();
        test.put("accountId", moneyTransferDto.getAccountId());
        test.put("receiverName", moneyTransferDto.getReceiverName());
        test.put("description", moneyTransferDto.getDescription());
        test.put("currency", moneyTransferDto.getCurrency());
        test.put("amount", moneyTransferDto.getAmount());
        test.put("executionDate", moneyTransferDto.getExecutionDate());

        HttpEntity<?> request = new HttpEntity<>(test, headers);
        ResponseEntity<?> responseEntity;
        responseEntity =
                restTemplate.postForEntity(AccountUtil.urlMoneyTransfer(moneyTransferDto.getAccountId()), request, String.class);
        responseEntity.getBody();

        return responseEntity;
    }
}
