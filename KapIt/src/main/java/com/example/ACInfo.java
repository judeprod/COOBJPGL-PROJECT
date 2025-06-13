package com.example;

public class ACInfo {
    private String Time;
    private String Location;
    private String Cost;


    public ACInfo(String Time, String Location, String Cost) {

        this.Time = Time;
        this.Location = Location;
        this.Cost = Cost;
    }



        public String getTime() {
        return Time;
    }

        public String getLocation() {
        return Location;
    }

    public String getCost() {
        return Cost;
    }
}
