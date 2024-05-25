package org.example.miniWallet.Models;

import java.util.Date;

public class Transaction {
    private String senderId;
    private String receiverId;
    private double amount;
    private Date date;
    private String offerApplied;

    public Transaction(String senderId, String receiverId, double amount, Date date, String offerApplied) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.date = date;
        this.offerApplied = offerApplied;
    }


    public String getReceiverId() {
        return receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public String toString() {
        return STR."Transaction{senderId='\{senderId}\{'\''}, receiverId='\{receiverId}\{'\''}, amount=\{amount}, date=\{date}, offerApplied='\{offerApplied}\{'\''}\{'}'}";
    }
}
