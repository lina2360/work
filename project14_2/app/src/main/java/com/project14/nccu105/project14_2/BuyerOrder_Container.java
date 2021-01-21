package com.project14.nccu105.project14_2;

import android.content.Context;
import android.media.Image;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


import java.util.Date;
// extends BaseAdapter
public class BuyerOrder_Container {
    private Context mContext;
    private Image image;
    private String classify;
    private String brand;
    private String name;
    private String standard;
    private String model;
    private String url;
    private String country;
    private String place;
    private String other;
    private String price;
    private String num;
    private String fee;
    private String delivery;
    private String user;
    private long messageTime;
    private String picture;
    private  String usernick;
    private String receipt;
//    private LayoutInflater mInflater;
    public BuyerOrder_Container(String classify,String brand,String name, String standard,String model,
                                String url,String country,String place,String other,String price,String num,String fee,
                                String delivery,String user,long messageTime,String picture,String usernick, String receipt) {
        this.classify = classify;
        this.brand = brand;
        this.name = name;
        this.standard = standard;
        this.model = model;
        this.url = url;
        this.country =country;
        this.place = place;
        this.other = other;
        this.price = price;
        this.num = num;
        this.fee = fee;
        this.delivery = delivery;
        this.user = user;
//        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Initialize to current time
        this.messageTime = messageTime;
        this.picture=picture;
        this.usernick=usernick;
        this.receipt=receipt;

    }

    public BuyerOrder_Container() {

    }


    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify =classify;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getName() {
        return name;
    }

    public void setNmae(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery =delivery;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt =receipt;
    }


//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageview = new ImageView(mContext);
////
//        imageview.setLayoutParams(new Gallery.LayoutParams(240, 120));		// 设置布局 图片120×120显示
//        imageview.setScaleType(ImageView.ScaleType.CENTER);				// 设置显示比例类型（不缩放）
//        return imageview;
//    }


}
