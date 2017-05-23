package com.neko.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Geometry {
    private Location location;


    public void setLocation(Location location) {
        this.location = location;
    }
}
