package it.banking.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.banking.dto.MoneyTransferDto;
import org.springframework.http.HttpHeaders;

public class AccountUtil {

    public static HttpHeaders buildHeaders(String contentType, String apiKey, String authSchema) {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", contentType);
        headers.set("Api-Key", apiKey);
        headers.set("Auth-Schema", authSchema);

        return headers;
    }


    public static String getRequestJson(Long accountId) {
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

    public static String getRequestJson(String fromAccountingDate, String toAccountingDate) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();

        //((ObjectNode) rootNode).put("accountId", accountId);
        ((ObjectNode) rootNode).put("fromAccountingDate", fromAccountingDate);
        ((ObjectNode) rootNode).put("toAccountingDate", toAccountingDate);

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

    public static String getRequestJson(MoneyTransferDto moneyTransferDto) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();

        ((ObjectNode) rootNode).put("accountId", moneyTransferDto.getAccountId());
        ((ObjectNode) rootNode).put("receiverName", moneyTransferDto.getReceiverName());
        ((ObjectNode) rootNode).put("description", moneyTransferDto.getDescription());
        ((ObjectNode) rootNode).put("currency", moneyTransferDto.getCurrency());
        ((ObjectNode) rootNode).put("amount", moneyTransferDto.getAmount());
        ((ObjectNode) rootNode).put("executionDate", moneyTransferDto.getExecutionDate());

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


    public static String urlBalance(){
        return "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts";
    }

    public static String urlTransactions(Long accountId){
        return "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId +"/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
    }

    public static String urlMoneyTransfer(Long accountId){
        return "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + accountId +"/payments/money-transfers";
    }


}
