package it.banking.rto;

public class TransactionRto {

    private String accountId;
    private String iban;
    private String abiCode;
    private String cabCode;
    private String countryCode;
    private String internationalCin;
    private String nationalCin;
    private String account;
    private String alias;
    private String productName;
    private String holderName;
    private String activatedDate;
    private String currency;

    public String getAccountId() {
        return accountId;
    }

    public String getIban() {
        return iban;
    }

    public String getAbiCode() {
        return abiCode;
    }

    public String getCabCode() {
        return cabCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getInternationalCin() {
        return internationalCin;
    }

    public String getNationalCin() {
        return nationalCin;
    }

    public String getAccount() {
        return account;
    }

    public String getAlias() {
        return alias;
    }

    public String getProductName() {
        return productName;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getActivatedDate() {
        return activatedDate;
    }

    public String getCurrency() {
        return currency;
    }
}
