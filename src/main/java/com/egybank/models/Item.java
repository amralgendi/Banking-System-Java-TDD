//
//  Item.java
//  Software-TestingProj
//
//  Created by Mohamed Salah on 6/9/2023.
//

package com.egybank.models;

public class Item {

    private int id;
    private String name, category;
    private int price;

    public Item(int id, String name, String category, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
