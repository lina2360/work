package com.project14.nccu105.project14_2;

public class InitialChat {
    private String user1;
    private String user2;
//    private String chat;

    public InitialChat(String user1,String user2) {
        this.user1 = user1;
        this.user2=user2;
//        this.chat=chat;
    }

    public InitialChat() {

    }


    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1=user1;
    }

    public String getUser2() { return user2; }

    public void setUser2(String user2) {
        this.user2 = user2;
    }
//    public String getChat() { return chat; }
//
//    public void setChat(String chat) {
//        this.chat = chat;
//    }

}
