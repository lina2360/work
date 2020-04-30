package com.project14.nccu105.project14_2;

import java.util.Date;

public class Article {

    private String article;
    private String classify;
    private String messageUser;
    private long messageTime;
    private String messageText;
    private String picture;
    private  String usernick;

    public Article(String article,String classify,String messageText, String messageUser,String picture,String usernick) {
        this.article = article;
        this.classify = classify;
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.usernick=usernick;
        // Initialize to current time
        messageTime = new Date().getTime();
        this.picture=picture;
    }

    public Article() {

    }
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getClassify() { return classify; }

    public void setClassify(String classify) {
        this.classify = classify;
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture =picture;
    }

    public String getUsernick() {
        return usernick;
    }

    public void setUsernick(String usernick) {
        this.usernick =usernick;
    }
}
