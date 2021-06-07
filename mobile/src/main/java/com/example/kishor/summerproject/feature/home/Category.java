package com.example.kishor.summerproject.feature.home;

import com.example.kishor.summerproject.feature.category.Product;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Category extends RealmObject {
    private int id;
    private String title;
    private int image;
    private RealmList<Product> productArrayList = new RealmList<>();

    public RealmList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList.clear();
        this.productArrayList.addAll(productArrayList);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
