package com.google.sps.data;

//this is basically the userEntries
public class Comments {
    public String comment;
    public long author;
    public String timestamp;
    public String email;

    public Comments(String comment, long author, String timestamp, String email){
        this.comment=comment; 
        this.author=author; 
        this.timestamp=timestamp; 
        this.email=email;
    }

    
    public Comments (String comment, long author, String timestamp){
        this.comment=comment; 
        this.author=author; 
        this.timestamp=timestamp; 
        this.email="";
    }


}