package com.ahmedhamdy.healthcare_adminversion;

public class Uploaded {
    private static Uploaded uploaded = new Uploaded();
    private Uploaded(){}

    public String virousName = "";
    public String virousImageUrl = "";
    public static Uploaded getInstance(){
        return uploaded;
    }
}
