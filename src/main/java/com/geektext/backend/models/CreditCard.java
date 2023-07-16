package com.geektext.backend.models;

import java.util.List;

public class CreditCard {
    private String username;
    private String creditCardNumber;
    private String expirationDate;
    private String securityCode;
    private List<String> billingAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditcardNumber) {
        this.creditCardNumber = creditcardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public List<String> getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(List<String> billingAddress) {
        this.billingAddress = billingAddress;
    }

    public static boolean isValid(CreditCard jsonCreditCard) {
        if (jsonCreditCard.getUsername() == null || jsonCreditCard.getUsername().isEmpty()) {
            return false;
        }

        if (jsonCreditCard.getCreditCardNumber() == null || jsonCreditCard.getCreditCardNumber().isEmpty()) {
            return false;
        }

        if (jsonCreditCard.getBillingAddress() == null || jsonCreditCard.getBillingAddress().size() == 0) {
            return false;
        }

        if (jsonCreditCard.getExpirationDate() == null || jsonCreditCard.getExpirationDate().isEmpty()) {
            return false;
        }

        if (jsonCreditCard.getSecurityCode() == null || jsonCreditCard.getSecurityCode().isEmpty()) {
            return false;
        }

        return true;
    }
}
