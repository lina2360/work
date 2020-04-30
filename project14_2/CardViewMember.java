package com.project14.nccu105.project14_2;

public class CardViewMember {
    private String db;
    private String db2;
    private  String image;
    private String name;

    public CardViewMember() {
        super();
    }

    public CardViewMember(String db,String db2, String image, String name) {
        super();
        this.db = db;
        this.db2 = db2;
        this.image = image;
        this.name = name;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDb2() {
        return db2;
    }

    public void setDb2(String db2) {
        this.db2 = db2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
