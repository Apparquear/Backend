package com.apparquear.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Position {

    double earthRadius = 6371;//km

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long position_ID;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    public Long getPosition_ID() {
        return position_ID;
    }

    public void setPosition_ID(Long position_ID) {
        this.position_ID = position_ID;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public double getDistanceTo(Position position){
        //https://en.wikipedia.org/wiki/Haversine_formula
        double latitudeRad1 = Math.toRadians(this.latitude);
        double longitudeRad1 = Math.toRadians(this.longitude);
        double latitudeRad2 = Math.toRadians(position.getLatitude());
        double longitudeRad2 = Math.toRadians(position.getLongitude());

        double deltaLongitude = (longitudeRad2 - longitudeRad1);
        double deltaLatitude = (latitudeRad2 - latitudeRad1);

        double sinLatitudeatitude = Math.sin(deltaLatitude / 2);
        double sinLongitude = Math.sin(deltaLongitude / 2);

        double a = (sinLatitudeatitude * sinLatitudeatitude) + Math.cos(latitudeRad1)*Math.cos(latitudeRad2)*(sinLongitude*sinLongitude);
        double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

        double distanceInMeters = earthRadius * c * 1000;

        return distanceInMeters;
    }
}
