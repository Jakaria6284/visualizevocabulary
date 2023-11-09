package com.example.visualizevocabulary.Model;

import com.google.firebase.Timestamp;

public class newModel {
    String name;
    String Bio;
    String uID;


    public newModel(String name, String bio, String uID) {
        this.name = name;
        Bio = bio;
        this.uID = uID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}
