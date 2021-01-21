package com.project14.nccu105.project14_2;

import android.content.Context;

public class PayConfContainer{
    private Context mContext;
    private String account;
    private String total;
    private String ok;

    //    private LayoutInflater mInflater;
    public PayConfContainer(String account,String total,String ok) {
        this.account = account;
        this.total=total;
        this.ok = ok;


    }

    public PayConfContainer() {

    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account=account;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total=total;
    }

    public String getOk() { return ok; }

    public void setOk(String ok) {
        this.ok = ok;
    }



}

