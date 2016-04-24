package com.slack.csci3308project.dailyfortune;

/**
 * Created by luke on 3/24/16.
 */
public class GeneralQuote {
    private int id;
    private String quote;
    private String author;

    /**
     * @return the id of this quote
     */
    public int getId(){
        return id;
    }

    /**
     * Set the id of this quote
     * @param id the new id that this quote will have
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * @return The String representation of this quote
     */
    public String getQuote(){
        return quote;
    }

    /**
     * Set the String representation of this quote.
     * @param quote The new String representation of this quote.
     */
    public void setQuote(String quote){
        this.quote = quote;
    }

    /**
     * @return The author of this quote
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Set the author of this quote
     * @param author The new author of this quote
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * @return The String value of this quote
     */
    public String toStringQuote(){
        return quote;
    }

    /**
     * @return The String value of this author
     */
    public String toStringAuthor(){
        return author;
    }
}
