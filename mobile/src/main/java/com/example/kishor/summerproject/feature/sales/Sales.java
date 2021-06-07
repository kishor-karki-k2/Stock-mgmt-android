package com.example.kishor.summerproject.feature.sales;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sales extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    private String date;
    private String quantity;

    public int getId() {
        return id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
