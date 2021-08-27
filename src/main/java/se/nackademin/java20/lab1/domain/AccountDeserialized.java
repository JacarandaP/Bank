package se.nackademin.java20.lab1.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-26
 * Project: lab2
 */

public class AccountDeserialized {
    private String balance;
    private String holder;

    @JsonCreator //behövs inte i alla fall, inte heller JsonProperty.
    public AccountDeserialized(@JsonProperty("balance")String balance, @JsonProperty("holder")String holder) {
        this.balance = balance;
        this.holder = holder;
    }

    public AccountDeserialized() {

    }

    public String getBalance() {
        return balance;
    }

    public String getHolder() {
        return holder;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
