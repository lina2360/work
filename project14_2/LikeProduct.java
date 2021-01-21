package com.project14.nccu105.project14_2;

public class LikeProduct {
    private String ordernum;
    private String ordername;
    private String picture;
//    private int pic;

    public LikeProduct(String ordernum,String ordername,String picture){
        this.ordernum = ordernum;
        this.ordername = ordername;
        this.picture=picture;



    }

    public LikeProduct(){

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

    public String getPicture() {
        return picture;
    }

    public void setPictr(String picture) {
        this.picture=picture;
    }





}
