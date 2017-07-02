package com.quick.location.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {
    private String dayName;
    private String openFrom;
    private String closedFrom;
    private boolean open;

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(String openFrom) {
        this.openFrom = openFrom;
    }

    public String getClosedFrom() {
        return closedFrom;
    }

    public void setClosedFrom(String closedFrom) {
        this.closedFrom = closedFrom;
    }

}