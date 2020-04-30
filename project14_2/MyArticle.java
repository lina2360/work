package com.project14.nccu105.project14_2;

public class MyArticle {
    private String ordernum;
    private String ordername;
    private String db;
    private String picture;
    private String db2;
//    private int pic;

    public MyArticle(String ordernum,String ordername,String db,String picture,String db2){
        this.ordernum = ordernum;
        this.ordername = ordername;
        this.db=db;
        this.picture=picture;
        this.db2=db2;



    }

    public MyArticle(){

    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum =ordernum;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername=ordername;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db =db;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture =picture;
    }

    public String getDb2() {
        return db2;
    }

    public void setDb2(String db2) {
        this.db2 =db2;
    }



}
