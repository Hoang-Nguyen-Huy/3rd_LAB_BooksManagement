package model;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nguyen Huy Hoang SE180435
 */
public class Book implements Serializable{

    private String id;
    private String title;
    private float price;
    private Author author;

    public Book(String id, String title, float price, Author author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        String formattedString = String.format("%-10s%-80s%-15s%-10s%-15s",
                this.id, this.title, this.price, this.author.getAuthorId(), this.author.getName());
        return formattedString;
    }
    
    

}
