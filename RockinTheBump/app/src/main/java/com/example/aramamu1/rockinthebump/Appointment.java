package com.example.aramamu1.rockinthebump;

public class Appointment {

    //fields
    private int userID;
    private String date;
    private String description;

    // constructors
    public Appointment() {}
    public Appointment(int userID, String date, String description) {
        this.userID = userID;
        this.date = date;
        this.description = description;
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

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }


}
