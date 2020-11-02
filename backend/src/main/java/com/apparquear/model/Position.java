package com.apparquear.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Position {

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
        double distance;
        distance= Math.sqrt(Math.pow((this.latitude-position.getLatitude()),2) + Math.pow(this.longitude-position.getLongitude(),2));
        return distance;
    }
}
