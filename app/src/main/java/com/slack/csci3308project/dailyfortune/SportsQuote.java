package com.slack.csci3308project.dailyfortune;

/**
 * Created by luke on 3/25/16.
 */
public class SportsQuote {
    private int id;
    private String quote;
    private String author;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getQuote(){
        return quote;
    }

    public void setQuote(String quote){
        this.quote = quote;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String toStringQuote(){
        return quote;
    }

    public String toStringAuthor(){
        return author;
    }
}
