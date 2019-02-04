package com.test.other;

public class Book {
    private String name, author;
    private float price, rate;
    private boolean isBestseller;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return price;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
    public float getRate() {
        return rate;
    }

    public void setBestseller(boolean bestseller){
        this.isBestseller = bestseller;
    }
    public boolean getBestseller(){
        return isBestseller;
    }
}
