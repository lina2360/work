package com.project14.nccu105.project14_2;


import java.util.Date;

public class ChatMessage  {

    private String messageText;
    private String messageUser;
    private long messageTime;
    private  String useracc;

    public ChatMessage(String messageText, String messageUser,String useracc) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.useracc=useracc;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

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

    public String getUseracc() {
        return useracc;
    }

    public void setUseracc(String useracc) {
        this.useracc = useracc;
    }
}