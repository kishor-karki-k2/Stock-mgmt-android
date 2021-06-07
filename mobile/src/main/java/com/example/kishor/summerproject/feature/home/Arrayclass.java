package com.example.kishor.summerproject.feature.home;

public class Arrayclass  {
    String text;
    int id;

    public Arrayclass(int id,String text)
    {
        this.id=id;
        this.text=text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
