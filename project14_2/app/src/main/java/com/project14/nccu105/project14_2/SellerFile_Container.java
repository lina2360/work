package com.project14.nccu105.project14_2;

public class SellerFile_Container {//信箱 真實姓名(買) 生日(買) 身分證 戶籍地址 收款資訊(銀行 分行 帳號) 評價 賣家編號
    private String check;
    private String email;
    private  String name;
    private String id_num;
    private String myaddress;
    private String sellerrating;
    private String sellernum;
    private  String defbank;
    private String defbankbranch;
    private String defcardnum;
    SellerFile_Container(String check,String email,String name,String  sellerrating,String sellernum,String myaddress,String id_num,String defbank,String defbankbranch,String defcardnum){
        this.check=check;
        this.email=email;
        this.name=name;
        this.sellerrating=sellerrating;
        this.sellernum=sellernum;
        this.myaddress=myaddress;
        this.id_num=id_num;
        this.defbank=defbank;
        this.defbankbranch=defbankbranch;
        this.defcardnum=defcardnum;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check =check;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email =email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =name;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num =id_num;
    }

    public String getMyaddress() {
        return myaddress;
    }

    public void setMyaddress(String myaddress) {
        this.myaddress =myaddress;
    }

    public String getSellerrating() {
        return sellerrating;
    }

    public void setSellerrating(String sellerrating) {
        this.sellerrating =sellerrating;
    }

    public String getSellernum() {
        return sellernum;
    }

    public void setSellernum(String sellernum) {
        this.sellernum =sellernum;
    }

    public String getDefbank() {
        return defbank;
    }

    public void setDefbank(String defbank) {
        this.defbank =defbank;
    }

    public String getDefbankbranch() {
        return defbankbranch;
    }

    public void setDefbankbranch(String defbankbranch) {
        this.defbankbranch =defbankbranch;
    }

    public String getDefcardnum() {
        return defcardnum;
    }

    public void setDefcardnum(String defcardnum) {
        this.defcardnum =defcardnum;
    }

}
