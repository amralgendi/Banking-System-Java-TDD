//
//  Bill.java
//  Software-TestingProj
//
//  Created by Mohamed Salah on 6/9/2023.
//

package com.egybank.models;

public class Bill {

    private int id;
    private String name;
    private int account_id;
    private int amount;
    private boolean isPaid;

    public Bill(int id, String name, int account_id, int amount, int isPaid) {
        this.id = id;
        this.name = name;
        this.account_id = account_id;
        this.amount = amount;
        this.isPaid = isPaid == 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }
}
