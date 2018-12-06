package com.example.aramamu1.rockinthebump;

public class User {

    //fields
    private int userID;
    private String userName;
    private String userPswd;
    private String deliveryDate;
    private String initialWeight;
    // constructors
    public User() {}
    public User(int id, String userName, String userPswd, String deliveryDate, String initialWeight) {
        this.userID = id;
        this.userName = userName;
        this.userPswd = userPswd;
        this.deliveryDate = deliveryDate;
        this.initialWeight = initialWeight;
    }
    // properties
    public void setID(int id) {
        this.userID = id;
    }
    public int getID() {
        return this.userID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return this.userName;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }
    public String getUserPswd() {
        return this.userPswd;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setInitialWeight(String weight) {
        this.initialWeight= weight;
    }
    public String getInitialWeight() {
        return this.initialWeight;
    }
}
