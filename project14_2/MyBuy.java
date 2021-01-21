package com.project14.nccu105.project14_2;

public class MyBuy {
    private String orderTime;
    private String myName;
    private String address;
    private String orderState;
    private String renewTime;
    private String moneyWay;
    private String seller;
    private String product;
    private int num;
    private int price;
    private int fee;
    private String other;
    private long productOrderTime;
    private String deliveryTime;
    private String completeTime;
    private String orderNum;
    private String kind;
    private String picture;
    private String deliveryNum;
    private  String usernick;
    private String receipt;
    private  String receiptpic;

    public MyBuy (String orderTime, String myName, String address, String orderState, String renewTime, String moneyWay,
            String seller, String product, int num, int price,int fee, String other, long productOrderTime, String deliveryTime,
            String completeTime,String orderNum,String kind,String picture,String deliveryNum,String usernick, String receipt,String receiptpic){
        this.orderTime=orderTime;
        this.myName=myName;
        this.address=address;
        this.orderState=orderState;
        this.renewTime=renewTime;
        this.moneyWay=moneyWay;
        this.seller=seller;
        this.product=product;
        this.num=num;
        this.price=price;
        this.fee=fee;
        this.other=other;
        this.productOrderTime=productOrderTime;
        this.deliveryTime=deliveryTime;
        this.completeTime=completeTime;
        this.orderNum=orderNum;
        this.kind=kind;
        this.picture=picture;
        this.deliveryNum=deliveryNum;
        this.usernick=usernick;
        this.receipt=receipt;
        this.receiptpic=receiptpic;
    }

    public MyBuy(){

    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime =orderTime;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName=myName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderState() {
        return orderState;
    }


    public void setOrderState(String orderState) {
        this.orderState =orderState;
    }

    public String getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(String renewTime) {
        this.renewTime =renewTime;
    }

    public String getMoneyWay() {
        return moneyWay;
    }

    public void setMoneyWay(String moneyWay) {
        this.moneyWay =moneyWay;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }



    public String getProduct() {
        return product;
    }

    public void setProduct(String product) { this.product= product; }

    public long getNum() {
        return num;
    }

    public void setNum(int isSeller) {
        this.num = num;
    }



    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price =price;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee =fee;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public long getProductOrderTime() {
        return productOrderTime;
    }

    public void setProductOrderTime(long productOrderTime) {
        this.productOrderTime = productOrderTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime =deliveryTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime =completeTime;
    }
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum =orderNum;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind=kind;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture=picture;
    }

    public String getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(String deliveryNum) {
        this.deliveryNum=deliveryNum;
    }

    public String getUsernick() {
        return usernick;
    }

    public void setUsernick(String usernick) {
        this.usernick =usernick;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt =receipt;
    }

    public String getReceiptpic() {
        return receiptpic;
    }

    public void setReceiptpic(String receiptpic) {
        this.receiptpic =receiptpic;
    }


}

