//
//  Transaction.java
//  Software-TestingProj
//
//  Created by Mohamed Salah on 6/9/2023.
//

package com.egybank.models;

import com.egybank.helpers.db.dao.TransactionType;

public class Transaction {

    private int id;
    private String name;
    private int from_account, to_account;
    private int amount;
    private TransactionType type;

    public Transaction(int id, String name, int from_account, int to_account, int amount, int type) {
        this.id = id;
        this.name = name;
        this.from_account = from_account;
        this.to_account = to_account;
        this.amount = amount;
        this.type = TransactionType.fromInteger(type);
    }

    public int getId() {
        return id;
    }

    public int getFrom_account() {
        return from_account;
    }

    public int getTo_account() {
        return to_account;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
