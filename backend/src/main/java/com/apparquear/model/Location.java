package com.apparquear.model;

import javax.persistence.*;

@Entity
public class Location {

    double earthRadius = 6371;//km

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long location_ID;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    public Long getLocation_ID() {
        return location_ID;
    }

    public void setLocation_ID(Long location_ID) {
        this.location_ID = location_ID;
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

    public double getDistanceTo(Location location){
        //https://en.wikipedia.org/wiki/Haversine_formula
        double latitudeRad1 = Math.toRadians(this.latitude);
        double longitudeRad1 = Math.toRadians(this.longitude);
        double latitudeRad2 = Math.toRadians(location.getLatitude());
        double longitudeRad2 = Math.toRadians(location.getLongitude());

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
