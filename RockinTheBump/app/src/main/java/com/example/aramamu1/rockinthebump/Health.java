package com.example.aramamu1.rockinthebump;

public class Health {
    //fields
    private int healthID;
    private int userID;
    private String date;
    private int weight;
    private int bloodpressure;
    private int fetalhb;
    // constructors
    public Health() {}
    public Health(int id, int userid, String date, int weight, int bp, int fetalhb) {
        this.healthID = id;
        this.userID = userid;
        this.date = date;
        this.weight = weight;
        this.bloodpressure = bp;
        this.fetalhb = fetalhb;
    }
    // properties
    public void setID(int id) {
        this.healthID = id;
    }
    public int getID() {
        return this.healthID;
    }

    public void setUserID(int id) {
        this.userID = id;
    }
    public int getUserID() {
        return this.userID;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return this.weight;
    }

    public void setBloodpressure(int bloodpressure) {
        this.bloodpressure = bloodpressure;
    }
    public int getBloodpressure() {
        return this.bloodpressure;
    }

    public void setFetalhb(int id) {
        this.fetalhb = id;
    }
    public int getFetalhb() {
        return this.fetalhb;
    }
}
