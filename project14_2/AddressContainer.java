package com.project14.nccu105.project14_2;

public class AddressContainer {
    private String city;
    private String myaddress;

    public AddressContainer(String city,String myaddress) {
        this.city = city;
        this.myaddress=myaddress;

    }

    public AddressContainer() {

    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city=city;
    }

    public String getMyaddress() { return myaddress; }

    public void setMyaddress(String myaddress) {
        this.myaddress = myaddress;
    }




}
