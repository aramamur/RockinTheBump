package com.example.aramamu1.rockinthebump;

public class Picture {
    int userid;
    String picture;
    // constructors
    public Picture() {}
    public Picture(int userid, String picture) {
        this.userid = userid;
        this.picture = picture;
    }

    public int getUserID() {
            return userid;
    }

    public void setUserID(int userid) {
        this.userid = userid;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
