package it.banking.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

@Component
public class AccountProducerClientImp implements AccountProducerClient {

    private String getRequetJson(Long accountId) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("accountId", accountId);
        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(rootNode);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


    @Override
    public ResponseEntity<?> getBalance(String contentType, String apiKey, String authSchema, @NotNull Long accountId) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", contentType);
        headers.set("Api-Key", apiKey);
        headers.set("Auth-Schema", authSchema);

        String requestJson = getRequetJson(accountId);

        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<?> responseEntity;
        responseEntity =
                restTemplate.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts", HttpMethod.GET, request, Object.class);
        responseEntity.getBody();

        return responseEntity;
    }

    @Override
    public ResponseEntity<?> getTransactions(String contentType, String apiKey, String authSchema, Long accountId, String fromAccountingDate, String toAccountingDate) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", contentType);
        headers.set("Api-Key", apiKey);
        headers.set("Auth-Schema", authSchema);

        String requestJson = getRequetJson(accountId);

        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<?> responseEntity;
        responseEntity =
                restTemplate.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId +"/transactions", HttpMethod.GET, request, Object.class);
        responseEntity.getBody();

        return responseEntity;
    }


    @Override
    public ResponseEntity<?> createMoneyTransfer(String contentType, String apiKey, String authSchema, Long accountId, String receiverName, String description, String currency, String amount, String executionDate) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", contentType);
        headers.set("Api-Key", apiKey);
        headers.set("Auth-Schema", authSchema);

        String requestJson = getRequetJson(accountId);

        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<?> responseEntity;
        responseEntity =
                restTemplate.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId +"/payments/money-transfers", HttpMethod.POST, request, Object.class);
        responseEntity.getBody();

        return responseEntity;
    }
}
