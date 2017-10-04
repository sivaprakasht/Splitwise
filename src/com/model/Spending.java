package com.model;

public class Spending {

    public Spending() {

    }

    public Spending(Friend friend) {
        this.who = friend;
    }

    public Spending(Friend friend, int amount, String description) {
        this.who = friend;
        this.amount = amount;
        this.description = description;
    }

    private Friend who;

    private int amount;

    private String description;

    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Friend getWho() {
        return who;
    }

    public void setWho(Friend who) {
        this.who = who;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
