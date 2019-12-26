package com.example.loginactivitytest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Parameter;

public class TransportScheduleUser {


    /*@SerializedName("id")
        @Expose*/
    private int id;

   /* @SerializedName("fkTransportId")
    @Expose*/
    private int fkTransportId;

   /* @SerializedName("fkFromLocation")
    @Expose*/
    private int fkFromLocation;

   /* @SerializedName("fkToLocation")
    @Expose*/
    private int fkToLocation;

    /*@SerializedName("departureTime")
    @Expose*/
    private String departureTime;

    public TransportScheduleUser() {
    }

    public TransportScheduleUser( int id,  int fkTransportId, int fkFromLocation, int fkToLocation, String departureTime) {
        super();
        this.id = id;
        this.fkTransportId = fkTransportId;
        this.fkFromLocation = fkFromLocation;
        this.fkToLocation = fkToLocation;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public void setTmpId(int tmpId) {
        this.id = tmpId;
    }

    public int getFkTransportId() {
        return fkTransportId;
    }

    public void setFkTransportId(int fkTransportId) {
        this.fkTransportId = fkTransportId;
    }

    public int getFkFromLocation() {
        return fkFromLocation;
    }

    public void setFkFromLocation(int fkFromLocation) {
        this.fkFromLocation = fkFromLocation;
    }

    public int getFkToLocation() {
        return fkToLocation;
    }

    public void setFkToLocation(int fkToLocation) {
        this.fkToLocation = fkToLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

}
