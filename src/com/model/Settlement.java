package com.model;

public class Settlement {

    public Settlement(){

    }

    public Settlement(Friend from, Friend to, int amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    private Friend from;

    private Friend to;

    private int amount;

    public Friend getFrom() {
        return from;
    }

    public void setFrom(Friend from) {
        this.from = from;
    }

    public Friend getTo() {
        return to;
    }

    public void setTo(Friend to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSettlementReport(){
        return this.from.getName() + " -> "+ this.to.getName() + " : " + this.amount;
    }
}
