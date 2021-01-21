package com.project14.nccu105.project14_2;

public class RatingContainer {

    private String who;
    private String kind;
    private String product;
    private float score;
    private String comment;
    private Long time;
    private  String usernick;

    //    private LayoutInflater mInflater;
    public RatingContainer(String who,String kind,String product,float score,String comment,Long time,String usernick) {
        this.who=who;
        this.kind=kind;
        this.product=product;
        this.score = score;
        this.comment = comment;
        this.time=time;
        this.usernick=usernick;
    }

    public RatingContainer() {

    }

    public String getWho() { return who; }

    public void setWho(String who) {
        this.who = who;
    }
    public String getKind() { return kind; }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getProduct() { return product; }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getComment() { return comment; }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUsernick() {
        return usernick;
    }

    public void setUsernick(String usernick) {
        this.usernick =usernick;
    }

}
