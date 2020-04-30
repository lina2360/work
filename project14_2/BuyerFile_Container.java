package com.project14.nccu105.project14_2;



public class BuyerFile_Container {
    private String mail;
    private int focusNum;
    private int fansNum;
    private String birthDay;
    private String sex;
    private String phoneNum;
    private String address;
    private String creditCard;
    private int isSeller;
    private int buyerRating;
    private String personalArticle;
    private String memberNum;
    private String picture;
    private String mybuy;
    private  String nickname;
    private String receipt;

    public BuyerFile_Container(String mail,int focusNum,int fansNum,String birthDay,String sex,String phoneNum,String address,String creditCard
            ,int isSeller,int buyerRating,String personalArticle,String memberNum,String picture,String mybuy,String nickname,String receipt){
        this.mail = mail;
        this.focusNum = focusNum;
        this.fansNum = fansNum;
        this.birthDay = birthDay;
        this.sex = sex;
        this.phoneNum =phoneNum;
        this.address=address;
        this.creditCard = creditCard;
        this.isSeller = isSeller;
        this.buyerRating = buyerRating;
        this.personalArticle = personalArticle;
        this.memberNum = memberNum;
        this.picture=picture;
        this.mybuy=mybuy;
        this.nickname=nickname;
        this.receipt=receipt;
    }

    public BuyerFile_Container(){

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail =mail;
    }

    public int getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(int focusNum) {
        this.focusNum=focusNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum =fansNum;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay =birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex =sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }

    public long getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(int isSeller) {
        this.isSeller = isSeller;
    }

    public int getBuyerRating() {
        return buyerRating;
    }

    public void setBuyerRating(int buyerRating) {
        this.buyerRating = buyerRating;
    }

    public String getPersonalArticle() {
        return personalArticle;
    }

    public void setPersonalArticle(String personalArticle) {
        this.personalArticle = personalArticle;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture =picture;
    }

    public String getMybuy() {
        return mybuy;
    }

    public void setMybuy(String mybuy) {
        this.mybuy =mybuy;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname =nickname;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt =receipt;
    }




}
